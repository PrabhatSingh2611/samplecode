import React from 'react'
import { Box, Stack } from '@mui/material';
import WTypography from "../typography";
import UploadIllustration  from '../../assets/illustration_upload';

export default function WBlockContent():JSX.Element {
  return (
    <Stack
      spacing={2}
      alignItems="center"
      justifyContent="center"
      direction={{ xs: 'column', md: 'row' }}
      sx={{ width: 1, textAlign: { xs: 'center', md: 'left' } }}
    >
      <UploadIllustration sx={{ width: 220 }} />

      <Box sx={{ p: 3 }}>
        <WTypography gutterBottom variant="h5">
          Drop or Select file
        </WTypography>

        <WTypography variant="body2" sx={{ color: 'text.secondary' }}>
          Drop files here or click&nbsp;
          <WTypography
            variant="body2"
            component="span"
            sx={{ color: 'primary.main', textDecoration: 'underline' }}
          >
            browse
          </WTypography>
          &nbsp;thorough your machine
        </WTypography>
      </Box>
    </Stack>
  );
}
