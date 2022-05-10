import React from "react";

import Typography, { TypographyProps } from '@mui/material/Typography';

export interface WTypographyProps extends TypographyProps {}

function WTypography(props: WTypographyProps) {
    return <Typography {...props} />;
}

export default WTypography;