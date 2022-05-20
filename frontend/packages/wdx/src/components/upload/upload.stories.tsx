import { Meta, Story } from '@storybook/react';
import React from 'react';
import WUploadSingleFile from './upload-single-file';
import WButton from '../button';
import WIconify from '../iconify';

const meta: Meta = {
  title: 'Extra Components/Upload singe file',
  component: WUploadSingleFile,
};

export default meta;

const uploadButton = (
  <WButton>
    <WIconify icon="WifiOff" />
    Upload file
  </WButton>
);

const Template: Story = () => {
  return <WUploadSingleFile file={null} footer={uploadButton} />;
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = { file: null, footer: uploadButton };
