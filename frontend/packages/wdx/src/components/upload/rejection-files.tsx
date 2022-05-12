import React from 'react'
import { FileRejection } from 'react-dropzone';
import {alpha, Theme} from '@mui/material/styles';
import { Box, Paper } from '@mui/material';
import WTypography  from '../typography'
import { fData } from '../../utils/formatNumber';
import getFileData from '../../utils/getFileData';

type WRejectionProps = {
  fileRejections: FileRejection[];
};

export default function WRejectionFiles({ fileRejections }: WRejectionProps):JSX.Element
{
  return (
    <Paper
      variant="outlined"
      sx={{
        py: 1,
        px: 2,
        mt: 3,
        borderColor: 'error.light',
        bgcolor: (theme: Theme) => alpha(theme.palette.error.main, 0.08),
      }}
    >
      {fileRejections.map(({ file, errors }) => {
        const { path, size } = getFileData(file);

        return (
          <Box key={path} sx={{ my: 1 }}>
            <WTypography variant="subtitle2" noWrap>
              {path} - {size ? fData(size) : ''}
            </WTypography>

            {errors.map((error) => (
              <Box key={error.code} component="li" sx={{ typography: 'caption' }}>
                {error.message}
              </Box>
            ))}
          </Box>
        );
      })}
    </Paper>
  );
}
