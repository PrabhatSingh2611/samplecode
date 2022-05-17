// TODO: Fix types to Theme (YD)
// @ts-nocheck
import { Theme } from '@mui/material/styles';

export default function Radio(theme: Theme):any {
  return {
    MuiRadio: {
      styleOverrides: {
        root: {
          padding: theme.spacing(1),
          svg: {
            fontSize: 24,
            '&[font-size=small]': {
              fontSize: 20
            }
          }
        }
      }
    }
  };
}
