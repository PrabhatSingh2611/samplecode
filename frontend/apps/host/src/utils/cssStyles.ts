import { Theme, alpha } from '@mui/material/styles';

type BackgroundBlurProps = {
    blur?: number;
    opacity?: number;
    color?: string;
};

type BackgroundGradientProps = {
    direction?: string;
    startColor?: string;
    endColor?: string;
};

interface IBackgroundImageProps extends BackgroundGradientProps {
    url?: string;
}

function getDirection(value = 'bottom'): string | undefined {
    return {
        top: 'to top',
        right: 'to right',
        bottom: 'to bottom',
        left: 'to left',
    }[value];
}

type BgGradientResult = { background: string };

interface IBgBlurResult {
    WebkitBackdropFilter: string;
    backgroundColor: string;
    backdropFilter: string;
}

interface IBgImagesResult {
    background: string;
    backgroundSize: string;
    backgroundPosition: string;
    backgroundRepeat: string;
}

interface ICssStylesResult {
    bgBlur: (props?: BackgroundBlurProps) => IBgBlurResult;
    bgImage: (props?: IBackgroundImageProps) => IBgImagesResult;
    bgGradient: (props?: BackgroundGradientProps) => BgGradientResult;
}

export default function cssStyles(theme?: Theme): ICssStylesResult {
    return {
        bgBlur: (props?: BackgroundBlurProps): IBgBlurResult => {
            const color = props?.color || theme?.palette.background.default || '#000000';

            const blur = props?.blur || 6;
            const opacity = props?.opacity || 0.8;

            return {
                backdropFilter: `blur(${blur}px)`,
                WebkitBackdropFilter: `blur(${blur}px)`, // Fix on Mobile
                backgroundColor: alpha(color, opacity),
            };
        },
        bgGradient: (props?: BackgroundGradientProps): BgGradientResult => {
            const direction = getDirection(props?.direction);
            const startColor = props?.startColor || `${alpha('#000000', 0)} 0%`;
            const endColor = props?.endColor || '#000000 75%';

            return {
                background: `linear-gradient(${direction}, ${startColor}, ${endColor});`,
            };
        },
        bgImage: (props?: IBackgroundImageProps): IBgImagesResult => {
            const url = props?.url || '/assets/bg_gradient.jpg';
            const direction = getDirection(props?.direction);
            const startColor =
                props?.startColor || alpha(theme?.palette.grey[900] || '#000000', 0.88);
            const endColor = props?.endColor || alpha(theme?.palette.grey[900] || '#000000', 0.88);

            return {
                background: `linear-gradient(${direction}, ${startColor}, ${endColor}), url(${url})`,
                backgroundSize: 'cover',
                backgroundRepeat: 'no-repeat',
                backgroundPosition: 'center center',
            };
        },
    };
}
