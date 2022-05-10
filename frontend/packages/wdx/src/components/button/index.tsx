import React from "react";

import Button, { ButtonProps } from '@mui/material/Button';

export interface WButtonProps extends ButtonProps {}

function WButton(props: WButtonProps) {
    return <Button {...props} />;
}

export default WButton;