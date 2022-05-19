import React, { forwardRef } from 'react';
import { useDropzone } from 'react-dropzone';
import { styled } from '@mui/material/styles';
import { Box } from '@mui/material';
import { WUploadProps } from './type';
import WImage from '../image';
import RejectionFiles from './rejection-files';
import BlockContent from './block-content';

const DropZoneStyle = styled('div')(({ theme }: any): any => ({
  outline: 'none',
  overflow: 'hidden',
  position: 'relative',
  padding: theme.spacing(5, 1),
  borderRadius: theme.shape.borderRadius,
  transition: theme.transitions.create('padding'),
  backgroundColor: theme.palette.background.neutral,
  border: `1px dashed ${theme.palette.grey[500_32]}`,
  '&:hover': { opacity: 0.72, cursor: 'pointer' },
}));

const WUploadSingleFile = forwardRef<HTMLInputElement, WUploadProps>(
  ({ error = false, file, helperText, sx, footer, ...other }, ref) => {
    const {
      getRootProps,
      getInputProps,
      isDragActive,
      isDragReject,
      fileRejections,
    } = useDropzone({
      multiple: false,
      ...other,
    });

    return (
      <Box sx={{ width: '100%', ...sx }}>
        <DropZoneStyle
          {...getRootProps()}
          sx={{
            ...(isDragActive && { opacity: 0.72 }),
            ...((isDragReject || error) && {
              color: 'error.main',
              borderColor: 'error.light',
              bgcolor: 'error.lighter',
            }),
            ...(file && {
              padding: '12% 0',
            }),
          }}
        >
          <input {...getInputProps()} ref={ref} />

          <BlockContent />

          {file && (
            <WImage
              alt="file preview"
              src={typeof file === 'string' ? file : file.preview}
              sx={{
                top: 0,
                left: 0,
                borderRadius: 1,
                position: 'absolute',
                width: '100%',
                height: '100%',
              }}
            />
          )}
          {footer && footer}
        </DropZoneStyle>

        {fileRejections.length > 0 && (
          <RejectionFiles fileRejections={fileRejections} />
        )}

        {helperText && helperText}
      </Box>
    );
  }
);

export default WUploadSingleFile;
