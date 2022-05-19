import React from "react";

import Select, { SelectProps } from "@mui/material/Select";

export interface WSelectProps extends SelectProps {}

function WSelect(props: WSelectProps): JSX.Element {
    return <Select {...props} />;
}

export default WSelect;