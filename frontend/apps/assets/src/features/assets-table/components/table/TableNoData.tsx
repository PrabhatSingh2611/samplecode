import React from 'react';

import { WTable, WEmptyState } from 'wdx';

type Props = {
    isNotFound: boolean;
};

export default function TableNoData({ isNotFound }: Props): JSX.Element {
    return (
        <WTable.Row>
            {isNotFound ? (
                <WTable.Cell colSpan={12}>
                    <WEmptyState
                        title="No matches found"
                        subtitle="Change your search query or modify filters"
                        sx={{
                            '& span.MuiBox-root': { height: 160 },
                        }}
                    />
                </WTable.Cell>
            ) : (
                <WTable.Cell colSpan={12} sx={{ p: 0 }} />
            )}
        </WTable.Row>
    );
}
