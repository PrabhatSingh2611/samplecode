import React from 'react';

import { Dialog, Box, Paper, DialogProps } from '@mui/material';
import { m, AnimatePresence } from 'framer-motion';

import { varFade } from 'components/animate/variants';

interface IProps extends DialogProps {
    variants?: Record<string, unknown>;
    onClose?: VoidFunction;
}

export default function DialogAnimate({
    open = false,
    variants,
    onClose,
    children,
    sx,
    ...other
}: IProps): JSX.Element {
    return (
        <AnimatePresence>
            {open && (
                <Dialog
                    fullWidth
                    maxWidth="xs"
                    open={open}
                    onClose={onClose}
                    PaperComponent={(props): JSX.Element => (
                        <Box
                            component={m.div}
                            {...(variants ||
                                varFade({
                                    distance: 120,
                                    durationIn: 0.32,
                                    durationOut: 0.24,
                                    easeIn: 'easeInOut',
                                }).inUp)}
                            sx={{
                                display: 'flex',
                                justifyContent: 'center',
                                alignItems: 'center',
                                width: '100%',
                                height: '100%',
                            }}
                        >
                            <Box
                                onClick={onClose}
                                sx={{ position: 'fixed', width: '100%', height: '100%' }}
                            />
                            <Paper sx={sx} {...props}>
                                {props.children}
                            </Paper>
                        </Box>
                    )}
                    {...other}
                >
                    {children}
                </Dialog>
            )}
        </AnimatePresence>
    );
}
