import { Meta, Story } from '@storybook/react';
import React, {useRef, useState} from 'react';
import WPopover from './index';
import WButton from "../button";
import WTypography from "../typography";

const meta: Meta = {
  title: 'Extra Components/Popover',
  component: WPopover,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
  const buttonRef = useRef(null);

  const [popoverAnchor, setPopoverAnchor] = useState<HTMLElement | null>(null);

  const handleOpen = (event: React.MouseEvent<HTMLElement>) => {
    setPopoverAnchor(event.currentTarget);
  };

  const handleClose = () => {
    setPopoverAnchor(null);
  };

  return <>
    <WButton ref={buttonRef} onClick={handleOpen}>Toggle popover</WButton>
    <WPopover {...args} open={!!popoverAnchor} anchorEl={popoverAnchor} onClose={handleClose}>
      <WTypography>popover content</WTypography>
    </WPopover>
  </>;
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});

Default.args = {
  open: false,
  arrow: 'top-left'
};
