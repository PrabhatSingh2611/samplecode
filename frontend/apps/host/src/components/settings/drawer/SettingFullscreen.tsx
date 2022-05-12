import React, { useState } from 'react';

import { Button } from '@mui/material';
import { alpha } from '@mui/material/styles';

import Iconify from 'components/Iconify';

export default function SettingFullscreen(): JSX.Element {
    const [fullscreen, setFullscreen] = useState(false);

    const toggleFullScreen = (): void => {
        if (!document.fullscreenElement) {
            document.documentElement.requestFullscreen();
            setFullscreen(true);
            // Actually can be situation when document.exitFullscreen can not exist
            // eslint-disable-next-line @typescript-eslint/no-unnecessary-condition
        } else if (document.exitFullscreen) {
            document.exitFullscreen();
            setFullscreen(false);
        }
    };

    return (
        <Button
            fullWidth
            size="large"
            variant="outlined"
            color={fullscreen ? 'primary' : 'inherit'}
            startIcon={
                <Iconify icon={fullscreen ? 'ic:round-fullscreen-exit' : 'ic:round-fullscreen'} />
            }
            onClick={toggleFullScreen}
            sx={{
                fontSize: 14,
                ...(fullscreen && {
                    bgcolor: (theme) =>
                        alpha(theme.palette.primary.main, theme.palette.action.selectedOpacity),
                }),
            }}
        >
            {fullscreen ? 'Exit Fullscreen' : 'Fullscreen'}
        </Button>
    );
}
