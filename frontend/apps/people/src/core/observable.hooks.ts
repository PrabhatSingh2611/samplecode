import { useLayoutEffect } from 'react';
import { useHistory } from 'react-router-dom';

import { createRemoteObservable } from 'wdx';

// NOTE: !!! This should be done only once per app. !!!
type TAuthNavigatePayload = {
    pathname: string;
};

const authNavigateObservable = createRemoteObservable<TAuthNavigatePayload>('auth:navigate', {
    type: 'object',
    properties: {
        pathname: {
            type: 'string',
        },
    },
    required: ['pathname'],
});

// NOTE: !!! This should be done only once per app. !!!
type HostNavigatePayload = {
    pathname: string;
};

export const hostNavigateObservable = window.__shared__.getRemoteObservable<HostNavigatePayload>(
    'host:navigate',
    {
        type: 'object',
        properties: {
            pathname: {
                type: 'string',
            },
        },
        required: ['pathname'],
    },
);

// NOTE: Hooks should be called only inside <Router> child to be able to work with useHistory()
export const useInitAuthObservables = (): void => {
    useInitHostNavigateObservable();
    useInitAuthNavigateObservable();
};

const useInitHostNavigateObservable = (): void => {
    const history = useHistory();

    const onHostNavigate = ({ pathname }: Location): void => {
        console.count('onHostNavigate');
        if (pathname !== history.location.pathname) {
            history.push(pathname);
        }
    };

    useLayoutEffect(() => {
        // TODO: Fix typo
        hostNavigateObservable.subscribe(onHostNavigate as any);
    });
};

const useInitAuthNavigateObservable = (): void => {
    const history = useHistory();

    const onHistoryChange = ({ pathname }: Location): void => {
        const lastEvent = authNavigateObservable.observable.getLastEvent();
        if (lastEvent?.pathname !== pathname) {
            authNavigateObservable.publish({ pathname });
        }
    };
    // TODO: Fix typo
    history.listen(onHistoryChange as any);
};
