import React from 'react';

import { WButton } from 'wdx';

import { useGetIsUserAuthorized } from 'features/me/hooks/me.hooks';
import { isAuthorizedVar } from 'local-state/reactiveVars';

export default function AuthorizationBtn(): JSX.Element {
    const isAuthorized = useGetIsUserAuthorized();

    const auth = (): void => {
        isAuthorizedVar(!isAuthorized);
    };

    return <WButton onClick={auth}>{isAuthorized ? 'logout' : 'login'}</WButton>;
}
