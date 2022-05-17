import { Theme } from '@mui/material/styles';

import Accordion from './Accordion';
import Avatar from './Avatar';
import Badge from './Badge';
import Button from './Button';
import Card from './Card';
import Checkbox from './Checkbox';
import Chip from './Chip';
import ControlLabel from './ControlLabel';
import CssBaseline from './CssBaseline';
import Input from './Input';
import Menu from './Menu';
import Paper from './Paper';
import Radio from './Radio';
import Slider from './Slider';
import Steper from './Stepper';
import Switch from './Switch';
import Typography from './Typography';

export default function ComponentsOverrides(theme: Theme) {
  return Object.assign(
    Accordion(theme),
    Avatar(theme),
    Badge(),
    Button(theme),
    Card(theme),
    Checkbox(theme),
    Chip(theme),
    ControlLabel(theme),
    CssBaseline(),
    Input(theme),
    Menu(theme),
    Paper(theme),
    Radio(theme),
    Slider(theme),
    Steper(theme),
    Switch(theme),
    Typography(theme),
  );
}
