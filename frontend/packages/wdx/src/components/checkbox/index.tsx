import React from 'react';

import { Checkbox, CheckboxProps } from '@mui/material';

export interface WCheckboxProps extends CheckboxProps {}

const WCheckbox = React.forwardRef<HTMLButtonElement, WCheckboxProps>(
  (props, ref) => {
    return <Checkbox {...props} ref={ref} />;
  }
);

export default WCheckbox;
