import React, { useState } from 'react';
import WCheckbox from '../../../components/checkbox';
import WTable from '../../../components/table';
import WTableMoreMenu from './table-more-menu';
import { TMoreMenuActions } from '../base-table.widget';

interface IWTableRow {
  // TODO Fix type (VS)
  rowData: any;
  isCheckable?: boolean;
  hover?: boolean;
  selected?: boolean;
  onSelectRow?: (row: string) => void;
  //TODO: FIX types (VS)
  headerData: any;
  moreMenuActions?: TMoreMenuActions;
}

export default function WTableRow({
  rowData,
  isCheckable,
  hover,
  selected,
  onSelectRow,
  moreMenuActions,
  headerData,
}: IWTableRow) {
  const [open, setOpen] = useState<HTMLElement | null>(null);

  const rowDataCopy = { ...rowData };
  delete rowDataCopy.id;
  return (
    <WTable.Row
      hover={hover}
      selected={selected}
      onClick={() => onSelectRow?.(rowData.id)}
    >
      {isCheckable && (
        <WTable.Cell padding="checkbox">
          <WCheckbox checked={selected} />
        </WTable.Cell>
      )}
      {headerData.map((rowDataKey: any, index: number) => (
        <WTable.Cell
          key={rowData.id + rowDataCopy[rowDataKey.id]}
          align={index > 0 ? 'right' : 'left'}
        >
          {rowDataCopy[rowDataKey.id]}
        </WTable.Cell>
      ))}
      {moreMenuActions && (
        // TODO: Fix type (VS)
        <WTable.Cell onClick={(e: any): any => e.stopPropagation()}>
          <WTableMoreMenu
            open={open}
            setOpen={setOpen}
            actions={moreMenuActions}
          />
        </WTable.Cell>
      )}
    </WTable.Row>
  );
}
