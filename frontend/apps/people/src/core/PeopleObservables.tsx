import React, { memo } from 'react';

import { useInitAuthObservables } from 'core/observable.hooks';

const AuthObservables = memo(
    function AuthObservables(): JSX.Element {
        useInitAuthObservables();

        return <></>;
    },
    () => true,
);

export default AuthObservables;
