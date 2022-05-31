import React from 'react';

import { WImage, WList, WStack, WTooltip, WTansitionFade } from 'wdx';

import { AssetsLocationFragment } from 'features/assets-table/graphql/queries/assetsForAssetsList.generated';

interface ILocationCellProps {
    location: AssetsLocationFragment;
}

export function LocationCell({ location }: ILocationCellProps): JSX.Element {
    const { country, name } = location;

    return (
        <WTooltip
            title={country.name}
            TransitionComponent={WTansitionFade}
            TransitionProps={{ timeout: 600 }}
        >
            <WStack direction="row" alignItems="center">
                <WImage
                    disabledEffect
                    src={country.iconName}
                    alt={country.name}
                    sx={{ width: 24, height: 18, marginRight: 1 }}
                />
                <WList.ItemText primary={name} />
            </WStack>
        </WTooltip>
    );
}
