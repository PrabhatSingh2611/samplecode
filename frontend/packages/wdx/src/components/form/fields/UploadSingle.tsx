import { Controller, useFormContext } from 'react-hook-form';
import React, { useCallback } from 'react';

import WUploadSingleFile from '../../upload/upload-single-file';
import { WUploadProps } from '../../upload/type';
import WForm from '../index';

type WFormUploadProps = WUploadProps & {
  name: string;
};

function WFormUploadSingle({ name, ...other }: WFormUploadProps) {
  const { control, setValue } = useFormContext();

  const handleDrop = useCallback(
    (acceptedFiles: any) => {
      const file = acceptedFiles[0];

      if (file) {
        setValue(name, { ...file, preview: URL.createObjectURL(file) });
      }
    },
    [setValue]
  );

  return (
    <Controller
      name={name}
      control={control}
      render={({ field, fieldState: { error } }) => (
        <WUploadSingleFile
          {...field}
          error={!!error}
          onDrop={handleDrop}
          helperText={
            !!error && (
              <WForm.HelperText error={true}>{error.message}</WForm.HelperText>
            )
          }
          {...other}
          file={field.value}
        />
      )}
    />
  );
}

export default WFormUploadSingle;
