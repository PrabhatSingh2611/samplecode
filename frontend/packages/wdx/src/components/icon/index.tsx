import React from "react";

import { SvgIcon, SvgIconProps } from "@mui/material";
import { TIcons } from "./icons.type";
import { iconsConfig } from "./icons.config";

export interface WIconProps extends SvgIconProps {
  name: TIcons;
}

export interface WSvgIconProps extends SvgIconProps {}

export function WSvgIcon(props: WSvgIconProps): JSX.Element {
    return <SvgIcon {...props} />
}

function WIcon({name, ...props}: WIconProps): JSX.Element {
    const Icon = iconsConfig[name];
    return <Icon {...props}/>;
}

export default WIcon;
