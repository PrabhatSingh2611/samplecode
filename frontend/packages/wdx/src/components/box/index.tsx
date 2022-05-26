import React from "react";

import Box, { BoxProps } from '@mui/material/Box';

export interface WBoxProps extends BoxProps {}

const WBox = React.forwardRef<HTMLElement, WBoxProps>((props, ref) => {
    return <Box {...props} ref={ref} />;
});

export default WBox;