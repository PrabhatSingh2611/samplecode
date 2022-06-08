import { castToArray, EFetchPolicy, Order } from 'wdx';

import {
    GetLocationsForLocationsListQueryHookResult,
    LocationForLocationsListFragment,
    useGetLocationsForLocationsListQuery,
} from 'features/locations/graphql/queries/locationsForLocationsList.generated';
import { useGetLocationsListSearchParams } from 'features/locations/hooks/searchParams.hooks';
import { LocationSort } from 'graphql-generated-types/types';

interface IUseGetLocationsTableListResult {
    locationsListQuery: GetLocationsForLocationsListQueryHookResult;
    locations?: LocationForLocationsListFragment[] | [];
}

export const useGetLocationsTableList = (
    fetchPolicy = EFetchPolicy.CACHE_ONLY,
): IUseGetLocationsTableListResult => {
    const { currentPage, order, rowsPerPage } = useGetLocationsListSearchParams();

    const locationsOrder = order === Order.DESC ? LocationSort.NameDesc : LocationSort.NameAsc;

    const locationsListQuery = useGetLocationsForLocationsListQuery({
        variables: {
            input: {
                pagination: {
                    itemsPerPage: rowsPerPage,
                    pageNumber: currentPage,
                },
                sort: [locationsOrder],
            },
        },
        fetchPolicy: fetchPolicy,
    });

    const locations = castToArray(locationsListQuery.data?.locations.items);

    return {
        locationsListQuery,
        locations,
    };
};
