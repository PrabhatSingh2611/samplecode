import React from "react";
import FormControl, { FormControlProps } from "@mui/material/FormControl";
import FormHelperText, { FormHelperTextProps } from "@mui/material/FormHelperText";
import FormControlLabel, { FormControlLabelProps } from "@mui/material/FormControlLabel";
import FormGroup, { FormGroupProps } from "@mui/material/FormGroup";
import FormLabel, { FormLabelProps } from "@mui/material/FormLabel";


export interface WFormControlProps extends FormControlProps {}

export interface WFormHelperTextProps extends FormHelperTextProps {}

export interface WFormControlLabelProps extends FormControlLabelProps {}

export interface WFormGroupProps extends FormGroupProps {}

export interface WFormLabelProps extends FormLabelProps {}

function WFormControl(props: WFormControlProps) {
    return <FormControl {...props} />
}

export function WFormHelperText(props: WFormHelperTextProps) {
    return <FormHelperText {...props} />
}
export function WFormControlLabel(props: WFormControlLabelProps) {
    return <FormControlLabel {...props} />
}

export function WFormGroup(props: WFormGroupProps) {
    return <FormGroup {...props} />
}

export function WFormLabel(props: WFormLabelProps) {
    return <FormLabel {...props} />
}

export default WFormControl;