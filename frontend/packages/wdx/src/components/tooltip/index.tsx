import React from "react";

import Tooltip, { TooltipProps } from '@mui/material/Tooltip';

export interface WTooltipProps extends TooltipProps {}

function WTooltip(props: WTooltipProps): JSX.Element {
    return <Tooltip {...props} />;
}

export default WTooltip;