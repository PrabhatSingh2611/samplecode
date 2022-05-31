import React from 'react';

import { WStack, WTable, WTableRowProps, WSkeleton } from 'wdx';

export default function TableSkeleton(props: WTableRowProps): JSX.Element {
    const rowHeight = 40;

    return (
        <WTable.Row {...props}>
            <WTable.Cell colSpan={12}>
                <WStack spacing={3} direction="row" alignItems="center">
                    <WSkeleton
                        variant="rectangular"
                        width={65}
                        height={65}
                        sx={{ flexShrink: 0, borderRadius: 1 }}
                    />
                    <WSkeleton variant="text" width="30%" height={rowHeight} />
                    <WSkeleton variant="text" width="18%" height={rowHeight} />
                    <WSkeleton variant="text" width="20%" height={rowHeight} />
                    <WSkeleton variant="text" width="15%" height={rowHeight} />
                    <WSkeleton variant="text" width="15%" height={rowHeight} />
                    <WSkeleton variant="text" width="3%" height={rowHeight} />
                </WStack>
            </WTable.Cell>
        </WTable.Row>
    );
}
