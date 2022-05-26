import React from 'react';

import { WImage, WList, WStack, WTooltip, WTansitionFade } from 'wdx';

import { AssetsLocationFragment } from 'features/assets-table/graphql/queries/assetsForAssetsList.generated';

interface ILocationCellProps {
    location: AssetsLocationFragment;
}

export function LocationCell({ location }: ILocationCellProps): JSX.Element {
    const { country, details, flagIcon } = location;

    return (
        <WTooltip
            title={country}
            TransitionComponent={WTansitionFade}
            TransitionProps={{ timeout: 600 }}
        >
            <WStack direction="row" alignItems="center">
                <WImage
                    disabledEffect
                    src={flagIcon}
                    alt={country}
                    sx={{ width: 24, height: 18, marginRight: 1 }}
                />
                <WList.ItemText primary={details} />
            </WStack>
        </WTooltip>
    );
}
