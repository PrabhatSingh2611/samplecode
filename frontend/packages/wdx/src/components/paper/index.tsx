import React from "react";

import Paper, { PaperProps } from '@mui/material/Paper';

export interface WPaperProps extends PaperProps {}

function WPaper(props: WPaperProps):JSX.Element {
    return <Paper {...props} />;
}

export default WPaper;