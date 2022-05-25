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
  visibility: VisibilityIcon,
  delete: DeleteIcon,
};
