import { Theme } from '@mui/material/styles';

import Accordion from './Accordion';
import Avatar from './Avatar';
import Badge from './Badge';
import Button from './Button';
import Card from './Card';
import Chip from './Chip';
import CssBaseline from './CssBaseline';
import Input from './Input';
import Menu from './Menu';
import Paper from './Paper';
import Steper from './Stepper';
import Typography from './Typography';

export default function ComponentsOverrides(theme: Theme) {
  return Object.assign(
    Accordion(theme),
    Avatar(theme),
    Badge(),
    Button(theme),
    Card(theme),
    Chip(theme),
    CssBaseline(),
    Input(theme),
    Menu(theme),
    Paper(theme),
    Steper(theme),
    Typography(theme),
  );
}
