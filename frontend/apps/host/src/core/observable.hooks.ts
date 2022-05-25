import { useLayoutEffect } from 'react';
import { useHistory } from 'react-router-dom';

import { Observable } from 'wdx';

import { PEOPLE_APP_CHANNEL, HOST_APP_CHANNEL } from 'core/constants';

import { EPeopleRouterLink } from '../models/people-router-link';

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
    const onHistoryChange = ({ pathname, search }: any): void => {
        const relativePathname = getRelativePathname(pathname, EPeopleRouterLink.People, false);
        const lastEvent = hostNavigateObservable.getLastEvent();
        if (search && lastEvent?.search !== search) {
            return hostNavigateObservable.publish({ pathname: relativePathname, search });
        }
        if (lastEvent?.pathname !== pathname) {
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
    const onPeopleNavigate = ({ pathname, search }: any): void => {
        const relativePathname = getRelativePathname(pathname, EPeopleRouterLink.People);
        const unPrependedRelativePathName = getRelativePathname(
            pathname,
            EPeopleRouterLink.People,
            false,
        );

        if (search && search !== history.location.search) {
            return history.replace(relativePathname + search);
        }
        if (pathname !== unPrependedRelativePathName) {
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
