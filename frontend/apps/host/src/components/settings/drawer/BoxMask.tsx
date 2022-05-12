import React from 'react';

import { Radio, FormControlLabel } from '@mui/material';

type Props = {
    value: string;
};

export default function BoxMask({ value }: Props): JSX.Element {
    return (
        <FormControlLabel
            label=""
            value={value}
            control={<Radio sx={{ display: 'none' }} />}
            sx={{
                position: 'absolute',
                top: 0,
                left: 0,
                right: 0,
                bottom: 0,
                m: 0,
            }}
        />
    );
}
