import { useLayoutEffect } from 'react';
import { useHistory } from 'react-router-dom';

import { createRemoteObservable } from 'wdx';

// NOTE: !!! This should be done only once per app. !!!
type TAssetsNavigatePayload = {
    pathname: string;
    search?: string;
};

const assetsNavigateObservable = createRemoteObservable<TAssetsNavigatePayload>('assets:navigate', {
    type: 'object',
    properties: {
        pathname: {
            type: 'string',
        },
        search: {
            type: 'string',
        },
    },
    required: ['pathname'],
});

// NOTE: !!! This should be done only once per app. !!!
type HostNavigatePayload = {
    pathname: string;
    search: string;
};

export const hostNavigateObservable = createRemoteObservable<HostNavigatePayload>('host:navigate', {
    type: 'object',
    properties: {
        pathname: {
            type: 'string',
        },
        search: {
            type: 'string',
        },
    },
    required: ['pathname'],
});

// NOTE: Hooks should be called only inside <Router> child to be able to work with useHistory()
export const useInitAssetsObservables = (): void => {
    useInitHostNavigateObservable();
    useInitAssetsNavigateObservable();
};

const useInitHostNavigateObservable = (): void => {
    const history = useHistory();
    const onHostNavigate = ({ pathname, search }: Location): void => {
        console.count('onHostNavigate');
        if (search && search !== history.location.search) {
            return history.replace(pathname + search);
        }
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
    const onHistoryChange = ({ pathname, search }: Location): void => {
        const lastEvent = assetsNavigateObservable.observable.getLastEvent();
        if (search && lastEvent?.search !== search) {
            return assetsNavigateObservable.publish({ pathname, search });
        }
        if (lastEvent?.pathname !== pathname) {
            assetsNavigateObservable.publish({ pathname });
        }
    };
    // TODO: Fix typo
    history.listen(onHistoryChange as any);
};
