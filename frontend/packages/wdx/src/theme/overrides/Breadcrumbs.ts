// TODO: Fix types to Theme (AU)
// @ts-nocheck
import { Theme } from '@mui/material/styles';

export default function Breadcrumbs(theme: Theme) {
  return {
    MuiBreadcrumbs: {
      defaultProps: {
        separator: "•",
      },
      styleOverrides: {
        separator: {
          marginLeft: theme.spacing(2),
          marginRight: theme.spacing(2),
          color: theme.palette.text.disabled
        }
      }
    }
  };
}
