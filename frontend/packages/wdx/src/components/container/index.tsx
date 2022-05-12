import React from "react";

import Container, { ContainerProps } from '@mui/material/Container';

export interface WContainerProps extends ContainerProps {}

function WContainer(props: WContainerProps):JSX.Element {
    return <Container {...props} />;
}

export default WContainer;