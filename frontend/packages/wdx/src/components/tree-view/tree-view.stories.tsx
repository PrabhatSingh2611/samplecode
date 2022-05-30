import { Meta, Story } from '@storybook/react';
import React from 'react';
import WTreeView from './index';
import WForm from '../form';
import WCheckbox from '../checkbox';
import WStack from '../stack';
import WIcon from '../icon';
import {
  getRandomCity,
  getRandomCountry,
  getRandomUUID,
} from '../../utils/getRandomEntity';
import { setSelectedOnChange } from './tree-view.utils';
import { ILocation, IRenderOptions, ISelected } from './types';

const meta: Meta = {
  title: 'MUI/Data Display/TreeView',
  component: WTreeView,
};

export default meta;

const createLocation = (country: string): ILocation => {
  return {
    id: getRandomUUID(),
    country,
    name: getRandomCity(),
  };
};

const firstCountry = getRandomCountry();
const secondCountry = getRandomCountry();

const locationsArray = [
  {
    id: getRandomUUID(),
    name: firstCountry,
    locations: [
      createLocation(firstCountry),
      createLocation(firstCountry),
      createLocation(firstCountry),
    ],
  },
  {
    id: getRandomUUID(),
    name: secondCountry,
    locations: [createLocation(secondCountry), createLocation(secondCountry)],
  },
];

const Template: Story = () => {
  const [selected, setSelected] = React.useState<ISelected[]>([]);
  console.log('selected', selected);

  const renderTree = (nodes: IRenderOptions[]): JSX.Element[] =>
    nodes.map((node) => (
      <WTreeView.Item
        key={node.id}
        nodeId={node.id}
        label={
          <WForm.ControlLabel
            control={
              <WCheckbox
                checked={selected.some((element) => element.id === node.id)}
                onChange={(event: React.FormEvent<HTMLInputElement>): void =>
                  setSelectedOnChange({
                    checked: event.currentTarget.checked,
                    node,
                    selected,
                    setSelected,
                    optionsList: locationsArray,
                  })
                }
                onClick={(e: React.MouseEvent<HTMLButtonElement>): void =>
                  e.stopPropagation()
                }
              />
            }
            label={
              <WStack direction="row" alignItems="center">
                {node.name}
              </WStack>
            }
            key={node.id}
          />
        }
      >
        {Array.isArray(node.locations) && renderTree(node.locations)}
      </WTreeView.Item>
    ));

  return (
    <WTreeView
      defaultCollapseIcon={
        <WIcon name="expand-less" sx={{ width: 20, height: 20 }} />
      }
      defaultExpandIcon={
        <WIcon name="expand-more" sx={{ width: 20, height: 20 }} />
      }
    >
      {renderTree(locationsArray)}
    </WTreeView>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
