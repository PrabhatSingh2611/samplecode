import { Theme } from '@mui/material/styles';

// TODO: Fix type, if we return two object or more (AU)
export default function Avatar(theme: Theme): any {
  return {
    MuiAvatar: {
      styleOverrides: {
        colorDefault: {
          color: theme.palette.text.secondary,
          backgroundColor: theme.palette.grey[400]
        }
      }
    },
    MuiAvatarGroup: {
      styleOverrides: {
        avatar: {
          fontSize: 16,
          fontWeight: theme.typography.fontWeightMedium,
          '&:first-of-type': {
            fontSize: 14,
            color: theme.palette.primary.main,
            backgroundColor: theme.palette.primary.lighter
          }
        }
      }
    }
  };
}
