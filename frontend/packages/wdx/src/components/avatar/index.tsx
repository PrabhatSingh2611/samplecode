import React from "react";

import Avatar, { AvatarProps } from '@mui/material/Avatar';

export interface WAvatarProps extends AvatarProps {}

function WAvatar(props: WAvatarProps): JSX.Element {
    return <Avatar {...props} />;
}

export default WAvatar;