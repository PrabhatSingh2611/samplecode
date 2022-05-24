import { Controller, useFormContext, FieldError } from 'react-hook-form';
import React from 'react';
import WDatePicker, { WDatePickerProps } from '../../date-picker';
import { WTextFieldProps } from '../../text-field';

export interface WFieldError extends FieldError {}

type WFormDatePickerProps = Omit<
  WDatePickerProps,
  'onChange' | 'value' | 'renderInput'
> & {
  name: string;
  onChange?: (date: unknown, keyboardInputValue?: string) => void;
  renderInput: (
    props: WTextFieldProps,
    error: WFieldError | undefined
  ) => React.ReactElement;
};

function WFormDatePicker({
  name,
  onChange,
  renderInput,
  ...other
}: WFormDatePickerProps) {
  const { control } = useFormContext();

  return (
    <Controller
      name={name}
      control={control}
      render={({ field, fieldState: { error } }) => {
        return (
          <WDatePicker
            value={field.value}
            onChange={(newValue, keyboardInputValue) => {
              field.onChange(newValue);
              onChange && onChange(newValue, keyboardInputValue);
            }}
            renderInput={(props): React.ReactElement =>
              renderInput(props, error)
            }
            {...other}
          />
        );
      }}
    />
  );
}

export default WFormDatePicker;
