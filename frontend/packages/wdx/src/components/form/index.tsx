import React from 'react';
import {
  DeepPartial,
  FormProvider,
  UnpackNestedValue,
  useForm,
} from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';

import FormControl, { FormControlProps } from '@mui/material/FormControl';
import FormHelperText, {
  FormHelperTextProps,
} from '@mui/material/FormHelperText';
import FormControlLabel, {
  FormControlLabelProps,
} from '@mui/material/FormControlLabel';
import FormGroup, { FormGroupProps } from '@mui/material/FormGroup';
import FormLabel, { FormLabelProps } from '@mui/material/FormLabel';

import WFormInput from './fields/Input';
import WFormSelect from './fields/Select';
import WFormUploadSingle from './fields/UploadSingle';
import WFormCheckbox from './fields/Checkbox';
import WFormDatePicker from './fields/FormDatePicker';

import WFormSubmit from './fields/Submit';
import { OverridableComponent } from '@mui/material/OverridableComponent';
import { FormLabelTypeMap } from '@mui/material/FormLabel/FormLabel';

export interface WFormProps<T> {
  id: string;
  onSubmit: (values: UnpackNestedValue<T>) => void;
  validation?: any;
  defaultValues?: UnpackNestedValue<DeepPartial<T>>;
  children: React.ReactNode;
}

function WForm<T>({
  id,
  onSubmit,
  validation,
  defaultValues,
  children,
}: WFormProps<T>) {
  const methods = useForm<T>({
    resolver: validation ? yupResolver(validation) : undefined,
    defaultValues,
  });

  const { handleSubmit } = methods;

  return (
    <FormProvider {...methods}>
      <form id={id} onSubmit={handleSubmit(onSubmit)}>
        {children}
      </form>
    </FormProvider>
  );
}

export interface WFormControlProps extends FormControlProps {}

const WFormControl: typeof FormControl = (props: FormControlProps) => {
  return <FormControl {...props} />;
};

export interface WFormHelperTextProps extends FormHelperTextProps {}

function WFormHelperText(props: WFormHelperTextProps) {
  return <FormHelperText {...props} />;
}

export interface WFormControlLabelProps extends FormControlLabelProps {}

function WFormControlLabel(props: WFormControlLabelProps) {
  return <FormControlLabel {...props} />;
}

export interface WFormGroupProps extends FormGroupProps {}

function WFormGroup(props: WFormGroupProps) {
  return <FormGroup {...props} />;
}

export interface WFormLabelProps extends FormLabelProps {}
const WFormLabel: OverridableComponent<
  FormLabelTypeMap<{
    variant?: 'bold';
  }>
> = (props: WFormLabelProps) => {
  return <FormLabel {...props} />;
};

WForm.Control = WFormControl;
WForm.ControlLabel = WFormControlLabel;
WForm.HelperText = WFormHelperText;
WForm.Group = WFormGroup;
WForm.Label = WFormLabel;

// Fields
WForm.Submit = WFormSubmit;
WForm.Input = WFormInput;
WForm.Select = WFormSelect;
WForm.UploadSingle = WFormUploadSingle;
WForm.Checkbox = WFormCheckbox;
WForm.DatePicker = WFormDatePicker;

export default WForm;
