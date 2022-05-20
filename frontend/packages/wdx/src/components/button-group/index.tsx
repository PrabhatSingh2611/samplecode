import React from "react";

import ButtonGroup, { ButtonGroupProps } from '@mui/material/ButtonGroup';

export interface WButtonGroupProps extends ButtonGroupProps {}

function WButtonGroup(props: WButtonGroupProps): JSX.Element {
    return <ButtonGroup {...props} />;
}

export default WButtonGroup;