import React from "react";

import Typography, { TypographyProps } from '@mui/material/Typography';

export interface WTypographyProps extends TypographyProps {
    component?: string
}

function WTypography(props: WTypographyProps):JSX.Element {
    return <Typography {...props} />;
}

export default WTypography;