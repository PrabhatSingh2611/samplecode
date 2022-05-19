import { Controller, useFormContext } from 'react-hook-form';
import React from 'react';

import WTextField, { WTextFieldProps } from '../../text-field';

type WFormInputProps = WTextFieldProps & {
  name: string;
};

function WFormInput({ name, ...other }: WFormInputProps) {
  const { control } = useFormContext();

  return (
    <Controller
      name={name}
      control={control}
      render={({ field, fieldState: { error } }) => (
        <WTextField
          {...field}
          value={field.value || ''}
          fullWidth={true}
          error={!!error}
          helperText={error?.message}
          {...other}
        />
      )}
    />
  );
}

export default WFormInput;
