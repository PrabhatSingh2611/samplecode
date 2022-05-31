import React from 'react';

import { useUpdateSearchUrlParam, WIcon, WInputAdornment, WTextField } from 'wdx';

import { EAssetsSearchParams } from 'const/assets-table';
import { useGetAssetsListSearchParams } from 'features/assets-table/hooks/searcParams.hooks';

export function AssetsListSearch(): JSX.Element {
    const { searchValue } = useGetAssetsListSearchParams();
    const updateSearchParam = useUpdateSearchUrlParam();

    const onSearchChange = (value: string): void => {
        updateSearchParam(EAssetsSearchParams.SearchValue, value);
        updateSearchParam(EAssetsSearchParams.CurrentPage, 0);
    };

    return (
        <WTextField
            fullWidth
            value={searchValue}
            onChange={(event: React.ChangeEvent<HTMLInputElement>): void => {
                onSearchChange(event.target.value);
            }}
            placeholder="Search user..."
            InputProps={{
                startAdornment: (
                    <WInputAdornment position="start">
                        <WIcon name="search" />
                    </WInputAdornment>
                ),
            }}
        />
    );
}
