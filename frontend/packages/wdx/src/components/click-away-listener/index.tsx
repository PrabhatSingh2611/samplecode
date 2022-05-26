import React from "react";

import ClickAwayListener, { ClickAwayListenerProps } from '@mui/material/ClickAwayListener';

export interface WClickAwayListenerProps extends ClickAwayListenerProps {}

function WClickAwayListener(props: WClickAwayListenerProps):JSX.Element {
    return <ClickAwayListener {...props} />;
}

export default WClickAwayListener;