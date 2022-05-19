import React, { useState } from 'react';

import { Meta, Story } from '@storybook/react';
import WBaseTable, { AlignTableCell, Order } from './base-table.widget';
import WTableToolBar from './components/table-toolbar';
import { IconButton, Tooltip } from '@mui/material';
import WIconify from '../../components/iconify';
import { WMenuItem } from '../../components/menu';
import WTable from '../../components/table';
import WCheckbox from '../../components/checkbox';
import WButton from '../../components/button';
import { onSelectRow } from '../../widgets/table/utils';

const makeOption = (text: string) => {
  return { id: text + '/option', label: text };
};

const ROLE_OPTIONS = [
  makeOption('all'),
  makeOption('ux designer'),
  makeOption('full stack designer'),
  makeOption('backend developer'),
  makeOption('project manager'),
  makeOption('leader'),
  makeOption('ui designer'),
  makeOption('ui/ux designer'),
  makeOption('front end developer'),
  makeOption('full stack developer'),
];

const STATUS_OPTIONS = [
  makeOption('banned'),
  makeOption('online'),
  makeOption('offline'),
  makeOption('deleted'),
];

const headerData = [
  { id: 'dessert', label: 'Dessert (100g serving)' },
  { id: 'calories', label: 'Calories', align: AlignTableCell.RIGHT },
  { id: 'fat', label: 'Fat (g)', align: AlignTableCell.RIGHT },
  { id: 'carbs', label: 'Carbs (g)', align: AlignTableCell.RIGHT },
  { id: 'protein', label: 'Protein (g)', align: AlignTableCell.RIGHT },
  { id: 'actions', label: '', align: AlignTableCell.RIGHT },
];

const meta: Meta = {
  title: 'Widgets/Extended Table',
  component: WBaseTable,
  parameters: {
    controls: { expanded: true },
  },
};

function createData(
  dessert: string,
  calories: number,
  fat: number,
  carbs: number,
  protein: number,
  id = Math.random() + ''
) {
  return { id, dessert, calories, fat, carbs, protein };
}

const bodyData = [
  createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
  createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
  createData('Eclair', 262, 16.0, 24, 6.0),
  createData('Cupcake', 305, 3.7, 67, 4.3),
  createData('Gingerbread', 356, 16.0, 49, 3.9),
];

export default meta;

const Template: Story = () => {
  const [selectedRole, setSelectedRole] = useState('all/option');
  const [selectedStatus, setSelectedStatus] = useState('online/option');
  const [searchValue, setSearchValue] = useState('protein');

  const [orderBy, setOrderBy] = useState('protein');
  const [order, setOrder] = useState(Order.ASC);
  const [page, setCurrentPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [selected, setSelected] = useState<string[]>([]);

  const MoreMenuActions = ({
    onClose,
  }: {
    onClose: () => void;
  }): JSX.Element => (
    <>
      <WMenuItem
        onClick={(e) => {
          e.stopPropagation();
          onClose();
          console.log('OnClickHandler');
        }}
        sx={{ color: 'error.main' }}
      >
        <WIconify icon="eva:trash-2-outline" />
        Delete
      </WMenuItem>
      <WMenuItem
        onClick={(e) => {
          e.stopPropagation();
          onClose();
          console.log('OnClickHandler');
        }}
      >
        <WIconify icon="eva:edit-fill" />
        Edit
      </WMenuItem>
    </>
  );

  const SelectActions = (): JSX.Element => (
    <Tooltip title="Delete">
      <IconButton
        color="primary"
        onClick={() => console.log(selected + ' are deleted')}
      >
        <WIconify icon="eva:trash-2-outline" />
      </IconButton>
    </Tooltip>
  );

  const toolbarElements: JSX.Element = (
    <>
      <WTableToolBar.Filter
        label="Role"
        selectedFilter={selectedRole}
        onFilterChange={setSelectedRole}
        filterOptions={ROLE_OPTIONS}
      />
      <WTableToolBar.Filter
        label="Status"
        selectedFilter={selectedStatus}
        onFilterChange={setSelectedStatus}
        filterOptions={STATUS_OPTIONS}
      />
      <WTableToolBar.SearchBar
        onSearchChange={setSearchValue}
        searchValue={searchValue}
      />
    </>
  );

  return (
    <WBaseTable
      currentPage={page}
      setCurrentPage={setCurrentPage}
      order={order}
      setOrder={setOrder}
      orderBy={orderBy}
      setOrderBy={setOrderBy}
      rowsPerPage={rowsPerPage}
      setRowsPerPage={setRowsPerPage}
      selected={selected}
      setSelected={setSelected}
      headerData={headerData}
      bodyData={bodyData}
      isCheckable={true}
      withFooter={true}
      totalPageCount={30}
      toolbarElements={toolbarElements}
      selectActions={<SelectActions />}
      moreMenuActions={MoreMenuActions}
      renderBodyRow={(rowData): JSX.Element => (
        <CustomRow
          selected={selected.includes(rowData.id)}
          isCheckable={true}
          onSelectRow={(rowId: string) =>
            onSelectRow({ id: rowId, selected, setSelected })
          }
          rowData={rowData}
        />
      )}
    />
  );
};

type TRowData = {
  id: string;
  dessert: string;
  fat: number;
  calories: number;
  carbs: number;
  protein: number;
};

interface ICustomRow {
  rowData: TRowData;
  onSelectRow: (rowData: string) => void;
  selected: boolean;
  isCheckable: boolean;
}

function CustomRow({
  rowData,
  selected,
  onSelectRow,
  isCheckable,
}: ICustomRow): JSX.Element {
  const rowDataCopy = { ...rowData };
  //TODO:Fix ts-ignore (VS)
  // @ts-ignore
  delete rowDataCopy.id;
  return (
    <WTable.Row selected={selected} onClick={() => onSelectRow?.(rowData.id)}>
      {isCheckable && (
        <WTable.Cell padding="checkbox">
          <WCheckbox checked={selected} />
        </WTable.Cell>
      )}
      <WTable.Cell key={rowData.id + rowDataCopy.dessert} align="left">
        {rowDataCopy.dessert}
      </WTable.Cell>
      <WTable.Cell key={rowData.id + rowDataCopy.calories} align="right">
        <WButton color="primary">{rowDataCopy.calories}</WButton>
      </WTable.Cell>
      <WTable.Cell key={rowData.id + rowDataCopy.fat} align="right">
        <WButton color="error">{rowDataCopy.fat}</WButton>
      </WTable.Cell>
      <WTable.Cell key={rowData.id + rowDataCopy.fat} align="right">
        <WIconify
          icon="eva:more-vertical-fill"
          width={20}
          height={20}
          color="error"
        ></WIconify>
        {rowDataCopy.fat}
      </WTable.Cell>
      <WTable.Cell key={rowData.id + rowDataCopy.fat} align="right">
        {rowDataCopy.fat}
      </WTable.Cell>
      {<span>more ?</span>}
    </WTable.Row>
  );
}

export const Default = Template.bind({});

Default.args = {};
