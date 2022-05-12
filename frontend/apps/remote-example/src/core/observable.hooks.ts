import { useLayoutEffect } from 'react';
import { useHistory } from 'react-router-dom';

import { createRemoteObservable } from 'wdx';

// NOTE: !!! This should be done only once per app. !!!
type RemoteExampleNavigatePayload = {
    pathname: string;
};

export const remoteExampleNavigateObservable = createRemoteObservable<RemoteExampleNavigatePayload>(
        'remote-example:navigate',
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

export const hostNavigateObservable = createRemoteObservable<HostNavigatePayload>(
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
export const useInitRemoteExampleObservables = () => {
    useInitHostNavigateObservable();
    useInitRemoteExampleNavigateObservable();
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

const useInitRemoteExampleNavigateObservable = () => {
    const history = useHistory();

    const onHistoryChange = ({ pathname }: Location) => {
        const lastEvent = remoteExampleNavigateObservable?.observable.getLastEvent();
        if (lastEvent?.pathname !== pathname) {
            remoteExampleNavigateObservable?.publish({ pathname });
        }
    };
    // TODO: Fix typo
    history.listen(onHistoryChange as any);
};
