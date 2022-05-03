import React, { Suspense } from 'react';
import { ErrorBoundary } from 'react-error-boundary';

export interface AsyncLoaderProps {
    children: React.ReactNode;
}

export default function AsyncLoader({ children }: AsyncLoaderProps): JSX.Element {
    return (
        <ErrorBoundary FallbackComponent={AsyncErrorFallback}>
            <Suspense fallback={<AsyncLoadingFallback />}>{children}</Suspense>
        </ErrorBoundary>
    );
}

interface FallbackProps {
    error: Error;
    resetErrorBoundary: (...args: Array<unknown>) => void;
}

const AsyncErrorFallback = ({ error }: FallbackProps): JSX.Element => {
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
export const AsyncLoadingFallback = (): JSX.Element => <b>Loading...</b>;

// TODO: Replace "<b>Loading ...</b>" with "<Progress />" component
export const ImportLoadingFallback = (): JSX.Element => <b>Loading...</b>;

export const ImportErrorFallback = (): JSX.Element => (
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

export interface ImportFallbacksProps {
    state: ImportState;
    loadingFallback?: React.ReactNode;
    errorFallback?: React.ReactNode;
}

export const ImportFallbacks = ({
    state,
    loadingFallback,
    errorFallback,
}: ImportFallbacksProps): JSX.Element => (
    <>
        {state === ImportState.LOADING && (loadingFallback || <ImportLoadingFallback />)}
        {state === ImportState.ERROR && (errorFallback || <ImportErrorFallback />)}
    </>
);
