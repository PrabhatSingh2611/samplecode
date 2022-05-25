import React, { useRef, useLayoutEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';

import AsyncLoader, { ImportFallbacks, ImportState } from 'core/AsyncLoader';
import { getRelativePathname } from 'core/observable.hooks';
import { EPeopleRouterLink } from 'models/people-router-link';

interface IImportLoaderProps {
    importLoadingFallback?: React.ReactNode;
    importErrorFallback?: React.ReactNode;
}

const PeopleApp = React.memo(function PeopleApp({
    importLoadingFallback,
    importErrorFallback,
}: IImportLoaderProps): JSX.Element {
    const ref = useRef(null);
    const unmountRef = useRef<(() => void) | null>(null);
    const [state, setState] = useState<ImportState | null>(null);
    const history = useHistory();
    const searchParams = history.location.search;

    useLayoutEffect(() => {
        setState(ImportState.LOADING);

        import('people/PeopleApp')
            .then(({ mount }) => {
                // TODO: Handle "onUnmount" returned from "mount()"
                const relativePathname = getRelativePathname(
                    history.location.pathname,
                    EPeopleRouterLink.People,
                    false,
                );
                unmountRef.current = mount({
                    element: ref.current!,
                    inIsolation: false,
                    initialEntry: relativePathname + searchParams,
                });
                setState(ImportState.SUCCESS);
            })
            .catch((err) => {
                console.error('Failed to import people/PeopleApp.', err);
                setState(ImportState.ERROR);
            });

        return (): void => {
            unmountRef.current?.();
        };
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <AsyncLoader>
            {/* IMPORTANT: "<div ref={ref} />" should always be rendered */}
            <div ref={ref} />
            <ImportFallbacks
                state={state}
                loadingFallback={importLoadingFallback}
                errorFallback={importErrorFallback}
            />
        </AsyncLoader>
    );
});

export default PeopleApp;
