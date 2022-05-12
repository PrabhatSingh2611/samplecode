import React, { forwardRef, ReactNode } from 'react';

import { Box, IconButton, IconButtonProps } from '@mui/material';
import { m } from 'framer-motion';

import { getVariantSize } from 'components/animate/FabButtonAnimate';

const IconButtonAnimate = forwardRef<HTMLButtonElement, IconButtonProps>(
    ({ children, size = 'medium', ...other }, ref): JSX.Element => (
        <AnimateWrap size={size}>
            <IconButton size={size} ref={ref} {...other}>
                {children}
            </IconButton>
        </AnimateWrap>
    ),
);

IconButtonAnimate.displayName = 'IconButtonAnimate';
export default IconButtonAnimate;

type AnimateWrapProp = {
    children: ReactNode;
    size: 'small' | 'medium' | 'large';
};

const varSmall = {
    hover: { scale: 1.1 },
    tap: { scale: 0.95 },
};

const varMedium = {
    hover: { scale: 1.09 },
    tap: { scale: 0.97 },
};

const varLarge = {
    hover: { scale: 1.08 },
    tap: { scale: 0.99 },
};

function AnimateWrap({ size, children }: AnimateWrapProp): JSX.Element {
    return (
        <Box
            component={m.div}
            whileTap="tap"
            whileHover="hover"
            variants={getVariantSize(size, varSmall, varMedium, varLarge)}
            sx={{
                display: 'inline-flex',
            }}
        >
            {children}
        </Box>
    );
}
