import { Meta, Story } from '@storybook/react';
import React from 'react';
import { WTable } from './index';
import WBaseTable from '../../widgets/table/base-table.widget';
import { TableCell } from '@mui/material';

const meta: Meta = {
  title: 'Widgets/Basic Table',
  component: WBaseTable,
  parameters: {
    controls: { expanded: true },
  },
};

function createData(
  name: string,
  calories: number,
  fat: number,
  carbs: number,
  protein: number,
  id = Math.random() + ''
) {
  return { id, name, calories, fat, carbs, protein };
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
  return (
    <WTable.Container>
      <WTable sx={{ minWidth: 650 }} aria-label="simple table">
        <WTable.Head>
          <WTable.Row>
            <WTable.Cell>Dessert (100g serving)</WTable.Cell>
            <TableCell align="right">Calories</TableCell>
            <TableCell align="right">Fat&nbsp;(g)</TableCell>
            <TableCell align="right">Carbs&nbsp;(g)</TableCell>
            <TableCell align="right">Protein&nbsp;(g)</TableCell>
          </WTable.Row>
        </WTable.Head>
        <WTable.Body>
          {bodyData.map((row) => (
            <WTable.Row
              key={row.name}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <WTable.Cell component="th" scope="row">
                {row.name}
              </WTable.Cell>
              <WTable.Cell align="right">{row.calories}</WTable.Cell>
              <WTable.Cell align="right">{row.fat}</WTable.Cell>
              <WTable.Cell align="right">{row.carbs}</WTable.Cell>
              <WTable.Cell align="right">{row.protein}</WTable.Cell>
            </WTable.Row>
          ))}
        </WTable.Body>
      </WTable>
    </WTable.Container>
  );
};

export const Default = Template.bind({});

Default.args = {};
