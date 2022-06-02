import React from 'react';
import { alpha } from '@mui/material/styles';
import { Box } from '@mui/material';
import WImage from '../image';
import getFileData from '../../utils/getFileData';
import { WIconButton } from '../button';
import WIcon from '../icon';

interface IUploadFilePreviewProps {
  file: File | string;
  onRemove: VoidFunction;
}

export default function UploadFilePreview({
  file,
  onRemove,
}: IUploadFilePreviewProps): JSX.Element {
  const { preview } = getFileData(file);

  return (
    <Box
      sx={{
        p: 0,
        m: 0.5,
        width: 64,
        height: 64,
        borderRadius: 1.25,
        overflow: 'hidden',
        position: 'relative',
      }}
    >
      <WImage
        alt="preview"
        src={preview}
        sx={{
          height: 1,
          position: 'absolute',
          border: (theme: any) => `solid 1px ${theme.palette.divider}`,
        }}
      />

      <Box sx={{ top: 6, right: 6, position: 'absolute' }}>
        <WIconButton
          size="small"
          onClick={onRemove}
          sx={{
            p: '2px',
            color: 'common.white',
            bgcolor: (theme: any) => alpha(theme.palette.grey[900], 0.72),
            '&:hover': {
              bgcolor: (theme: any) => alpha(theme.palette.grey[900], 0.48),
            },
          }}
        >
          <WIcon name="close" />
        </WIconButton>
      </Box>
    </Box>
  );
}
