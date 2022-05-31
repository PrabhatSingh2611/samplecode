// TODO: Fix types to Theme (AU)
// @ts-nocheck
import { useTheme } from '@mui/material/styles';
import { Variant } from '@mui/material/styles/createTypography';
import useGetScreenWidthBreakpoint from '../hooks/useGetScreenWidthBreakpoint.hooks';

export interface IGetFontValueResult {
  fontSize: number;
  lineHeight: number;
  fontWeight: number;
  letterSpacing: number;
}

export default function getFontValue(variant: Variant): IGetFontValueResult {
  const theme = useTheme();
  const breakpoints = useGetScreenWidthBreakpoint();

  const key = theme.breakpoints.up(breakpoints === 'xl' ? 'lg' : breakpoints);

  const hasResponsive = ['h1', 'h2', 'h3', 'h4', 'h5', 'h6'].includes(variant);

  const font: any =
    hasResponsive && theme.typography[variant][key]
      ? theme.typography[variant][key]
      : theme.typography[variant];

  const fontSize = remToPx(font.fontSize);
  const lineHeight = Number(theme.typography[variant].lineHeight) * fontSize;
  const { fontWeight, letterSpacing } = theme.typography[variant];

  return { fontSize, lineHeight, fontWeight, letterSpacing };
}

export function remToPx(value: string) {
  return Math.round(parseFloat(value) * 16);
}

export function pxToRem(value: number) {
  return `${value / 16}rem`;
}

export function getResponsiveFontSizes({ sm, md, lg }: { sm: number; md: number; lg: number }) {
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