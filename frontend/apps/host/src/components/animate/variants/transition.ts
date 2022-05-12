import { TranEnterType, TranExitType, EaseType } from 'components/animate/type';

export const varTranEnter = (
    props?: TranEnterType,
): {
    duration: number;
    ease: EaseType;
} => {
    const duration = props?.durationIn || 0.64;
    const ease = props?.easeIn || [0.43, 0.13, 0.23, 0.96];

    return { duration, ease };
};

export const varTranExit = (
    props?: TranExitType,
): {
    duration: number;
    ease: EaseType;
} => {
    const duration = props?.durationOut || 0.48;
    const ease = props?.easeOut || [0.43, 0.13, 0.23, 0.96];

    return { duration, ease };
};
