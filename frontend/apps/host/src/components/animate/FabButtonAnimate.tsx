import React, { forwardRef, ReactNode } from 'react';

import { Box, Fab, FabProps, SxProps } from '@mui/material';
import { useTheme } from '@mui/material/styles';
import { m, Variants } from 'framer-motion';

interface IProps extends Omit<FabProps, 'color'> {
    sxWrap?: SxProps;
    color?:
        | 'inherit'
        | 'default'
        | 'primary'
        | 'secondary'
        | 'info'
        | 'success'
        | 'warning'
        | 'error';
}

const FabButtonAnimate = forwardRef<HTMLButtonElement, IProps>(
    ({ color = 'primary', size = 'large', children, sx, sxWrap, ...other }, ref) => {
        const theme = useTheme();

        if (
            color === 'default' ||
            color === 'inherit' ||
            color === 'primary' ||
            color === 'secondary'
        ) {
            return (
                <AnimateWrap size={size} sxWrap={sxWrap}>
                    <Fab ref={ref} size={size} color={color} sx={sx} {...other}>
                        {children}
                    </Fab>
                </AnimateWrap>
            );
        }

        return (
            <AnimateWrap size={size} sxWrap={sxWrap}>
                <Fab
                    ref={ref}
                    size={size}
                    sx={{
                        color: theme.palette[color].contrastText,
                        boxShadow: theme.customShadows[color],
                        '&:hover': {
                            bgcolor: theme.palette[color].dark,
                        },
                        bgcolor: theme.palette[color].main,
                        ...sx,
                    }}
                    {...other}
                >
                    {children}
                </Fab>
            </AnimateWrap>
        );
    },
);

FabButtonAnimate.displayName = 'FabButtonAnimate';
export default { FabButtonAnimate };

type Size = 'small' | 'medium' | 'large';

type AnimateWrapProp = {
    children: ReactNode;
    size: Size;
    sxWrap?: SxProps;
};

const varSmall = {
    hover: { scale: 1.07 },
    tap: { scale: 0.97 },
};

const varMedium = {
    hover: { scale: 1.06 },
    tap: { scale: 0.98 },
};

const varLarge = {
    hover: { scale: 1.05 },
    tap: { scale: 0.99 },
};

export const getVariantSize = (
    size: Size,
    smallVar: Variants,
    mediumVar: Variants,
    largeVar: Variants,
): Variants => {
    if (size === 'small') {
        return smallVar;
    }

    if (size === 'medium') {
        return mediumVar;
    }

    return largeVar;
};

function AnimateWrap({ size, children, sxWrap }: AnimateWrapProp): JSX.Element {
    return (
        <Box
            component={m.div}
            whileTap="tap"
            whileHover="hover"
            variants={getVariantSize(size, varSmall, varMedium, varLarge)}
            sx={{
                display: 'inline-flex',
                ...sxWrap,
            }}
        >
            {children}
        </Box>
    );
}
