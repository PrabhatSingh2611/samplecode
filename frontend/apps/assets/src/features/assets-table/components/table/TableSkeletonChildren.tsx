import React from 'react';

import { WSkeleton } from 'wdx';

export default function TableSkeletonChildren(): JSX.Element {
    const rowHeight = 40;

    return (
        <>
            <WSkeleton variant="text" width="30%" height={rowHeight} />
            <WSkeleton variant="text" width="18%" height={rowHeight} />
            <WSkeleton variant="text" width="20%" height={rowHeight} />
            <WSkeleton variant="text" width="15%" height={rowHeight} />
            <WSkeleton variant="text" width="15%" height={rowHeight} />
            <WSkeleton variant="text" width="3%" height={rowHeight} />
        </>
    );
}
