import React from 'react';

import { WMenuItem } from 'wdx';

interface IAssetMenuItem {
    fieldName: string;
}

export function AssetMenuItem({ fieldName }: IAssetMenuItem): JSX.Element[] {
    const initialFieldName = 'All';

    return [
        <WMenuItem
            key={initialFieldName}
            value={initialFieldName}
            sx={{
                my: 0.5,
                mx: 1,
                textTransform: 'capitalize',
                borderRadius: 0.75,
                typography: 'body2',
            }}
        >
            {initialFieldName}
        </WMenuItem>,
        <WMenuItem
            key={fieldName}
            value={fieldName}
            sx={{
                display: 'none',
                my: 0.5,
                mx: 1,
                textTransform: 'capitalize',
                borderRadius: 0.75,
                typography: 'body2',
            }}
        >
            {fieldName}
        </WMenuItem>,
    ];
}
