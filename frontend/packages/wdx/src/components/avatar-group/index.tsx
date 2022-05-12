import React from "react";

import AvatarGroup, { AvatarGroupProps } from '@mui/material/AvatarGroup';

export interface WAvatarGroupProps extends AvatarGroupProps {}

function WAvatarGroup(props: WAvatarGroupProps):JSX.Element {
    return <AvatarGroup {...props} />;
}

export default WAvatarGroup;