import React, { memo } from 'react';
import { useInitRemoteExampleObservables } from './observable.hooks';

const RemoteExampleObservables = memo(
    () => {
        useInitRemoteExampleObservables();

        return <></>;
    },
    () => true
);

export default RemoteExampleObservables;
