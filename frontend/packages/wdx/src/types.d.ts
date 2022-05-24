import { Theme } from '@mui/material';
import { SxProps } from '@mui/material/styles';

interface ErrorConstructor {
    captureStackTrace(thisArg: any, func: any): void
}

export interface WSxProps extends SxProps<Theme> {}