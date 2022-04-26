import React, { useRef, useEffect, useState } from 'react';

import AsyncLoader, { ImportFallbacks, ImportState } from '../core/AsyncLoader';

export interface ImportLoaderProps {
    importLoadingFallback?: React.ReactNode;
    importErrorFallback?: React.ReactNode;
}

const PeopleApp = React.memo(
    ({ importLoadingFallback, importErrorFallback }: ImportLoaderProps): JSX.Element => {
        const ref = useRef(null);
        const [state, setState] = useState<ImportState | null>(null);

        useEffect(() => {
            setState(ImportState.LOADING);

            import('people/PeopleApp')
                .then(({ mount }) => {
                    // TODO: Handle "onUnmount" returned from "mount()"
                    mount(ref.current);
                    setState(ImportState.SUCCESS);
                })
                .catch((err) => {
                    console.error('Failed to import people/PeopleApp.', err);
                    setState(ImportState.ERROR);
                });
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }, []);

        return (
            <AsyncLoader>
                {/* NOTE: "<div ref={ref} />" should be always rendered */}
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
