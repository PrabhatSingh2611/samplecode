import { Theme } from '@mui/material/styles';

import Accordion from 'theme/overrides/Accordion';
import Alert from 'theme/overrides/Alert';
import Autocomplete from 'theme/overrides/Autocomplete';
import Avatar from 'theme/overrides/Avatar';
import Backdrop from 'theme/overrides/Backdrop';
import Badge from 'theme/overrides/Badge';
import Breadcrumbs from 'theme/overrides/Breadcrumbs';
import Button from 'theme/overrides/Button';
import ButtonGroup from 'theme/overrides/ButtonGroup';
import Card from 'theme/overrides/Card';
import Checkbox from 'theme/overrides/Checkbox';
import Chip from 'theme/overrides/Chip';
import ControlLabel from 'theme/overrides/ControlLabel';
import CssBaseline from 'theme/overrides/CssBaseline';
import DataGrid from 'theme/overrides/DataGrid';
import Dialog from 'theme/overrides/Dialog';
import Drawer from 'theme/overrides/Drawer';
import Fab from 'theme/overrides/Fab';
import Input from 'theme/overrides/Input';
import Link from 'theme/overrides/Link';
import Lists from 'theme/overrides/List';
import LoadingButton from 'theme/overrides/LoadingButton';
import Menu from 'theme/overrides/Menu';
import Pagination from 'theme/overrides/Pagination';
import Paper from 'theme/overrides/Paper';
import Popover from 'theme/overrides/Popover';
import Progress from 'theme/overrides/Progress';
import Radio from 'theme/overrides/Radio';
import Rating from 'theme/overrides/Rating';
import Select from 'theme/overrides/Select';
import Skeleton from 'theme/overrides/Skeleton';
import Slider from 'theme/overrides/Slider';
import Stepper from 'theme/overrides/Stepper';
import SvgIcon from 'theme/overrides/SvgIcon';
import Switch from 'theme/overrides/Switch';
import Table from 'theme/overrides/Table';
import Tabs from 'theme/overrides/Tabs';
import Timeline from 'theme/overrides/Timeline';
import ToggleButton from 'theme/overrides/ToggleButton';
import Tooltip from 'theme/overrides/Tooltip';
import TreeView from 'theme/overrides/TreeView';
import Typography from 'theme/overrides/Typography';

export default function ComponentsOverrides(theme: Theme) {
    return Object.assign(
        Fab(theme),
        Tabs(theme),
        Chip(theme),
        Card(theme),
        Menu(theme),
        Link(),
        Input(theme),
        Radio(theme),
        Badge(),
        Lists(theme),
        Table(theme),
        Paper(theme),
        Alert(theme),
        Switch(theme),
        Select(),
        Button(theme),
        Rating(theme),
        Dialog(theme),
        Avatar(theme),
        Slider(theme),
        Drawer(theme),
        Stepper(theme),
        Tooltip(theme),
        Popover(theme),
        SvgIcon(),
        Checkbox(theme),
        DataGrid(theme),
        Skeleton(theme),
        Timeline(theme),
        TreeView(theme),
        Backdrop(theme),
        Progress(theme),
        Accordion(theme),
        Typography(theme),
        Pagination(theme),
        ButtonGroup(theme),
        Breadcrumbs(theme),
        CssBaseline(),
        Autocomplete(theme),
        ControlLabel(theme),
        ToggleButton(theme),
        LoadingButton(),
    );
}
