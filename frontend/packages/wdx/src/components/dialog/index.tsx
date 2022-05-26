import React from "react";

import Dialog, { DialogProps } from '@mui/material/Dialog';
import DialogActions, { DialogActionsProps } from "@mui/material/DialogActions";
import DialogContent, { DialogContentProps } from "@mui/material/DialogContent";
import DialogContentText, { DialogContentTextProps } from "@mui/material/DialogContentText";
import DialogTitle, { DialogTitleProps } from "@mui/material/DialogTitle";

export interface WDialogProps extends DialogProps {}
export interface WDialogActionsProps extends DialogActionsProps {}
export interface WDialogContentProps extends DialogContentProps {}
export interface WDialogContentTextProps extends DialogContentTextProps {}
export interface WDialogTitleProps extends DialogTitleProps {}

function WDialog(props: WDialogProps):JSX.Element {
    return <Dialog {...props} />;
}

function WDialogActions(props: WDialogActionsProps):JSX.Element {
    return <DialogActions {...props} />;
}

function WDialogContent(props: WDialogContentProps):JSX.Element {
    return <DialogContent {...props} />;
}

function WDialogContentText(props: WDialogContentTextProps):JSX.Element {
    return <DialogContentText {...props} />;
}

function WDialogTitle(props: WDialogTitleProps):JSX.Element {
    return <DialogTitle {...props} />;
}

WDialog.Actions = WDialogActions;
WDialog.Content = WDialogContent;
WDialog.ContentText = WDialogContentText;
WDialog.Title = WDialogTitle;

export default WDialog;