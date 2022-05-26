import { ApolloError } from '@apollo/client';

import {
    GetAssetsForAssetsListQuery,
    GetAssetsForAssetsListQueryHookResult,
    useGetAssetsForAssetsListQuery,
} from 'features/assets-table/graphql/queries/assetsForAssetsList.generated';
import { AssetSortEnum } from 'graphql-generated-types/types';

interface IUseGetAssetsTableListProps {
    input?: string;
    itemsPerPage?: number;
    pageNumber?: number;
    sort?: [AssetSortEnum];
}

interface IUseGetAssetsTableListResult {
    assetsListQueryResult: GetAssetsForAssetsListQueryHookResult;
    data: GetAssetsForAssetsListQuery | undefined;
    isLoading: boolean;
    error?: ApolloError;
}

export const useGetAssetsTableList = ({
    input,
    itemsPerPage,
    pageNumber,
    sort = [AssetSortEnum.WaybillDateDesc],
}: IUseGetAssetsTableListProps): IUseGetAssetsTableListResult => {
    const assetsListQueryResult = useGetAssetsForAssetsListQuery({
        variables: {
            input: {
                where: { query: input },
                pagination: {
                    itemsPerPage,
                    pageNumber,
                },
                sort,
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
