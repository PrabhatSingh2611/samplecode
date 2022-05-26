// TODO: Fix types to Theme (YD)
// @ts-nocheck
import { alpha, SxProps, Theme } from '@mui/material';
import { WPillProps } from '.';
import { ColorSchema } from '../../theme/palette';

export const generatePillStyles = (
  theme: Theme,
  { color, variant }: WPillProps
): SxProps<Theme> => {
  const isLight = theme.palette.mode === 'light';

  const styleFilled = (color: ColorSchema) => ({
    color: theme.palette[color].contrastText,
    backgroundColor: theme.palette[color].main,
  });

  const styleOutlined = (color: ColorSchema) => ({
    color: theme.palette[color].main,
    backgroundColor: 'transparent',
    border: `1px solid ${theme.palette[color].main}`,
  });

  const styleGhost = (color: ColorSchema) => ({
    color: theme.palette[color][isLight ? 'dark' : 'light'],
    backgroundColor: alpha(theme.palette[color].main, 0.16),
  });

  return {
    height: 22,
    minWidth: 22,
    lineHeight: 0,
    borderRadius: '6px',
    cursor: 'default',
    alignItems: 'center',
    whiteSpace: 'nowrap',
    display: 'inline-flex',
    justifyContent: 'center',
    padding: theme.spacing(0, 1),
    color: theme.palette.grey[800],
    fontSize: theme.typography.pxToRem(12),
    fontFamily: theme.typography.fontFamily,
    backgroundColor: theme.palette.grey[300],
    fontWeight: theme.typography.fontWeightBold,

    ...(color !== 'default'
      ? // TODO: fix types(YD)
        {
          ...(variant === 'filled' && { ...styleFilled(color as ColorSchema) }),
          ...(variant === 'outlined' && {
            ...styleOutlined(color as ColorSchema),
          }),
          ...(variant === 'ghost' && { ...styleGhost(color as ColorSchema) }),
        }
      : {
          ...(variant === 'outlined' && {
            backgroundColor: 'transparent',
            color: theme.palette.text.primary,
            border: `1px solid ${theme.palette.grey[500_32]}`,
          }),
          ...(variant === 'ghost' && {
            color: isLight
              ? theme.palette.text.secondary
              : theme.palette.common.white,
            backgroundColor: theme.palette.grey[500_16],
          }),
        }),
  };
};
