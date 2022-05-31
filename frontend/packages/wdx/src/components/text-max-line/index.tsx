import React, { forwardRef, ReactNode } from 'react';
import { Variant } from '@mui/material/styles/createTypography';
import { Typography, TypographyProps, LinkProps, Link } from '@mui/material';
import getFontValue from '../../utils/getFontValue';

type IProps = TypographyProps & LinkProps;

export interface WTextMaxLineProps extends IProps {
    line?: number;
    asLink?: boolean;
    persistent?: boolean;
    children: ReactNode;
    variant?: Variant;
}

const WTextMaxLine = forwardRef<HTMLAnchorElement, WTextMaxLineProps>(
    ({ asLink, variant = 'body1', line = 2, persistent = false, children, sx, ...other }, ref) => {
        const { lineHeight } = getFontValue(variant);
        const style = {
            overflow: 'hidden',
            textOverflow: 'ellipsis',
            display: '-webkit-box',
            WebkitLineClamp: line,
            WebkitBoxOrient: 'vertical',
            ...(persistent && {
                height: lineHeight * line,
            }),
            ...sx,
        } as const;

        if (asLink) {
            return (
                <Link color="inherit" ref={ref} variant={variant} sx={{ ...style }} {...other}>
                    {children}
                </Link>
            );
        }

        return (
            <Typography ref={ref} variant={variant} sx={{ ...style }} {...other}>
                {children}
            </Typography>
        );
    }
);

export default WTextMaxLine;
