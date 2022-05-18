import { useReactiveVar } from '@apollo/client';

import { isAuthorizedVar } from 'local-state/reactiveVars';

export const useGetIsUserAuthorized = (): boolean => {
    return useReactiveVar(isAuthorizedVar);
};
