import React from 'react';

import Paper, { PaperProps } from '@mui/material/Paper';

export interface WPaperProps extends PaperProps {}

function WPaper(props: WPaperProps) {
  return <Paper {...props} />;
}

export default WPaper;
