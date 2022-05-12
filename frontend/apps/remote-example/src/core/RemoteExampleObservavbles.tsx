import React, { memo } from 'react';

import { useInitRemoteExampleObservables } from './observable.hooks';

const RemoteExampleObservables = memo(
    function RemoteExampleObservables(): JSX.Element {
        useInitRemoteExampleObservables();

        return <></>;
    },
    () => true,
);

export default RemoteExampleObservables;
