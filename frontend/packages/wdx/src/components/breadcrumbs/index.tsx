import React from "react";

import Breadcrumbs, { BreadcrumbsProps } from '@mui/material/Breadcrumbs';

export interface WBreadcrumbsProps extends BreadcrumbsProps {}

function WBreadcrumbs(props: WBreadcrumbsProps):JSX.Element {
    return <Breadcrumbs {...props} />;
}

export default WBreadcrumbs;