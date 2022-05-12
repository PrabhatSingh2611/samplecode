import React from 'react';

import { Box, Grid, RadioGroup, CardActionArea } from '@mui/material';
import { alpha, styled } from '@mui/material/styles';

import BoxMask from 'components/settings/drawer/BoxMask';
import useSettings from 'hooks/useSettings';

const BoxStyle = styled(CardActionArea)(({ theme }) => ({
    height: 48,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    color: theme.palette.text.disabled,
    border: `solid 1px ${theme.palette.grey[500_12]}`,
    borderRadius: Number(theme.shape.borderRadius) * 1.25,
}));

export default function SettingColorPresets(): JSX.Element {
    const { themeColorPresets, onChangeColor, colorOption } = useSettings();

    return (
        <RadioGroup name="themeColorPresets" value={themeColorPresets} onChange={onChangeColor}>
            <Grid dir="ltr" container spacing={1.5}>
                {colorOption.map((color) => {
                    const colorName = color.name;
                    const colorValue = color.value;
                    const isSelected = themeColorPresets === colorName;

                    return (
                        <Grid key={colorName} item xs={4} sx={{ pt: 2, pl: 2 }}>
                            <BoxStyle
                                sx={{
                                    ...(isSelected && {
                                        border: `solid 2px ${colorValue}`,
                                        boxShadow: `inset 0 4px 8px 0 ${alpha(colorValue, 0.24)}`,
                                        bgcolor: alpha(colorValue, 0.08),
                                    }),
                                }}
                            >
                                <Box
                                    sx={{
                                        width: 24,
                                        height: 14,
                                        borderRadius: '50%',
                                        transform: 'rotate(-45deg)',
                                        transition: (theme) =>
                                            theme.transitions.create('all', {
                                                duration: theme.transitions.duration.shorter,
                                                easing: theme.transitions.easing.easeInOut,
                                            }),
                                        bgcolor: colorValue,
                                        ...(isSelected && { transform: 'none' }),
                                    }}
                                />

                                <BoxMask value={colorName} />
                            </BoxStyle>
                        </Grid>
                    );
                })}
            </Grid>
        </RadioGroup>
    );
}
