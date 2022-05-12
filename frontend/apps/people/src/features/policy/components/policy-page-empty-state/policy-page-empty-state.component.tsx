import { Box } from '@mui/material';
import { WUploadSingleFile } from 'wdx';

export default function PolicyPageEmptyState(): JSX.Element {
    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignContent: 'center',
            }}
        >
            <WUploadSingleFile file={null} />
        </Box>
    );
}
