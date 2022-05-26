import React, { useState } from 'react';

import { AlignTableCell, WIcon, WMenuItem, WTable, WTableMoreMenu } from 'wdx';

interface IMoreMenuCellProps {
    onEditRow: VoidFunction;
    onDeleteRow: VoidFunction;
}

export function MoreMenuCell({ onEditRow, onDeleteRow }: IMoreMenuCellProps): JSX.Element {
    const [open, setOpen] = useState<HTMLElement | null>(null);

    return (
        <WTable.Cell align={AlignTableCell.RIGHT}>
            <WTableMoreMenu
                open={open}
                setOpen={setOpen}
                actions={({ onClose }: { onClose: () => void } | any): JSX.Element => (
                    <>
                        <WMenuItem
                            onClick={(): void => {
                                onEditRow();
                                onClose();
                            }}
                        >
                            <WIcon name="visibility" sx={{ width: 20, height: 20 }} />
                            View Details
                        </WMenuItem>

                        <WMenuItem
                            onClick={(): void => {
                                onEditRow();
                                onClose();
                            }}
                        >
                            <WIcon name="edit" sx={{ width: 20, height: 20 }} />
                            Edit
                        </WMenuItem>

                        <WMenuItem
                            onClick={(): void => {
                                onDeleteRow();
                                onClose();
                            }}
                        >
                            <WIcon name="delete" sx={{ width: 20, height: 20 }} />
                            Delete
                        </WMenuItem>
                    </>
                )}
            />
        </WTable.Cell>
    );
}
