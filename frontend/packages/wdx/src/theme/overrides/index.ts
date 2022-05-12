import { Theme } from '@mui/material/styles';

import Avatar from './Avatar';
import Badge from './Badge';
import Button from './Button';
import CssBaseline from './CssBaseline';

export default function ComponentsOverrides(theme: Theme) {
  return Object.assign(
    Avatar(theme),
    Badge(),
    Button(theme),
    CssBaseline(),
  );
}
