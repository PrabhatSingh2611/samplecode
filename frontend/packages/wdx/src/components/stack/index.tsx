import React from "react";

import Stack, { StackProps } from '@mui/material/Stack';

export interface WStackProps extends StackProps {}

function WStack(props: WStackProps):JSX.Element {
    return <Stack {...props} />;
}

export default WStack;