import { Controller, useFormContext } from 'react-hook-form';
import React from 'react';

import WTextField, { WTextFieldProps } from '../../text-field';

type WFormSelectProps = WTextFieldProps & {
  name: string;
  children: React.ReactNode;
};

function WFormSelect({ name, ...other }: WFormSelectProps) {
  const { control } = useFormContext();

  return (
    <Controller
      name={name}
      control={control}
      render={({ field, fieldState: { error } }) => (
        <WTextField
          {...field}
          value={field.value || ''}
          select={true}
          fullWidth={true}
          SelectProps={{ native: false }}
          error={!!error}
          helperText={error?.message}
          {...other}
        />
      )}
    />
  );
}

export default WFormSelect;
