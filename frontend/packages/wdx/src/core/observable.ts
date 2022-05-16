import { validate } from 'jsonschema';

import { Events, Observer, Observers, RemoteObservable } from './types';

function deepCompareStrict(a: any, b: any): boolean {
  if (typeof a !== typeof b) {
    return false;
  }

  if (Array.isArray(a)) {
    if (!Array.isArray(b)) {
      return false;
    }

    if (a.length !== b.length) {
      return false;
    }

    return a.every(function (_, i) {
      return deepCompareStrict(a[i], b[i]);
    });
  }

  if (typeof a === 'object') {
    if (!a || !b) {
      return a === b;
    }

    const aKeys = Object.keys(a);
    const bKeys = Object.keys(b);

    if (aKeys.length !== bKeys.length) {
      return false;
    }

    return aKeys.every(function (v) {
      return deepCompareStrict(a[v], b[v]);
    });
  }

  return a === b;
}

const SHARED = '__shared__';
const EVENTS = '__events__';
const CHANNELS = '__channels__';

export default class Observable<T = any> {
  private _namespace!: string;

  private _schema!: Object;

  private static initialize() {
    if (!window[SHARED]) {
      window[SHARED] = {
        [EVENTS]: {},
        [CHANNELS]: {},
        getRemoteObservable: (namespace, schema) =>
          createRemoteObservable(namespace, schema),
      };
    }

    if (!window[SHARED][EVENTS]) {
      window[SHARED][EVENTS] = {};
    }

    if (!window[SHARED][CHANNELS]) {
      window[SHARED][CHANNELS] = {};
    }
  }

  constructor(namespace: string, schema: Object) {
    Observable.initialize();

    this.namespace = namespace;
    this.schema = schema;
  }

  set namespace(namespace: string) {
    this._namespace = namespace;

    if (!this.events) this.events = [];
    if (!this.observers) this.observers = [];
  }

  set schema(newSchema: Object) {
    if (!window[SHARED][CHANNELS][this._namespace]) {
      window[SHARED][CHANNELS][this._namespace] = {
        observers: [],
        schema: null,
      };
    }
    const registered = window[SHARED][CHANNELS][this._namespace].schema;
    if (registered && !deepCompareStrict(registered, newSchema)) {
      throw new SchemaMismatchError(this._namespace, registered, newSchema);
    }
    window[SHARED][CHANNELS][this._namespace].schema = newSchema;
    this._schema = newSchema;
  }

  private get events(): Events<T> {
    return window[SHARED][EVENTS][this._namespace];
  }

  private set events(newEvents: Events<T>) {
    if (newEvents.length > 10) {
      newEvents.shift();
    }
    window[SHARED][EVENTS][this._namespace] = newEvents;
  }

  private get observers(): Observers<T> {
    if (!window[SHARED][CHANNELS][this._namespace]) {
      window[SHARED][CHANNELS][this._namespace] = {
        observers: [],
        schema: null,
      };
    }
    return window[SHARED][CHANNELS][this._namespace].observers;
  }

  private set observers(newObservers: Observers<T>) {
    if (newObservers.length > 50) {
      newObservers.shift();
      console.warn('Observers list is too long, first item was removed');
    }
    window[SHARED][CHANNELS][this._namespace].observers = newObservers;
  }

  getEvents(): Events<T> {
    return this.events;
  }

  getLastEvent(): T | undefined {
    const { events } = this;

    if (!events.length) {
      return;
    }

    return events[events.length - 1];
  }

  publish(data: T): void {
    if (
      typeof data !== 'undefined' &&
      this._schema &&
      !validate(data, this._schema).valid
    ) {
      throw new PayloadMismatchError(this._namespace, this._schema, data);
    }
    const { events } = this;
    const lastEvent = this.getLastEvent();

    this.observers.forEach((observer) => observer(data, { events, lastEvent }));

    this.events.push(data);
  }

  dispatch = this.publish;

  subscribe(observer: Observer<T>): void {
    this.unsubscribe(observer);
    this.observers = this.observers.concat(observer);
  }

  unsubscribe(observer: Observer<T>): void {
    this.observers = this.observers.filter((obs) => obs !== observer);
  }

  clear(): void {
    const { events } = this;
    const lastEvent = this.getLastEvent();

    this.observers.forEach((observer) =>
      observer(undefined, { events, lastEvent })
    );

    this.events = [];
    this.observers = [];
  }
}

class PayloadMismatchError extends Error {
  /**
   * Creates a new PayloadMismatchError error
   * @param namespace - name of event namespace
   * @param schema - registered schema on event namespace
   * @param payload - payload detail sent
   */
  constructor(
    public namespace: string,
    public schema: any,
    public payload: any
  ) {
    super(
      `payload does not match the specified schema for namespace [${namespace}].`
    );
    // @ts-ignore
    if (Error.captureStackTrace) {
      // @ts-ignore
      Error.captureStackTrace(this, PayloadMismatchError);
    }
    this.name = 'PayloadMismatchError';
  }
}

class SchemaMismatchError extends Error {
  /**
   * Creates a new SchemaMismatchError error
   * @param namespace - name of event namespace
   * @param schema - registered schema on event namespace
   * @param newSchema - new schema attempting to be registered on event namespace
   */
  constructor(
    public namespace: string,
    public schema: any,
    public newSchema: any
  ) {
    super(
      `schema registration for [${namespace}] must match already registered schema.`
    );

    // @ts-ignore
    if (Error.captureStackTrace) {
      // @ts-ignore
      Error.captureStackTrace(this, SchemaMismatchError);
    }
    this.name = 'SchemaMismatchError';
  }
}

export function createRemoteObservable<T = any>(
  namespace: string,
  schema: Object
): RemoteObservable<T> {
  const observable = new Observable(namespace, schema);
  const subscriptions = new Set<any>();

  return {
    observable,
    subscribe: (subscription: Observer<any>) => {
      subscriptions.add(subscription);
      observable.subscribe(subscription);
    },
    publish: (data: any) => {
      observable.publish(data);
    },
    unsubscribeAll: () => {
      subscriptions.forEach((subscription: Observer<any>) => {
        observable.unsubscribe(subscription);
      });
    },
  };
}
