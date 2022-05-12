import React, { Suspense } from 'react';
import { ErrorBoundary } from 'react-error-boundary';

interface IAsyncLoaderProps {
    children: React.ReactNode;
}

export default function AsyncLoader({ children }: IAsyncLoaderProps): JSX.Element {
    return (
        <ErrorBoundary FallbackComponent={AsyncErrorFallback}>
            <Suspense fallback={<AsyncLoadingFallback />}>{children}</Suspense>
        </ErrorBoundary>
    );
}

interface IFallbackProps {
    error: Error;
}

const AsyncErrorFallback = ({ error }: IFallbackProps): JSX.Element => {
    console.error(error);

    return (
        <div
            role="alert"
            style={{
                margin: 'auto',
                width: '600px',
                backgroundColor: 'pink',
                padding: 50,
                borderRadius: 10,
                color: 'red',
            }}
        >
            <b>Oh snap!</b>
            <p>There was a problem with this part.</p>
        </div>
    );
};

// TODO: Replace "<b>Loading ...</b>" with "<Progress />" component
const AsyncLoadingFallback = (): JSX.Element => <b>Loading...</b>;

// TODO: Replace "<b>Loading ...</b>" with "<Progress />" component
const ImportLoadingFallback = (): JSX.Element => <b>Loading...</b>;

const ImportErrorFallback = (): JSX.Element => (
    <div
        role="alert"
        style={{
            margin: 'auto',
            width: '600px',
            backgroundColor: 'pink',
            padding: 50,
            borderRadius: 10,
            color: 'red',
        }}
    >
        <b>Oh snap!</b>
        <p>Failed to import this part.</p>
    </div>
);

export enum ImportState {
    'LOADING' = 'loading',
    'SUCCESS' = 'success',
    'ERROR' = 'error',
}

interface IImportFallbacksProps {
    state: ImportState | null;
    loadingFallback?: React.ReactNode;
    errorFallback?: React.ReactNode;
}

export const ImportFallbacks = ({
    state,
    loadingFallback,
    errorFallback,
}: IImportFallbacksProps): JSX.Element => (
    <>
        {state === ImportState.LOADING && (loadingFallback || <ImportLoadingFallback />)}
        {state === ImportState.ERROR && (errorFallback || <ImportErrorFallback />)}
    </>
);
