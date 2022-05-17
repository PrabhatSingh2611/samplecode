import React from "react";

import { Checkbox, CheckboxProps } from "@mui/material";

export interface WCheckboxProps extends CheckboxProps {}

function WCheckbox(props: WCheckboxProps): JSX.Element {
    return <Checkbox {...props} />;
}


export default WCheckbox;