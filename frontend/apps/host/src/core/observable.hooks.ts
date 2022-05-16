import { useLayoutEffect } from 'react';
import { useHistory } from 'react-router-dom';

import { Observable } from 'wdx';

import { PEOPLE_APP_CHANNEL, HOST_APP_CHANNEL } from 'core/constants';

// NOTE: Hooks should be called only inside <Router> child to be able to work with useHistory()
export const useInitHostObservables = (): void => {
    useInitHostNavigateObservable();
    useInitPeopleNavigateObservable();
};

const useInitHostNavigateObservable = (): void => {
    const history = useHistory();
    const hostNavigateObservable = new Observable(
        HOST_APP_CHANNEL.NAVIGATE.EVENT,
        HOST_APP_CHANNEL.NAVIGATE.SCHEMA,
    );

    // TODO: Fix typo
    const onHistoryChange = ({ pathname }: any): void => {
        const lastEvent = hostNavigateObservable.getLastEvent();
        if (lastEvent?.pathname !== pathname) {
            const relativePathname = getRelativePathname(pathname, '/people', false);
            hostNavigateObservable.publish({ pathname: relativePathname });
        }
    };
    history.listen(onHistoryChange);
};

const useInitPeopleNavigateObservable = (): void => {
    const peopleNavigateObservable = new Observable(
        PEOPLE_APP_CHANNEL.NAVIGATE.EVENT,
        PEOPLE_APP_CHANNEL.NAVIGATE.SCHEMA,
    );
    const history = useHistory();

    // TODO: Fix typo
    const onPeopleNavigate = ({ pathname }: any): void => {
        console.count('onPeopleNavigate');
        if (pathname !== history.location.pathname) {
            const relativePathname = getRelativePathname(pathname, '/people');
            console.log(
                '🚀 ~ file: observable.hooks.ts ~ line 42 ~ onPeopleNavigate ~ relativePathname',
                relativePathname,
            );
            history.push(relativePathname);
        }
    };

    useLayoutEffect(() => {
        peopleNavigateObservable.subscribe(onPeopleNavigate);
    });
};

// NOTE: This functions will remove or add relatesTo string from pathname (i.e. "/people/details" <=> "/details")
export const getRelativePathname = (
    pathname: string,
    relatesTo: string,
    prepend = true,
): string => {
    if (prepend) {
        if (pathname === '/') {
            return relatesTo;
        }

        return relatesTo + pathname;
    }

    if (pathname === '/' || pathname === relatesTo) {
        return '/';
    }

    const replaceValue = `${relatesTo}/`;

    return pathname.replace(replaceValue, '/');
};
