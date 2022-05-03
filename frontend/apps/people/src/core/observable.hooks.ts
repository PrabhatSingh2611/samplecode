import { useLayoutEffect } from 'react';
import { useHistory } from 'react-router-dom';

// NOTE: !!! This should be done only once per app. !!!
type AuthNavigatePayload = {
    pathname: string;
};

export const authNavigateObservable = window.__shared__?.getRemoteObservable<AuthNavigatePayload>(
    'auth:navigate',
    {
        type: 'object',
        properties: {
            pathname: {
                type: 'string',
            },
        },
        required: ['pathname'],
    }
);

// NOTE: !!! This should be done only once per app. !!!
type HostNavigatePayload = {
    pathname: string;
};

export const hostNavigateObservable = window.__shared__?.getRemoteObservable<HostNavigatePayload>(
    'host:navigate',
    {
        type: 'object',
        properties: {
            pathname: {
                type: 'string',
            },
        },
        required: ['pathname'],
    }
);

// NOTE: Hooks should be called only inside <Router> child to be able to work with useHistory()
export const useInitAuthObservables = () => {
    useInitHostNavigateObservable();
    useInitAuthNavigateObservable();
};

const useInitHostNavigateObservable = () => {
    const history = useHistory();

    const onHostNavigate = ({ pathname }: Location) => {
        console.count('onHostNavigate');
        if (pathname !== history.location.pathname) {
            history.push(pathname);
        }
    };

    useLayoutEffect(() => {
        // TODO: Fix typo
        hostNavigateObservable?.subscribe(onHostNavigate as any);
    });
};

const useInitAuthNavigateObservable = () => {
    const history = useHistory();

    const onHistoryChange = ({ pathname }: Location) => {
        const lastEvent = authNavigateObservable?.observable.getLastEvent();
        if (lastEvent?.pathname !== pathname) {
            authNavigateObservable?.publish({ pathname });
        }
    };
    // TODO: Fix typo
    history.listen(onHistoryChange as any);
};
