import React, { useState, useEffect } from 'react';

import { Stack, Divider, Backdrop, Typography, IconButton } from '@mui/material';
import { alpha, styled } from '@mui/material/styles';
import { AnimatePresence, m } from 'framer-motion';

import Iconify from 'components/Iconify';
import Scrollbar from 'components/Scrollbar';
import { varFade } from 'components/animate';
import SettingColorPresets from 'components/settings/drawer/SettingColorPresets';
import SettingContrast from 'components/settings/drawer/SettingContrast';
import SettingDirection from 'components/settings/drawer/SettingDirection';
import SettingFullscreen from 'components/settings/drawer/SettingFullscreen';
import SettingLayout from 'components/settings/drawer/SettingLayout';
import SettingMode from 'components/settings/drawer/SettingMode';
import SettingStretch from 'components/settings/drawer/SettingStretch';
import ToggleButton from 'components/settings/drawer/ToggleButton';
import useSettings from 'hooks/useSettings';
import { NAVBAR, defaultSettings } from 'theme/config';
import cssStyles from 'utils/cssStyles';

const RootStyle = styled(m.div)(({ theme }) => ({
    ...cssStyles(theme).bgBlur({ color: theme.palette.background.paper, opacity: 0.92 }),
    top: 0,
    right: 0,
    bottom: 0,
    display: 'flex',
    position: 'fixed',
    overflow: 'hidden',
    width: NAVBAR.BASE_WIDTH,
    flexDirection: 'column',
    margin: theme.spacing(2),
    paddingBottom: theme.spacing(3),
    zIndex: theme.zIndex.drawer + 3,
    borderRadius: Number(theme.shape.borderRadius) * 1.5,
    boxShadow: `-24px 12px 32px -4px ${alpha(
        theme.palette.mode === 'light' ? theme.palette.grey[500] : theme.palette.common.black,
        0.16,
    )}`,
}));

export default function SettingsDrawer(): JSX.Element {
    const {
        themeMode,
        themeLayout,
        themeStretch,
        themeContrast,
        themeDirection,
        themeColorPresets,
        onResetSetting,
    } = useSettings();

    const [open, setOpen] = useState(false);

    const notDefault =
        themeMode !== defaultSettings.themeMode ||
        themeLayout !== defaultSettings.themeLayout ||
        themeStretch !== defaultSettings.themeStretch ||
        themeContrast !== defaultSettings.themeContrast ||
        themeDirection !== defaultSettings.themeDirection ||
        themeColorPresets !== defaultSettings.themeColorPresets;

    const varSidebar =
        themeDirection !== 'rtl'
            ? varFade({
                  distance: NAVBAR.BASE_WIDTH,
                  durationIn: 0.32,
                  durationOut: 0.32,
              }).inRight
            : varFade({
                  distance: NAVBAR.BASE_WIDTH,
                  durationIn: 0.32,
                  durationOut: 0.32,
              }).inLeft;

    useEffect(() => {
        if (open) {
            document.body.style.overflow = 'hidden';
        } else {
            document.body.style.overflow = '';
        }
    }, [open]);

    const handleToggle = (): void => {
        setOpen((prev) => !prev);
    };

    const handleClose = (): void => {
        setOpen(false);
    };

    return (
        <>
            <Backdrop
                open={open}
                onClick={handleClose}
                sx={{ zIndex: (theme) => theme.zIndex.drawer + 1, background: 'transparent' }}
            />

            {!open && <ToggleButton open={open} notDefault={notDefault} onToggle={handleToggle} />}

            <AnimatePresence>
                {open && (
                    <>
                        <RootStyle {...varSidebar}>
                            <Stack
                                direction="row"
                                alignItems="center"
                                justifyContent="space-between"
                                sx={{ py: 2, pr: 1, pl: 2.5 }}
                            >
                                <Typography variant="subtitle1" sx={{ flexGrow: 1 }}>
                                    Settings
                                </Typography>

                                <IconButton onClick={onResetSetting}>
                                    <Iconify icon={'ic:round-refresh'} width={20} height={20} />
                                </IconButton>

                                <IconButton onClick={handleClose}>
                                    <Iconify icon={'eva:close-fill'} width={20} height={20} />
                                </IconButton>
                            </Stack>

                            <Divider sx={{ borderStyle: 'dashed' }} />

                            <Scrollbar sx={{ flexGrow: 1 }}>
                                <Stack spacing={3} sx={{ p: 3 }}>
                                    <Stack spacing={1.5}>
                                        <Typography variant="subtitle2">Mode</Typography>
                                        <SettingMode />
                                    </Stack>

                                    <Stack spacing={1.5}>
                                        <Typography variant="subtitle2">Contrast</Typography>
                                        <SettingContrast />
                                    </Stack>

                                    <Stack spacing={1.5}>
                                        <Typography variant="subtitle2">Direction</Typography>
                                        <SettingDirection />
                                    </Stack>

                                    <Stack spacing={1.5}>
                                        <Typography variant="subtitle2">Layout</Typography>
                                        <SettingLayout />
                                    </Stack>

                                    <Stack spacing={1.5}>
                                        <Typography variant="subtitle2">Presets</Typography>
                                        <SettingColorPresets />
                                    </Stack>

                                    <Stack spacing={1.5}>
                                        <Typography variant="subtitle2">Stretch</Typography>
                                        <SettingStretch />
                                    </Stack>

                                    <SettingFullscreen />
                                </Stack>
                            </Scrollbar>
                        </RootStyle>
                    </>
                )}
            </AnimatePresence>
        </>
    );
}
