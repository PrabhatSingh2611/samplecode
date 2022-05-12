import React from "react";

import Grid, { GridProps } from '@mui/material/Grid';

export interface WGridProps extends GridProps {}

function WGrid(props: WGridProps):JSX.Element {
    return <Grid {...props} />;
}

export default WGrid;