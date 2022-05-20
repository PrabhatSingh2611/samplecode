import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import WStack from '../stack';
import WTypography from '../typography';
import WPagination from './index';

const meta: Meta = {
  title: 'MUI/Navigation/Pagination',
  component: WPagination,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  return (
    <WStack spacing={5}>
        <WBox>
            <WTypography variant='h6' mb={2}>Small pagination</WTypography>
            <WPagination {...args} size="small" />
        </WBox>
        <WBox>
            <WTypography variant='h6' mb={2}>Default pagination</WTypography>
            <WPagination {...args} />
        </WBox>
        <WBox>
            <WTypography variant='h6' mb={2}>Large pagination</WTypography>
            <WPagination {...args} size="large" />
        </WBox>
        <WBox>
            <WTypography variant='h6' mb={2}>Primary color pagination</WTypography>
            <WPagination {...args} color="primary" />
        </WBox>
        <WBox>
            <WTypography variant='h6' mb={2}>Pagination rounded</WTypography>
            <WPagination {...args} shape="rounded"  />
        </WBox>
  </WStack>
  );
}

export const Default = Template.bind({});

Default.args = {
    count: 10,
    shape: 'circular',
    color: 'standard',
};
