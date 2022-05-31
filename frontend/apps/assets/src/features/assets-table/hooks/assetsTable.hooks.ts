import { Order } from 'wdx';

import { ApolloError } from '@apollo/client';

import { MIN_COUNT_OF_SYMBOLS_TO_SEARCH } from 'const/assets-table';
import {
    GetAssetsForAssetsListQuery,
    GetAssetsForAssetsListQueryHookResult,
    useGetAssetsForAssetsListQuery,
} from 'features/assets-table/graphql/queries/assetsForAssetsList.generated';
import { useGetAssetsListSearchParams } from 'features/assets-table/hooks/searcParams.hooks';
import { AssetSortEnum } from 'graphql-generated-types/types';

interface IUseGetAssetsTableListResult {
    assetsListQueryResult: GetAssetsForAssetsListQueryHookResult;
    data: GetAssetsForAssetsListQuery | undefined;
    isLoading: boolean;
    error?: ApolloError;
}

export const useGetAssetsTableList = (): IUseGetAssetsTableListResult => {
    const { currentPage, order, rowsPerPage, searchValue } = useGetAssetsListSearchParams();

    const waybillOrder =
        order === Order.DESC ? AssetSortEnum.WaybillDateDesc : AssetSortEnum.WaybillDateAsc;

    const query =
        searchValue && searchValue.length >= MIN_COUNT_OF_SYMBOLS_TO_SEARCH
            ? searchValue
            : undefined;

    const assetsListQueryResult = useGetAssetsForAssetsListQuery({
        variables: {
            input: {
                where: { query },
                pagination: {
                    itemsPerPage: rowsPerPage,
                    pageNumber: currentPage,
                },
                sort: [waybillOrder],
            },
        },
    });

    const { data, loading: isLoading, error } = assetsListQueryResult;

    return {
        assetsListQueryResult,
        data,
        isLoading,
        error,
    };
};
