import React from "react";

import CircularProgress, { CircularProgressProps } from '@mui/material/CircularProgress';
import LinearProgress, { LinearProgressProps } from '@mui/material/LinearProgress';

export interface WCircularProgressProps extends CircularProgressProps {}

export function WCircularProgress(props: WCircularProgressProps):JSX.Element {
    return <CircularProgress {...props} />;
}

export interface WLinearProgressProps extends LinearProgressProps {}

export function WLinearProgress(props: WLinearProgressProps):JSX.Element {
    return <LinearProgress {...props} />;
}
