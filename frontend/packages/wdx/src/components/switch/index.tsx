import React from "react";

import { Switch, SwitchProps } from "@mui/material";

export interface WSwitchProps extends SwitchProps {}

function WSwitch(props: WSwitchProps): JSX.Element {
    return <Switch {...props} />;
}


export default WSwitch;