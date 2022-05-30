import { ReactNode } from 'react';
import { DropzoneOptions } from 'react-dropzone';
import { SxProps } from '@mui/material';
import { Theme } from '@mui/material/styles';

export interface WCustomFile extends File {
  path?: string;
  preview?: string;
  lastModifiedDate?: Date;
}

export interface WUploadProps extends DropzoneOptions {
  error?: boolean;
  file?: WCustomFile | string | null;
  helperText?: ReactNode;
  sx?: SxProps<Theme>;
  footer?:JSX.Element
}
