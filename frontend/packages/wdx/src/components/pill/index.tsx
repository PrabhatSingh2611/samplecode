import { useTheme } from "@mui/material";
import React  from "react";
import { ColorSchema } from "../../theme/palette";

import WBox, { WBoxProps } from "../box";
import { generatePillStyles } from "./pills.styles";

export type LabelColor = ColorSchema | 'default';

type LabelVariant = 'filled' | 'outlined' | 'ghost';

export interface WPillProps extends WBoxProps {
    startIcon?: React.ReactElement | null;
    endIcon?: React.ReactElement | null;
    color?: LabelColor;
    variant?: LabelVariant;
}

const baseIconStyles = {
    width: 16,
    height: 16,
    '& svg, img': { width: 1, height: 1, objectFit: 'cover' },
  };

function WPill({
        color = 'default',
        variant = 'ghost',
        startIcon,
        endIcon,
        children,
        sx,
        ...props
    }: WPillProps):JSX.Element {
    const theme = useTheme();

    const pillStyles = generatePillStyles(theme, {color, variant});

    return (
        <WBox
            // TODO: fix types (YD)
            // @ts-ignore
            sx={{
                ...(startIcon && { pl: 0.75 }),
                ...(endIcon && { pr: 0.75 }),
                ...pillStyles,
                ...sx,
            }}
            {...props}
        >
            {startIcon && <WBox sx={{ mr: 0.75, ...baseIconStyles }}>{startIcon}</WBox>}
            {children}
            {endIcon && <WBox sx={{ ml: 0.75, ...baseIconStyles }}>{endIcon}</WBox>}
        </WBox>
    )
}

export default WPill;