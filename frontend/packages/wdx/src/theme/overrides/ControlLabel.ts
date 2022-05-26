// TODO: Fix types to Theme
// @ts-nocheck
import { Theme } from "@mui/material";
import FormLabel, { FormLabelProps } from '@mui/material/FormLabel';


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
          color: theme.palette.text.disabled
        }
      },
      variants: [
        {
          props: { variant: 'bold' },
          style: {
            ...theme.typography.subtitle1,
            color: theme.palette.text.primary,
            marginBottom: theme.spacing(2)
          },
        },
      ]
    }
  };
}
