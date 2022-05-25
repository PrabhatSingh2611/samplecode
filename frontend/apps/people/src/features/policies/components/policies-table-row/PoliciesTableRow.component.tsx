import React from 'react';

import {
    WAvatar,
    WTable,
    WCheckbox,
    WImage,
    AlignTableCell,
    WPill,
    WStack,
    WTypography,
    castToString,
    formatDate,
    deleteUtcFromDate,
    getAvatarInitials,
} from 'wdx';

import { PoliciesTableMoreMenuActions } from 'features/policies/components/policies-table-more-menu-actions/PoliciesTableMoreMenuActions.component';
import { PolicyForPolicyListFragment } from 'features/policies/graphql/queries/getPoliciesForPoliciesList.generated';
import { PolicyStatus } from 'graphql-generated-types/types';

interface IPolicyListTableRowProps {
    rowData: PolicyForPolicyListFragment;
    selected: boolean;
    onSelectRow: (rowId: string) => void;
}

export function PolicyListTableRow({
    rowData,
    selected,
    onSelectRow,
}: IPolicyListTableRowProps): JSX.Element {
    const isPublished = rowData.status === PolicyStatus.Published;

    const publicationDate = formatDate(deleteUtcFromDate(rowData.publicationDate));

    const policyName = (
        <WStack direction="row" alignItems="center" spacing={2}>
            <WImage
                src={castToString(rowData.file.thumbnail)}
                sx={{
                    width: 64,
                    height: 64,
                    borderRadius: 1.5,
                }}
            />
            <WTypography variant="body2" noWrap={true} maxWidth="80%">
                {rowData.title}
            </WTypography>
        </WStack>
    );

    const owner = (
        <WStack direction="row" alignItems="center" spacing={1}>
            <WAvatar variant="circular">
                {getAvatarInitials(`${rowData.owner.firstName} ${rowData.owner.lastName}`)}
            </WAvatar>
            <WTypography variant="body2" noWrap={true}>
                {rowData.owner.firstName} {rowData.owner.lastName}
            </WTypography>
        </WStack>
    );

    return (
        <WTable.Row onClick={(): void => onSelectRow(rowData.id)} selected={selected} hover={true}>
            <WTable.Cell padding="checkbox">
                <WCheckbox checked={selected} />
            </WTable.Cell>

            <WTable.Cell key={rowData.title} align={AlignTableCell.LEFT}>
                {policyName}
            </WTable.Cell>

            <WTable.Cell key={rowData.owner.id} align={AlignTableCell.LEFT}>
                {owner}
            </WTable.Cell>

            <WTable.Cell key={publicationDate} align={AlignTableCell.LEFT}>
                {publicationDate}
            </WTable.Cell>

            <WTable.Cell key={rowData.status} align={AlignTableCell.LEFT}>
                <WPill color={isPublished ? 'primary' : 'warning'}>
                    {isPublished ? 'Published' : 'Unpublished'}
                </WPill>
            </WTable.Cell>

            <WTable.Cell
                onClick={(e: React.MouseEvent<HTMLTableCellElement, MouseEvent>): void =>
                    e.stopPropagation()
                }
            >
                <PoliciesTableMoreMenuActions rowData={rowData} />
            </WTable.Cell>
        </WTable.Row>
    );
}
