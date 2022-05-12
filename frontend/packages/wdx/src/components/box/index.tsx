import React from "react";

import Box, { BoxProps } from '@mui/material/Box';

export interface WBoxProps extends BoxProps {}

function WBox(props: WBoxProps):JSX.Element {
    return <Box {...props} />;
}

export default WBox;