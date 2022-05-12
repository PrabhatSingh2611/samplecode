import React from "react";

import Badge, { BadgeProps } from '@mui/material/Badge';

export interface WBadgeProps extends BadgeProps {}

function WBadge(props: WBadgeProps):JSX.Element {
    return <Badge {...props} />;
}

export default WBadge;