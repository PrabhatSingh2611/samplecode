import React from "react";

import Chip, { ChipProps } from '@mui/material/Chip';

export interface WChipProps extends ChipProps {}

function WChip(props: WChipProps): JSX.Element {
    return <Chip {...props} />;
}

export default WChip;