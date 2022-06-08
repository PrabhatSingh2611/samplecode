import { Theme } from '@mui/material/styles';

import Accordion from './Accordion';
import Avatar from './Avatar';
import Backdrop from './Backdrop';
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
import LoadingButton from './LoadingButton';
import Menu from './Menu';
import Paper from './Paper';
import Popover from './Popover';
import Radio from './Radio';
import Slider from './Slider';
import Steper from './Stepper';
import Switch from './Switch';
import Table from './Table';
import Tabs from './Tabs';
import ToggleButton from './ToggleButton';
import Tooltip from './Tooltip';
import Typography from './Typography';
import Alert from './Alert';
import Select from './Select';
import Pagination from './Pagination';
import Skeleton from './Skeleton';
import Dialog from './Dialog';
import Drawer from './Drawer';
import TreeView from './TreeView';
import SvgIcon from './SvgIcon';
import Rating from './Rating';
import Timeline from './Timeline';

export default function ComponentsOverrides(theme: Theme) {
  return Object.assign(
    Accordion(theme),
    Alert(theme),
    Avatar(theme),
    Backdrop(theme),
    Badge(),
    Breadcrumbs(theme),
    Button(theme),
    ButtonGroup(theme),
    Card(theme),
    Checkbox(theme),
    Chip(theme),
    ControlLabel(theme),
    CssBaseline(),
    Dialog(theme),
    Drawer(theme),
    Fab(theme),
    Input(theme),
    Link(),
    List(theme),
    LoadingButton(),
    Menu(theme),
    Pagination(theme),
    Paper(theme),
    Popover(theme),
    Radio(theme),
    Rating(theme),
    Select(),
    Skeleton(theme),
    Slider(theme),
    Steper(theme),
    SvgIcon(),
    Switch(theme),
    Table(theme),
    Tabs(theme),
    Timeline(theme),
    ToggleButton(theme),
    Tooltip(theme),
    Typography(theme),
    TreeView(theme)
  );
}
