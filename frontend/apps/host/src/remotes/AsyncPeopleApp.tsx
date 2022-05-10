import React, { useRef, useLayoutEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';

import AsyncLoader, { ImportFallbacks, ImportState } from 'core/AsyncLoader';
import { getRelativePathname } from 'core/observable.hooks';

export interface ImportLoaderProps {
    importLoadingFallback?: React.ReactNode;
    importErrorFallback?: React.ReactNode;
}

const PeopleApp = React.memo(
    ({ importLoadingFallback, importErrorFallback }: ImportLoaderProps): JSX.Element => {
        const ref = useRef(null);
        const unmountRef = useRef<Function | null>(null);
        const [state, setState] = useState<ImportState | null>(null);
        const history = useHistory();

        useLayoutEffect(() => {
            setState(ImportState.LOADING);

            import('people/PeopleApp')
                .then(({ mount }) => {
                    // TODO: Handle "onUnmount" returned from "mount()"
                    const relativePathname = getRelativePathname(
                        history.location.pathname,
                        '/people',
                        false
                    );
                    unmountRef.current = mount({
                        element: ref.current!,
                        inIsolation: false,
                        initialEntry: relativePathname,
                    });
                    setState(ImportState.SUCCESS);
                })
                .catch((err) => {
                    console.error('Failed to import people/PeopleApp.', err);
                    setState(ImportState.ERROR);
                });

            return () => {
                unmountRef.current?.();
            };
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }, []);

        return (
            <AsyncLoader>
                {/* IMPORTANT: "<div ref={ref} />" should always be rendered */}
                <div ref={ref} />
                <ImportFallbacks
                    state={state!}
                    loadingFallback={importLoadingFallback}
                    errorFallback={importErrorFallback}
                />
            </AsyncLoader>
        );
    }
);

export default PeopleApp;
