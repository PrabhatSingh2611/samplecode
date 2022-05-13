import React from 'react';

import Divider, { DividerProps } from '@mui/material/Divider';

export interface WDividerProps extends DividerProps {}

function WDivider(props: WDividerProps): JSX.Element {
  return <Divider {...props} />;
}

export default WDivider;
