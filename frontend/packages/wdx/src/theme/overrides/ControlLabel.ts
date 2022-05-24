// TODO: Fix types to Theme
// @ts-nocheck
import { Theme } from "@mui/material";

export default function ControlLabel(theme: Theme):any {
  return {
    MuiFormControlLabel: {
      styleOverrides: {
        label: {
          ...theme.typography.body2
        }
      }
    },
    MuiFormHelperText: {
      styleOverrides: {
        root: {
          marginTop: theme.spacing(1)
        }
      }
    },
    MuiFormLabel: {
      styleOverrides: {
        root: {
          ...theme.typography.subtitle1,
          color: theme.palette.text.primary,
          marginBottom: theme.spacing(2)
        }
      }
    }
  };
}
