import { Controller, useFormContext } from 'react-hook-form';
import React from 'react';

import WCheckbox, { WCheckboxProps } from '../../checkbox';

type WFormCheckboxProps = WCheckboxProps & {
  name: string;
};

function WFormCheckbox({ name, ...other }: WFormCheckboxProps) {
  const { control } = useFormContext();

  return (
    <Controller
      name={name}
      control={control}
      render={({ field }) => (
        <WCheckbox {...field} value={field.value} {...other} />
      )}
    />
  );
}

export default WFormCheckbox;
