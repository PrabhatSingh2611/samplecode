import React, { memo } from 'react';

import { useInitPeopleObservables } from 'core/observable.hooks';

const PeopleObservables = memo(
    function PeopleObservables(): JSX.Element {
        useInitPeopleObservables();

        return <></>;
    },
    () => true,
);

export default PeopleObservables;
