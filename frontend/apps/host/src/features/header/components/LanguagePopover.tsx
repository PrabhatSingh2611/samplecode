import React, { useState } from 'react';

import { MenuItem, Stack } from '@mui/material';
import { Localization } from '@mui/material/locale';

import Image from 'components/Image';
import MenuPopover from 'components/MenuPopover';
import { IconButtonAnimate } from 'components/animate';
import { allLangs } from 'theme/config';

interface ILangOption {
    label: string;
    value: string;
    systemValue: Localization;
    icon: string;
}

export default function LanguagePopover(): JSX.Element {
    const [open, setOpen] = useState<HTMLElement | null>(null);

    const handleOpen = (event: React.MouseEvent<HTMLElement>): void => {
        setOpen(event.currentTarget);
    };

    const handleClose = (): void => {
        setOpen(null);
    };

    return (
        <>
            <IconButtonAnimate
                onClick={handleOpen}
                sx={{
                    width: 40,
                    height: 40,
                    ...(open && { bgcolor: 'action.selected' }),
                }}
            >
                <Image disabledEffect src={allLangs[0].icon} alt={allLangs[0].label} />
            </IconButtonAnimate>

            <MenuPopover
                open={Boolean(open)}
                anchorEl={open}
                onClose={handleClose}
                sx={{
                    width: 180,
                    mt: 1.5,
                    ml: 0.75,
                    '& .MuiMenuItem-root': { px: 1, borderRadius: 0.75, typography: 'body2' },
                }}
            >
                <Stack spacing={0.75}>
                    {allLangs.map((option: ILangOption) => (
                        <MenuItem
                            key={option.value}
                            selected={option.value === allLangs[0].value}
                            onClick={handleClose}
                        >
                            <Image
                                disabledEffect
                                alt={option.label}
                                src={option.icon}
                                sx={{ width: 28, mr: 2 }}
                            />

                            {option.label}
                        </MenuItem>
                    ))}
                </Stack>
            </MenuPopover>
        </>
    );
}
