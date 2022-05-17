import React from "react";

import Fab, { FabProps } from '@mui/material/Fab';

export interface WFabProps extends FabProps {}

function WFab(props: WFabProps): JSX.Element {
    return <Fab {...props} />;
}

export default WFab;