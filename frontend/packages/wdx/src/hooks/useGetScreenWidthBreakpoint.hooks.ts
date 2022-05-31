// TODO: Fix types to Theme (AU)
// @ts-nocheck

import { useTheme } from '@mui/material/styles';
import useResponsive from './useResponsive';

export default function useGetScreenWidthBreakpoint() {
  const theme = useTheme();
  const keys = [...theme.breakpoints.keys].reverse();
  return (
    // @ts-ignore not sure what is this
    keys.reduce((output, key) => {
      // eslint-disable-next-line react-hooks/rules-of-hooks
      const matches = useResponsive('up', key);
      return !output && matches ? key : output;
    }, null) || 'xs'
  );
}