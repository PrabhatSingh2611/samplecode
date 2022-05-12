import React from 'react';

import { Box, BoxProps } from '@mui/material';
import { m, MotionProps } from 'framer-motion';

import { varFade } from 'components/animate/variants';

type Props = BoxProps & MotionProps;

interface ITextAnimateProps extends Props {
    text: string;
}

export default function TextAnimate({
    text,
    variants,
    sx,
    ...other
}: ITextAnimateProps): JSX.Element {
    return (
        <Box
            component={m.h1}
            sx={{
                display: 'inline-flex',
                overflow: 'hidden',
                typography: 'h1',
                ...sx,
            }}
            {...other}
        >
            {text.split('').map((letter, index) => (
                <m.span key={index} variants={variants || varFade().inUp}>
                    {letter}
                </m.span>
            ))}
        </Box>
    );
}
