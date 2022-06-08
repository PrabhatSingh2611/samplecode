import { Meta, Story } from '@storybook/react';
import React from 'react';
import WTimeline from './index';

const meta: Meta = {
  title: 'MUI/Data Display/Timeline',
  component: WTimeline,
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

const Template: Story = () => {
  return (
    <WTimeline position='left' >
        <WTimeline.Item>
          <WTimeline.OppositeContent color="text.secondary">
            09:30 am
          </WTimeline.OppositeContent>
          <WTimeline.Separator>
            <WTimeline.Dot color="primary"/>
            <WTimeline.Connector />
          </WTimeline.Separator>
          <WTimeline.Content>Eat</WTimeline.Content>
        </WTimeline.Item>
        <WTimeline.Item>
          <WTimeline.OppositeContent color="text.secondary">
            10:00 am
          </WTimeline.OppositeContent>
          <WTimeline.Separator>
            <WTimeline.Dot color="error"/>
            <WTimeline.Connector />
          </WTimeline.Separator>
          <WTimeline.Content>Code</WTimeline.Content>
        </WTimeline.Item>
        <WTimeline.Item>
          <WTimeline.OppositeContent color="text.secondary">
            12:00 am
          </WTimeline.OppositeContent>
          <WTimeline.Separator>
            <WTimeline.Dot />
            <WTimeline.Connector />
          </WTimeline.Separator>
          <WTimeline.Content>Sleep</WTimeline.Content>
        </WTimeline.Item>
        <WTimeline.Item>
          <WTimeline.OppositeContent color="text.secondary">
            9:00 am
          </WTimeline.OppositeContent>
          <WTimeline.Separator>
            <WTimeline.Dot />
            <WTimeline.Connector />
          </WTimeline.Separator>
          <WTimeline.Content>Repeat</WTimeline.Content>
        </WTimeline.Item>
      </WTimeline>
  )
}

export const Default = Template.bind({});

Default.args = {
};
