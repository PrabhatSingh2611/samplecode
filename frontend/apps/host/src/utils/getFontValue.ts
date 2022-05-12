export function pxToRem(value: number): string {
    return `${value / 16}rem`;
}

interface IResponsiveFontSizeResult {
    '@media (min-width:900px)': { fontSize: string };
    '@media (min-width:600px)': { fontSize: string };
    '@media (min-width:1200px)': { fontSize: string };
}

export function responsiveFontSizes({
    sm,
    md,
    lg,
}: {
    sm: number;
    md: number;
    lg: number;
}): IResponsiveFontSizeResult {
    return {
        '@media (min-width:600px)': {
            fontSize: pxToRem(sm),
        },
        '@media (min-width:900px)': {
            fontSize: pxToRem(md),
        },
        '@media (min-width:1200px)': {
            fontSize: pxToRem(lg),
        },
    };
}
