import React from "react";

import Button, { ButtonProps } from '@mui/material/Button';
import ButtonBase, { ButtonBaseProps } from '@mui/material/ButtonBase';
import IconButton, { IconButtonProps } from '@mui/material/IconButton';
import LoadingButton, { LoadingButtonProps } from '@mui/lab/LoadingButton';

export interface WButtonProps extends ButtonProps {}
export interface WButtonBaseProps extends ButtonBaseProps {}
export interface WIconButtonProps extends IconButtonProps {}
export interface WLoadingButtonProps extends LoadingButtonProps {}

export function WButtonBase(props: WButtonBaseProps): JSX.Element {
    return <ButtonBase {...props} />;
}

export function WIconButton(props: WIconButtonProps): JSX.Element {
    return <IconButton {...props} />;
}

export function WLoadingButton(props: LoadingButtonProps): JSX.Element {
    return <LoadingButton {...props} />;
}

function WButton(props: WButtonProps): JSX.Element {
    return <Button {...props} />;
}

export default WButton;