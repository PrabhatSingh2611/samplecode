import { Meta, Story } from '@storybook/react';
import React from 'react';
import WStack from '../stack';
import WTextMaxLine from './index';

const meta: Meta = {
  title: 'Extra Components/TextMaxLine',
  component: WTextMaxLine,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {

  return (
    <WStack alignItems="center" spacing={ 3 }>
      <WTextMaxLine {...args} sx={{ maxWidth: '50%' }}>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga officia ipsa eaque sit animi ab quo odit, esse aspernatur, doloremque voluptatem reiciendis beatae temporibus ad aliquam fugit, praesentium dignissimos! Rerum.
      </WTextMaxLine>
      <WTextMaxLine {...args} asLink={true} color="primary" href='#' sx={{ maxWidth: '50%'}}>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga officia ipsa eaque sit animi ab quo odit, esse aspernatur, doloremque voluptatem reiciendis beatae temporibus ad aliquam fugit, praesentium dignissimos! Rerum.
      </WTextMaxLine>
    </WStack>
  );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  variant: "subtitle1",
  component: "a",
  line: 2,
  persistent: false,
  asLink: false,
};
