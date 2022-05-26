import React from "react";

import Snackbar, { SnackbarProps, SnackbarOrigin } from '@mui/material/Snackbar';
import SnackbarContent, { SnackbarContentProps } from "@mui/material/SnackbarContent";

export interface WSnackbarProps extends SnackbarProps {}
export interface WSnackbarOrigin extends SnackbarOrigin {}
export interface WSnackbarContentProps extends SnackbarContentProps {}

function WSnackbar(props: WSnackbarProps):JSX.Element {
    return <Snackbar {...props} >{props.children}</Snackbar>;
}

export function WSnackbarContent(props: WSnackbarContentProps):JSX.Element {
    return <SnackbarContent {...props} />;
}

export default WSnackbar;