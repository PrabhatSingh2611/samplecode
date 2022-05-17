import { Meta, Story } from '@storybook/react';
import React from 'react';
import WImage from './index';
import WBox from '../box';

const meta: Meta = {
  title: 'Extra Components/Image',
  component: WImage,
  argTypes: {
    children: {
      control: {
        type: 'contained',
      },
    },
  },
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = (args) => {
  return (
    <WBox sx={{m: '0 auto', maxWidth: 400, }}>
      <WImage
        {...args}
        ratio="1/1"
        src={'https://picsum.photos/1000'}
        alt="Card with image"
      />
    </WBox>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
