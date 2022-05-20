import { Theme } from '@mui/material/styles';

import Accordion from './Accordion';
import Avatar from './Avatar';
import Badge from './Badge';
import Breadcrumbs from './Breadcrumbs';
import Button from './Button';
import ButtonGroup from './ButtonGroup';
import Card from './Card';
import Checkbox from './Checkbox';
import Chip from './Chip';
import ControlLabel from './ControlLabel';
import CssBaseline from './CssBaseline';
import Fab from './Fab';
import Input from './Input';
import Link from './Link';
import List from './List';
import Menu from './Menu';
import Paper from './Paper';
import Radio from './Radio';
import Slider from './Slider';
import Steper from './Stepper';
import Switch from './Switch';
import Typography from './Typography';
import Tooltip from './Tooltip';
import Table from './Table';
import Alert from './Alert';
import Select from './Select';

export default function ComponentsOverrides(theme: Theme) {
  return Object.assign(
    Accordion(theme),
    Alert(theme),
    Avatar(theme),
    Badge(),
    Breadcrumbs(theme),
    Button(theme),
    ButtonGroup(theme),
    Card(theme),
    Checkbox(theme),
    Chip(theme),
    ControlLabel(theme),
    CssBaseline(),
    Fab(theme),
    Input(theme),
    Link(),
    List(theme),
    Menu(theme),
    Paper(theme),
    Radio(theme),
    Select(),
    Slider(theme),
    Steper(theme),
    Switch(theme),
    Typography(theme),
    Table(theme),
    Tooltip(theme)
  );
}
