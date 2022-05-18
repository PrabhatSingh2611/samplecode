import React from "react";

import { SvgIconProps } from "@mui/material";
import { TIcons } from "./icons.type";
import { iconsConfig } from "./icons.config";

export interface WIconProps extends SvgIconProps {
  name: TIcons;
}

function WIcon({name, ...props}: WIconProps):JSX.Element {
    const Icon = iconsConfig[name];
    return <Icon {...props}/>;
}

export default WIcon;
