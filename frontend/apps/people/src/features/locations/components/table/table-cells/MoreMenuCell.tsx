import React, { useState } from 'react';

import { AlignTableCell, WIcon, WMenuItem, WTable, WTableMoreMenu } from 'wdx';

interface IMoreMenuCellProps {
    onEditRow: VoidFunction;
    onDeleteRow: VoidFunction;
    onViewDetails: VoidFunction;
}

export function MoreMenuCell({
    onEditRow,
    onDeleteRow,
    onViewDetails,
}: IMoreMenuCellProps): JSX.Element {
    const [menuAnchor, setMenuAnchor] = useState<HTMLElement | null>(null);
    const iconStyle = { width: 20, height: 20 };

    return (
        <WTable.Cell align={AlignTableCell.RIGHT}>
            <WTableMoreMenu
                open={menuAnchor}
                setOpen={setMenuAnchor}
                actions={({ onClose }): JSX.Element => (
                    <>
                        <WMenuItem
                            onClick={(): void => {
                                onViewDetails();
                                onClose();
                            }}
                        >
                            <WIcon name="visibility" sx={iconStyle} />
                            View Details
                        </WMenuItem>

                        <WMenuItem
                            onClick={(): void => {
                                onEditRow();
                                onClose();
                            }}
                        >
                            <WIcon name="edit" sx={iconStyle} />
                            Edit
                        </WMenuItem>

                        <WMenuItem
                            onClick={(): void => {
                                onDeleteRow();
                                onClose();
                            }}
                        >
                            <WIcon name="delete" sx={iconStyle} />
                            Delete
                        </WMenuItem>
                    </>
                )}
            />
        </WTable.Cell>
    );
}
