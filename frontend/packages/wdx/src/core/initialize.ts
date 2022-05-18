import {APOLLO_CACHE, CHANNELS, createRemoteObservable, EVENTS, SHARED,} from './observable';

export default function initialize() {
    if (!window[SHARED]) {
        window[SHARED] = {
            [EVENTS]: {},
            [CHANNELS]: {},
            [APOLLO_CACHE]: {},
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

    if (!window[SHARED][APOLLO_CACHE]) {
        window[SHARED][APOLLO_CACHE] = {};
    }
}
