import { Meta, Story } from '@storybook/react';
import React from 'react';
import WStack from '../stack';
import WTypography from '../typography';
import { WCircularProgress, WLinearProgress } from './index';

const meta: Meta = {
  title: 'MUI/Feedback/Progress',
  component: WCircularProgress,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return (
    <WStack direction="row" gap={10}>
        <WStack gap={2}>
            <WTypography variant='h4'>Indeterminate</WTypography>
            <WCircularProgress {...args} />
            <WCircularProgress {...args} color="error" size={100} />
            <WCircularProgress {...args} color="success" size={150}/>
        </WStack>
        <WStack gap={2}>
            <WTypography variant='h4'>Determinate</WTypography>
            <WCircularProgress {...args} variant="determinate" value={25} />
            <WCircularProgress {...args} variant="determinate" color="error"  value={50} size={100}/>
            <WCircularProgress {...args} variant="determinate" color="success" value={75} size={150}/>
        </WStack>
    </WStack>
  )
}

const LinearProgress: Story = args => {
    return (
      <WStack gap={10}>
          <WStack gap={2}>
              <WTypography variant='h4'>Indeterminate</WTypography>
              <WLinearProgress {...args} />
              <WLinearProgress {...args} color="warning"/>
          </WStack>
          <WStack gap={2}>
              <WTypography variant='h4'>Determinate</WTypography>
              <WLinearProgress {...args} variant="determinate" value={25} />
              <WLinearProgress {...args} variant="determinate" color="error" value={50}/>
          </WStack>
          <WStack gap={2}>
              <WTypography variant='h4'>Buffer</WTypography>
              <WLinearProgress {...args} variant="buffer" color="info" value={25} valueBuffer={50}/>
              <WLinearProgress {...args} variant="buffer" color="secondary" value={50} valueBuffer={60}/>
          </WStack>
      </WStack>
    )
  }

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Circular = Template.bind({});
export const Linear = LinearProgress.bind({});

Circular.args = {
    color: 'primary',
    variant: 'indeterminate',
    size: 50,
};

Linear.args = {
    color: 'primary',
    variant: 'indeterminate',
}
