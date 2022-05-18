import React, { useState } from 'react';

import { Input, Slide, Button, InputAdornment, ClickAwayListener } from '@mui/material';
import { styled } from '@mui/material/styles';

import Iconify from 'components/Iconify';
import { IconButtonAnimate } from 'components/animate';
import cssStyles from 'utils/cssStyles';

const APPBAR_MOBILE = 64;
const APPBAR_DESKTOP = 92;

const SearchbarStyle = styled('div')(({ theme }) => ({
    ...cssStyles(theme).bgBlur(),
    top: 0,
    left: 0,
    zIndex: 99,
    width: '100%',
    display: 'flex',
    position: 'absolute',
    alignItems: 'center',
    height: APPBAR_MOBILE,
    padding: theme.spacing(0, 3),
    boxShadow: theme.customShadows.z8,
    [theme.breakpoints.up('md')]: {
        height: APPBAR_DESKTOP,
        padding: theme.spacing(0, 5),
    },
}));

export default function Searchbar(): JSX.Element {
    const [isOpen, setOpen] = useState(false);

    const handleOpen = (): void => {
        setOpen((prev) => !prev);
    };

    const handleClose = (): void => {
        setOpen(false);
    };

    return (
        <ClickAwayListener onClickAway={handleClose}>
            <div>
                {!isOpen && (
                    <IconButtonAnimate onClick={handleOpen}>
                        <Iconify icon={'eva:search-fill'} width={20} height={20} />
                    </IconButtonAnimate>
                )}

                <Slide direction="down" in={isOpen} mountOnEnter unmountOnExit>
                    <SearchbarStyle>
                        <Input
                            autoFocus
                            fullWidth
                            disableUnderline
                            placeholder="Search…"
                            startAdornment={
                                <InputAdornment position="start">
                                    <Iconify
                                        icon={'eva:search-fill'}
                                        sx={{ width: 20, height: 20, color: 'text.disabled' }}
                                    />
                                </InputAdornment>
                            }
                            sx={{ mr: 1, fontWeight: 'fontWeightBold' }}
                        />
                        <Button variant="contained" onClick={handleClose}>
                            Search
                        </Button>
                    </SearchbarStyle>
                </Slide>
            </div>
        </ClickAwayListener>
    );
}
