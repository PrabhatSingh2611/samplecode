import {
    GetLocationsForHeaderQueryHookResult,
    useGetLocationsForHeaderQuery,
} from 'features/header/graphql/locationsForHeader.generated';

export const useGetLocations = (): GetLocationsForHeaderQueryHookResult =>
    useGetLocationsForHeaderQuery();
