import { TIcons } from './icons.type';
import { OverridableComponent } from '@mui/material/OverridableComponent';
import { SvgIconTypeMap } from '@mui/material/SvgIcon/SvgIcon';
import Abc from '@mui/icons-material/Abc';
import AccessAlarm from '@mui/icons-material/AccessAlarm';
import AcUnit from '@mui/icons-material/AcUnit';
import AddIcon from '@mui/icons-material/Add';
import Apps from '@mui/icons-material/Apps';
import ArrowBackIosNew from '@mui/icons-material/ArrowBackIosNew';
import ArrowForwardIos from '@mui/icons-material/ArrowForwardIos';
import ArrowRightAltIcon from '@mui/icons-material/ArrowRightAlt';
import Badge from '@mui/icons-material/Badge';
import Business from '@mui/icons-material/Business';
import Cable from '@mui/icons-material/Cable';
import ChairAlt from '@mui/icons-material/ChairAlt';
import Close from '@mui/icons-material/Close';
import Computer from '@mui/icons-material/Computer';
import ConnectWithoutContact from '@mui/icons-material/ConnectWithoutContact';
import DeleteIcon from '@mui/icons-material/Delete';
import DesktopMac from '@mui/icons-material/DesktopMac';
import DevicesOther from '@mui/icons-material/DevicesOther';
import DirectionsCar from '@mui/icons-material/DirectionsCar';
import DownloadIcon from '@mui/icons-material/Download';
import Edit from '@mui/icons-material/Edit';
import EmojiObjects from '@mui/icons-material/EmojiObjects';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import Headphones from '@mui/icons-material/Headphones';
import InsertDriveFileIcon from '@mui/icons-material/InsertDriveFile';
import Keyboard from '@mui/icons-material/Keyboard';
import KeyboardArrowDown from '@mui/icons-material/KeyboardArrowDown';
import LaptopMac from '@mui/icons-material/LaptopMac';
import Memory from '@mui/icons-material/Memory';
import MenuBook from '@mui/icons-material/MenuBook';
import Monitor from '@mui/icons-material/Monitor';
import MoreHoriz from '@mui/icons-material/MoreHoriz';
import MoreVertical from '@mui/icons-material/MoreVert';
import Mouse from '@mui/icons-material/Mouse';
import NaturePeople from '@mui/icons-material/NaturePeople';
import People from '@mui/icons-material/People';
import PeopleAlt from '@mui/icons-material/PeopleAlt';
import PhoneIphone from '@mui/icons-material/PhoneIphone';
import PhotoCamera from '@mui/icons-material/PhotoCamera';
import Policy from '@mui/icons-material/Policy';
import Power from '@mui/icons-material/Power';
import Print from '@mui/icons-material/Print';
import Router from '@mui/icons-material/Router';
import Save from '@mui/icons-material/Save';
import SdStorage from '@mui/icons-material/SdStorage';
import Search from '@mui/icons-material/Search';
import Settings from '@mui/icons-material/Settings';
import SettingsInputHdmi from '@mui/icons-material/SettingsInputHdmi';
import Storage from '@mui/icons-material/Storage';
import Tablet from '@mui/icons-material/Tablet';
import TabletMac from '@mui/icons-material/TabletMac';
import VisibilityIcon from '@mui/icons-material/Visibility';
import Undo from '@mui/icons-material/Undo';
import Redo from '@mui/icons-material/Redo';

type TIconComponent = OverridableComponent<SvgIconTypeMap<{}, 'svg'>> & {
  muiName: string;
};

export const iconsConfig: Record<TIcons, TIconComponent> = {
  abc: Abc,
  'ac-unit': AcUnit,
  'access-alarm': AccessAlarm,
  add: AddIcon,
  apps: Apps,
  'arrow-right-alt': ArrowRightAltIcon,
  badge: Badge,
  business: Business,
  cable: Cable,
  'chair-alt': ChairAlt,
  'chevron-down': KeyboardArrowDown,
  'chevron-left': ArrowBackIosNew,
  'chevron-right': ArrowForwardIos,
  close: Close,
  computer: Computer,
  'connect-without-contact': ConnectWithoutContact,
  delete: DeleteIcon,
  'desktop-mac': DesktopMac,
  'devices-other': DevicesOther,
  'directions-car': DirectionsCar,
  download: DownloadIcon,
  edit: Edit,
  'emoji-objects': EmojiObjects,
  'expand-less': ExpandLess,
  'expand-more': ExpandMore,
  headphones: Headphones,
  'insert-drive-file': InsertDriveFileIcon,
  keyboard: Keyboard,
  'laptop-mac': LaptopMac,
  memory: Memory,
  'menu-book': MenuBook,
  monitor: Monitor,
  'more-horizontal': MoreHoriz,
  'more-vertical': MoreVertical,
  mouse: Mouse,
  'nature-people': NaturePeople,
  people: People,
  'people-alt': PeopleAlt,
  'phone-iphone': PhoneIphone,
  'photo-camera': PhotoCamera,
  policy: Policy,
  power: Power,
  print: Print,
  router: Router,
  save: Save,
  'sd-storage': SdStorage,
  search: Search,
  settings: Settings,
  'settings-input-hdmi': SettingsInputHdmi,
  storage: Storage,
  tablet: Tablet,
  'tablet-mac': TabletMac,
  visibility: VisibilityIcon,
  undo: Undo,
  redo: Redo,
};
