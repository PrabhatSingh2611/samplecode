import React from 'react';

import TextField, { TextFieldProps } from '@mui/material/TextField';
import Input, { InputProps } from '@mui/material/Input';
import FilledInput, { FilledInputProps } from '@mui/material/FilledInput';
import OutlinedInput, { OutlinedInputProps } from '@mui/material/OutlinedInput';
import InputBase, { InputBaseProps } from '@mui/material/InputBase';
import InputLabel, { InputLabelProps } from '@mui/material/InputLabel';
import InputAdornment, {
  InputAdornmentProps,
} from '@mui/material/InputAdornment';

export type WTextFieldProps = TextFieldProps & {};

export interface WInputProps extends InputProps {}

export interface WFilledInputProps extends FilledInputProps {}

export interface WOutlinedInputProps extends OutlinedInputProps {}

export interface WInputBaseProps extends InputBaseProps {}

export interface WInputLabelProps extends InputLabelProps {}

export interface WInputAdornmentProps extends InputAdornmentProps {}

export function WInput(props: WInputProps) {
  return <Input {...props} />;
}

export function WFilledInput(props: WFilledInputProps) {
  return <FilledInput {...props} />;
}

export function WOutlinedInput(props: WOutlinedInputProps) {
  return <OutlinedInput {...props} />;
}

export function WInputBase(props: WInputBaseProps) {
  return <InputBase {...props} />;
}

export function WInputLabel(props: WInputLabelProps) {
  return <InputLabel {...props} />;
}

export function WInputAdornment(props: WInputAdornmentProps) {
  return <InputAdornment {...props} />;
}

const WTextField = React.forwardRef<HTMLDivElement, WTextFieldProps>(
  (props, ref) => {
    return <TextField {...props} ref={ref} />;
  }
);

export default WTextField;
