import React from 'react';

import Stack, { StackProps } from '@mui/material/Stack';

export interface WStackProps extends StackProps {}

const WStack = React.forwardRef<HTMLElement, WStackProps>((props, ref) => {
  return <Stack {...props} ref={ref} />;
});

export default WStack;
