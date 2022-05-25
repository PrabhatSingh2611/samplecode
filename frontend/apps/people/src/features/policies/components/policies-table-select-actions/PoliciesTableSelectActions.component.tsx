import React from 'react';

import { WIconButton, WTooltip, WIcon } from 'wdx';
interface ISelectActionsProps {
    onDeletePolicies: () => void;
}

export function PoliciesTableSelectActions({ onDeletePolicies }: ISelectActionsProps): JSX.Element {
    return (
        <WTooltip title="Delete">
            <WIconButton color="primary" onClick={onDeletePolicies}>
                <WIcon name="delete" />
            </WIconButton>
        </WTooltip>
    );
}
