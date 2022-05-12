import React, { memo } from 'react';

import { useInitHostObservables } from 'core/observable.hooks';

const HostObservables = memo(
    function HostObservables(): JSX.Element {
        useInitHostObservables();

        return <></>;
    },
    () => true,
);

export default HostObservables;
