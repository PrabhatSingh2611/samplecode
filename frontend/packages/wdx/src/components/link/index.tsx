import React from "react";

import Link, { LinkProps } from '@mui/material/Link';

export interface WLinkProps extends LinkProps {}

function WLink(props: WLinkProps): JSX.Element {
    return <Link {...props} />;
}

export default WLink;