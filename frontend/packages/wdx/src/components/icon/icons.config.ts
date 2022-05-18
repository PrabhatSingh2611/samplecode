import { TIcons } from './icons.type';
import { OverridableComponent } from '@mui/material/OverridableComponent';
import { SvgIconTypeMap } from '@mui/material/SvgIcon/SvgIcon';
import Abc from '@mui/icons-material/Abc';
import AcUnit from '@mui/icons-material/AcUnit';
import AccessAlarm from '@mui/icons-material/AccessAlarm';
import Close from '@mui/icons-material/Close';


type TIconComponent = OverridableComponent<SvgIconTypeMap<{}, "svg">> & { muiName: string; }

export const  iconsConfig:Record<TIcons, TIconComponent> = {
    'abc': Abc,
    'ac-unit': AcUnit,
    'access-alarm': AccessAlarm,
    'close': Close,
}