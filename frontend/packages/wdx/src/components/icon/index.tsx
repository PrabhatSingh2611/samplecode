import React from 'react';

import * as Icons from '@mui/icons-material';
import { SvgIconProps } from '@mui/material';
import { TIcons } from './icons.type';

export interface WIconProps extends SvgIconProps {
  name: TIcons;
}

function WIcon({ name, ...props }: WIconProps): JSX.Element {
  const Icon = Icons[name];
  return <Icon {...props} />;
}

export default WIcon;
