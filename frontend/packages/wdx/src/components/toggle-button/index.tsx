import React from "react";

import ToggleButton, { ToggleButtonProps } from '@mui/material/ToggleButton';
import ToggleButtonGroup, { ToggleButtonGroupProps } from '@mui/material/ToggleButtonGroup';

export interface WToggleButtonProps extends ToggleButtonProps {}
export interface WToggleButtonGroupProps extends ToggleButtonGroupProps {}

export function WToggleButton(props: WToggleButtonProps): JSX.Element {
    return <ToggleButton {...props} />;
}

function WToggleButtonGroup(props: WToggleButtonGroupProps): JSX.Element {
    return <ToggleButtonGroup {...props} />;
}

export default WToggleButtonGroup;