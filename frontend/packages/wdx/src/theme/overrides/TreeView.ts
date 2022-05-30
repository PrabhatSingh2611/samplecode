import { Theme } from '@mui/material/styles';

interface ITreeView {
  MuiTreeItem: {
    styleOverrides: {
      label: {
        [p: string]: unknown;
      };
    };
  };
}

export default function TreeView(theme: Theme): ITreeView {
  return {
    MuiTreeItem: {
      styleOverrides: {
        label: { ...theme.typography.body2 },
      },
    },
  };
}
