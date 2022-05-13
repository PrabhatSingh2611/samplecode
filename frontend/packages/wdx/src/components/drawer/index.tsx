import React from "react";

import Drawer, { DrawerProps } from '@mui/material/Drawer';

export interface WDrawerProps extends DrawerProps {}

function WDrawer(props: WDrawerProps): JSX.Element {
    return <Drawer {...props} />;
}

export default WDrawer;