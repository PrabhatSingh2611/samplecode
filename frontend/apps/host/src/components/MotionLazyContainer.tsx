import React, { ReactNode } from 'react';

import { LazyMotion, FeatureBundle } from 'framer-motion';

const loadFeatures = (): Promise<FeatureBundle> => import('./features').then((res) => res.default);

type IProps = {
    children: ReactNode;
};

export default function MotionLazyContainer({ children }: IProps): JSX.Element {
    return (
        <LazyMotion strict features={loadFeatures}>
            {children}
        </LazyMotion>
    );
}
