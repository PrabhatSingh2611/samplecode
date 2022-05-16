import React from "react";

import TextField, { TextFieldProps } from '@mui/material/TextField';
import Input, { InputProps } from "@mui/material/Input";
import FilledInput, { FilledInputProps } from "@mui/material/FilledInput";
import OutlinedInput, { OutlinedInputProps } from "@mui/material/OutlinedInput";
import InputBase, { InputBaseProps } from "@mui/material/InputBase";
import InputLabel, { InputLabelProps } from "@mui/material/InputLabel";
import InputAdornment, { InputAdornmentProps } from "@mui/material/InputAdornment";
import FormControl, { FormControlProps } from "@mui/material/FormControl";
import FormHelperText, { FormHelperTextProps } from "@mui/material/FormHelperText";

export type WTextFieldProps = TextFieldProps & {}

export interface WInputProps extends InputProps {}

export interface WFilledInputProps extends FilledInputProps {}

export interface WOutlinedInputProps extends OutlinedInputProps {}

export interface WInputBaseProps extends InputBaseProps {}

export interface WInputLabelProps extends InputLabelProps {}

export interface WInputAdornmentProps extends InputAdornmentProps {}

export interface WFormControlProps extends FormControlProps {}

export interface WFormHelperTextProps extends FormHelperTextProps {}

export function WInput(props: WInputProps) {
    return <Input {...props} />
}

export function WFilledInput(props: WFilledInputProps) {
    return <FilledInput {...props} />
}

export function WOutlinedInput(props: WOutlinedInputProps) {
    return <OutlinedInput {...props} />
}

export function WInputBase(props: WInputBaseProps) {
    return <InputBase {...props} />
}

export function WInputLabel(props: WInputLabelProps) {
    return <InputLabel {...props} />
}

export function WInputAdornment(props: WInputAdornmentProps) {
    return <InputAdornment {...props} />
}

export function WFormControl(props: WFormControlProps) {
    return <FormControl {...props} />
}

export function WFormHelperText(props: WFormHelperTextProps) {
    return <FormHelperText {...props} />
}

function WTextField(props: WTextFieldProps): JSX.Element {
    return <TextField {...props} />;
}

export default WTextField;