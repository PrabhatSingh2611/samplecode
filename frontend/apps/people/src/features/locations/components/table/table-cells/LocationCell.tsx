import React from 'react';

import { castToString, WList, WStack, WTansitionFade, WTooltip } from 'wdx';

interface ILocationCellProps {
    name: string;
}

export function LocationCell({ name }: ILocationCellProps): JSX.Element {
    return (
        <WTooltip
            title={castToString(name)}
            TransitionComponent={WTansitionFade}
            TransitionProps={{ timeout: 600 }}
        >
            <WStack direction="row" alignItems="center">
                <WList.ItemText primary={castToString(name)} />
            </WStack>
        </WTooltip>
    );
}
