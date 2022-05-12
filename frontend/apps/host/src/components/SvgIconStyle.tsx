import React from 'react';

import { Box, BoxProps } from '@mui/material';

interface IProps extends BoxProps {
    src: string;
}

export default function SvgIconStyle({ src, sx }: IProps): JSX.Element {
    return (
        <Box
            component="span"
            sx={{
                display: 'inline-block',
                width: 24,
                height: 24,
                mask: `url(${src}) no-repeat center / contain`,
                WebkitMask: `url(${src}) no-repeat center / contain`,
                bgcolor: 'currentColor',
                ...sx,
            }}
        />
    );
}
