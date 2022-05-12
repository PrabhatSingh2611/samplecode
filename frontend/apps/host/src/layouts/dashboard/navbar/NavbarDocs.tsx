import React from 'react';

import { Stack, Button, Typography } from '@mui/material';

import DocIllustration from 'components/nav-section/DocIllustration';

export default function NavbarDocs(): JSX.Element {
    return (
        <Stack
            spacing={3}
            sx={{ display: 'block', width: 1, mt: 10, px: 5, pb: 5, textAlign: 'center' }}
        >
            <DocIllustration sx={{ width: 1 }} />

            <div>
                <Typography gutterBottom variant="subtitle1">
                    Hi, Rayan Moran
                </Typography>
                <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                    Need help?
                    <br /> Please check our docs
                </Typography>
            </div>

            <Button variant="contained">Documentation</Button>
        </Stack>
    );
}
