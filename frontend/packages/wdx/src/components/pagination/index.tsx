import React from "react";

import Pagination, { PaginationProps } from '@mui/material/Pagination';
import PaginationItem, { PaginationItemProps } from "@mui/material/PaginationItem";

export interface WPaginationProps extends PaginationProps {}

export interface WPaginationItemProps extends PaginationItemProps {}

export function WPaginationItem(props: WPaginationItemProps):JSX.Element {
    return <PaginationItem {...props} />;
}

function WPagination(props: WPaginationProps):JSX.Element {
    return <Pagination {...props} />;
}

export default WPagination;