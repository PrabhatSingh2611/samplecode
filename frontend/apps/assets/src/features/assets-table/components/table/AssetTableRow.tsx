import React from 'react';

import {
    WBox,
    WTable,
    WImage,
    WAvatar,
    WList,
    formatDateTime,
    AlignTableCell,
    WPill,
    castToString,
    deleteUtcFromDate,
} from 'wdx';

import { AssigneeCell } from 'features/assets-table/components/table/table-cells/AssigneeCell';
import { LocationCell } from 'features/assets-table/components/table/table-cells/LocationCell';
import { MoreMenuCell } from 'features/assets-table/components/table/table-cells/MoreMenuCell';
import { AssetsItemFragment } from 'features/assets-table/graphql/queries/assetsForAssetsList.generated';

interface IAssetTableRow {
    row: AssetsItemFragment;
    onEditRow: VoidFunction;
    onDeleteRow: VoidFunction;
}

export default function AssetTableRow({
    row,
    onEditRow,
    onDeleteRow,
}: IAssetTableRow): JSX.Element {
    const { title, assignee, location, serialNumber, tagNumber, type, waybillDate } = row;
    const waybillDateString = deleteUtcFromDate(waybillDate);

    return (
        <WTable.Row hover>
            <WTable.Cell align={AlignTableCell.LEFT}>
                <WAvatar variant="rounded" sx={{ width: 64, height: 64, bgcolor: '#D6E4FF' }}>
                    <WImage
                        disabledEffect
                        src={castToString(type.icon)}
                        alt={castToString(type.title)}
                        sx={{ width: '100%', height: '100%' }}
                    />
                </WAvatar>
            </WTable.Cell>

            <WTable.Cell align={AlignTableCell.LEFT}>
                <WBox>
                    <WList.ItemText primary={title} secondary={serialNumber} />
                </WBox>
            </WTable.Cell>

            <WTable.Cell align={AlignTableCell.LEFT}>
                {assignee ? (
                    <AssigneeCell assignee={assignee} />
                ) : (
                    <WPill variant="ghost" color="success" sx={{ textTransform: 'capitalize' }}>
                        Unassigned
                    </WPill>
                )}
            </WTable.Cell>

            <WTable.Cell align={AlignTableCell.CENTER}>
                <WList.ItemText primary={formatDateTime(waybillDateString)} />
            </WTable.Cell>

            <WTable.Cell align={AlignTableCell.LEFT}>
                {!!location && <LocationCell location={location} />}
            </WTable.Cell>

            <WTable.Cell align={AlignTableCell.LEFT}>
                {!!tagNumber && <WList.ItemText primary={tagNumber} />}
            </WTable.Cell>

            <MoreMenuCell onDeleteRow={onDeleteRow} onEditRow={onEditRow} />
        </WTable.Row>
    );
}
