import React from 'react';

import IconButton, { IconButtonProps } from '@mui/material/IconButton';

export interface WIconButtonProps extends IconButtonProps {}

function WIconButton(props: WIconButtonProps): JSX.Element {
  return <IconButton {...props} />;
}

export default WIconButton;
