export type Props = {
    staggerIn?: number;
    delayIn?: number;
    staggerOut?: number;
};

interface IVariantContainer {
    animate: {
        transition: {
            staggerChildren: number;
            delayChildren: number;
        };
    };
    exit: {
        transition: {
            staggerChildren: number;
            staggerDirection: number;
        };
    };
}

export const varContainer = (props?: Props): IVariantContainer => {
    const staggerIn = props?.staggerIn || 0.05;
    const delayIn = props?.staggerIn || 0.05;
    const staggerOut = props?.staggerIn || 0.05;

    return {
        animate: {
            transition: {
                staggerChildren: staggerIn,
                delayChildren: delayIn,
            },
        },
        exit: {
            transition: {
                staggerChildren: staggerOut,
                staggerDirection: -1,
            },
        },
    };
};
