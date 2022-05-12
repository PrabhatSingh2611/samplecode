import React from 'react';
import { LazyLoadImage, LazyLoadImageProps } from 'react-lazy-load-image-component';

import { Box, BoxProps, SxProps } from '@mui/material';
import { Theme } from '@mui/material/styles';

type ImageRatio = '4/3' | '3/4' | '6/4' | '4/6' | '16/9' | '9/16' | '21/9' | '9/21' | '1/1';

type IBoxProps = BoxProps & LazyLoadImageProps;

interface IProps extends IBoxProps {
    sx?: SxProps<Theme>;
    ratio?: ImageRatio;
    disabledEffect?: boolean;
}

export default function Image({
    ratio,
    disabledEffect = false,
    effect = 'blur',
    sx,
    ...other
}: IProps): JSX.Element {
    if (ratio) {
        return (
            <Box
                component="span"
                sx={{
                    position: 'relative',
                    display: 'block',
                    width: 1,
                    pt: getRatio(ratio),
                    lineHeight: 0,
                    overflow: 'hidden',
                    '& .wrapper': {
                        position: 'absolute',
                        top: 0,
                        left: 0,
                        right: 0,
                        bottom: 0,
                        lineHeight: 0,
                        backgroundSize: 'cover !important',
                    },
                    ...sx,
                }}
            >
                <Box
                    component={LazyLoadImage}
                    wrapperClassName="wrapper"
                    effect={disabledEffect ? undefined : effect}
                    placeholderSrc="/assets/placeholder.svg"
                    sx={{ width: 1, height: 1, objectFit: 'cover' }}
                    {...other}
                />
            </Box>
        );
    }

    return (
        <Box
            component="span"
            sx={{
                display: 'block',
                lineHeight: 0,
                overflow: 'hidden',
                '& .wrapper': { width: 1, height: 1, backgroundSize: 'cover !important' },
                ...sx,
            }}
        >
            <Box
                component={LazyLoadImage}
                wrapperClassName="wrapper"
                effect={disabledEffect ? undefined : effect}
                placeholderSrc="/assets/placeholder.svg"
                sx={{ width: 1, height: 1, objectFit: 'cover' }}
                {...other}
            />
        </Box>
    );
}

function getRatio(ratio = '1/1'): string | undefined {
    return {
        '4/3': 'calc(100% / 4 * 3)',
        '3/4': 'calc(100% / 3 * 4)',
        '6/4': 'calc(100% / 6 * 4)',
        '4/6': 'calc(100% / 4 * 6)',
        '16/9': 'calc(100% / 16 * 9)',
        '9/16': 'calc(100% / 9 * 16)',
        '21/9': 'calc(100% / 21 * 9)',
        '9/21': 'calc(100% / 9 * 21)',
        '1/1': '100%',
    }[ratio];
}
