import React, { memo } from 'react';

import { useInitAssetsObservables } from 'core/observable.hooks';

const AssetsObservables = memo(
    function AssetsObservables(): JSX.Element {
        useInitAssetsObservables();

        return <></>;
    },
    () => true,
);

export default AssetsObservables;
