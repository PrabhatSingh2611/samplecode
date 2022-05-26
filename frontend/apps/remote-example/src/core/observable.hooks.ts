import { useLayoutEffect } from 'react';
import { useHistory } from 'react-router-dom';

import { createRemoteObservable } from 'wdx';

// NOTE: !!! This should be done only once per app. !!!
type AssetsNavigatePayload = {
    pathname: string;
};

const remoteAssetsObservable = createRemoteObservable<AssetsNavigatePayload>('assets:navigate', {
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

export const hostNavigateObservable = createRemoteObservable<HostNavigatePayload>('host:navigate', {
    type: 'object',
    properties: {
        pathname: {
            type: 'string',
        },
    },
    required: ['pathname'],
});

// NOTE: Hooks should be called only inside <Router> child to be able to work with useHistory()
export const useInitRemoteExampleObservables = (): void => {
    useInitHostNavigateObservable();
    useInitAssetsNavigateObservable();
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

const useInitAssetsNavigateObservable = (): void => {
    const history = useHistory();

    const onHistoryChange = ({ pathname }: Location): void => {
        const lastEvent = remoteAssetsObservable.observable.getLastEvent();
        if (lastEvent?.pathname !== pathname) {
            remoteAssetsObservable.publish({ pathname });
        }
    };
    // TODO: Fix typo
    history.listen(onHistoryChange as any);
};
