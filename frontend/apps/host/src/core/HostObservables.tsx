import React, { memo } from 'react';
import { useInitHostObservables } from 'core/observable.hooks';

const HostObservables = memo(
    () => {
        useInitHostObservables();

        return <></>;
    },
    () => true
);

export default HostObservables;
