import React, { ReactNode } from 'react';

import { Box, BoxProps } from '@mui/material';
import { m, MotionProps } from 'framer-motion';

import { varContainer } from 'components/animate/variants';
import useResponsive from 'hooks/useResponsive';

type IBoxProps = BoxProps & MotionProps;

interface IProps extends IBoxProps {
    children: ReactNode;
    disableAnimatedMobile?: boolean;
}

export default function MotionViewport({
    children,
    disableAnimatedMobile = true,
    ...other
}: IProps): JSX.Element {
    const isDesktop = useResponsive('up', 'sm');

    if (!isDesktop && disableAnimatedMobile) {
        return <Box {...other}>{children}</Box>;
    }

    return (
        <Box
            component={m.div}
            initial="initial"
            whileInView="animate"
            viewport={{ once: true, amount: 0.3 }}
            variants={varContainer()}
            {...other}
        >
            {children}
        </Box>
    );
}
