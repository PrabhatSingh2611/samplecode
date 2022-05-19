import React from "react";

import Alert, { AlertProps } from '@mui/material/Alert';
import { AlertTitle, AlertTitleProps } from "@mui/material";

export interface WAlertProps extends AlertProps {};
export interface WAlertTitleProps extends AlertTitleProps {};

export function WAlertTitle(props: WAlertTitleProps):JSX.Element {
    return <AlertTitle {...props} />
}

function WAlert(props: WAlertProps):JSX.Element {
    return <Alert {...props} />;
}

export default WAlert;