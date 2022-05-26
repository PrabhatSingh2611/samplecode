import React, { ReactNode } from 'react';

import { WBox, WTypography, WSxProps } from 'wdx';

interface IProps {
    action?: ReactNode;
    heading: string;
    sx?: WSxProps;
}

export default function AssetsHeader({ action, heading, sx }: IProps): JSX.Element {
    return (
        <WBox sx={{ mb: 5, ...sx }}>
            <WBox sx={{ display: 'flex', alignItems: 'center' }}>
                <WBox sx={{ flexGrow: 1 }}>
                    <WTypography variant="h4" gutterBottom>
                        {heading}
                    </WTypography>
                </WBox>

                {action && <WBox sx={{ flexShrink: 0 }}>{action}</WBox>}
            </WBox>
        </WBox>
    );
}
