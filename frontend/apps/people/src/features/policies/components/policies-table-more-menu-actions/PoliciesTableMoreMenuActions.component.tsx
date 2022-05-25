import React, { useState } from 'react';

import { WMenuItem, WTableMoreMenu, WTypography, WIcon, createDownloadLink } from 'wdx';

import { PolicyForPolicyListFragment } from 'features/policies/graphql/queries/getPoliciesForPoliciesList.generated';

interface IPoliciesTableMoreMenuActions {
    rowData: PolicyForPolicyListFragment;
}

export const PoliciesTableMoreMenuActions = ({
    rowData,
}: IPoliciesTableMoreMenuActions): JSX.Element => {
    const [open, setOpen] = useState<HTMLElement | null>(null);

    return (
        <WTableMoreMenu
            open={open}
            setOpen={setOpen}
            // TODO: Fix linter error on onClose if it has type (VS)
            actions={({ onClose }: { onClose: () => void } | any): JSX.Element => (
                <>
                    <WTypography variant="h6" sx={{ mb: 1, px: 1.5 }}>
                        Actions
                    </WTypography>
                    <WMenuItem
                        onClick={(e: React.MouseEvent<HTMLElement>): void => {
                            e.stopPropagation();
                            onClose();
                        }}
                    >
                        <WIcon name="visibility" />
                        Preview
                    </WMenuItem>
                    <WMenuItem
                        onClick={(e: React.MouseEvent<HTMLElement>): void => {
                            e.stopPropagation();
                            onClose();
                            createDownloadLink(rowData.file.url);
                        }}
                    >
                        <WIcon name="download" />
                        Download
                    </WMenuItem>
                </>
            )}
        />
    );
};
