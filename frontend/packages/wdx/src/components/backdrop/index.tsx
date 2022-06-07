import React from "react";

import Backdrop, { BackdropProps } from '@mui/material/Backdrop';

export interface WBackdropProps extends BackdropProps {};

function WBackdrop(props: WBackdropProps):JSX.Element {
    return <Backdrop {...props} />;
}

export default WBackdrop;