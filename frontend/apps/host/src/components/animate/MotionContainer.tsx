import React from 'react';

import { Box, BoxProps } from '@mui/material';
import { m, MotionProps } from 'framer-motion';

import { varContainer } from 'components/animate/variants';

type IBoxProps = BoxProps & MotionProps;

interface IProps extends IBoxProps {
    animate?: boolean;
    action?: boolean;
}

export default function MotionContainer({
    animate,
    action = false,
    children,
    ...other
}: IProps): JSX.Element {
    if (action) {
        return (
            <Box
                component={m.div}
                initial={false}
                animate={animate ? 'animate' : 'exit'}
                variants={varContainer()}
                {...other}
            >
                {children}
            </Box>
        );
    }

    return (
        <Box
            component={m.div}
            initial="initial"
            animate="animate"
            exit="exit"
            variants={varContainer()}
            {...other}
        >
            {children}
        </Box>
    );
}
