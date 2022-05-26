import { TIcons } from './icons.type';
import { OverridableComponent } from '@mui/material/OverridableComponent';
import { SvgIconTypeMap } from '@mui/material/SvgIcon/SvgIcon';
import Abc from '@mui/icons-material/Abc';
import AcUnit from '@mui/icons-material/AcUnit';
import AccessAlarm from '@mui/icons-material/AccessAlarm';
import Close from '@mui/icons-material/Close';
import DownloadIcon from '@mui/icons-material/Download';
import EditIcon from '@mui/icons-material/Edit';
import VisibilityIcon from '@mui/icons-material/Visibility';
import DeleteIcon from '@mui/icons-material/Delete';
import MoreHoriz from '@mui/icons-material/MoreHoriz';
import MoreVertical from '@mui/icons-material/MoreVert';
import ArrowRightAltIcon from '@mui/icons-material/ArrowRightAlt';
import AddIcon from '@mui/icons-material/Add';
import InsertDriveFileIcon from '@mui/icons-material/InsertDriveFile';
import Search from '@mui/icons-material/Search';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
type TIconComponent = OverridableComponent<SvgIconTypeMap<{}, 'svg'>> & {
  muiName: string;
};

export const iconsConfig: Record<TIcons, TIconComponent> = {
  abc: Abc,
  'ac-unit': AcUnit,
  'access-alarm': AccessAlarm,
  close: Close,
  download: DownloadIcon,
  edit: EditIcon,
  'expand-less': ExpandLess,
  'expand-more': ExpandMore,
  visibility: VisibilityIcon,
  delete: DeleteIcon,
  'more-horizontal': MoreHoriz,
  'arrow-right-alt': ArrowRightAltIcon,
  add: AddIcon,
  'insert-drive-file': InsertDriveFileIcon,
  'more-vertical': MoreVertical,
  search: Search,
};
