import { Meta, Story } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import React from 'react';
import WImageCard from './index';
import WBox from '../../components/box';
import WIconButton from '../../components/icon-button';
import WIconify from '../../components/iconify';

const meta: Meta = {
  title: 'Widgets/ImageCard',
  component: WImageCard,
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
      <WImageCard
        {...args}
        image={{
          id: '1',
          imageUrl: 'https://picsum.photos/1000',
          title: 'Title',
          postAt: '2014-07-12 10:54:11'
        }}
        actions={
          <WIconButton color="inherit">
            <WIconify icon="eva:more-vertical-fill" width={20} height={20} />
          </WIconButton>
        }
        onClick={action('OnClick')}/>
      </WBox>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
