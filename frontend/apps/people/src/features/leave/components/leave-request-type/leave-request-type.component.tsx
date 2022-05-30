import { WAvatar, WStack, WTypography, getAvatarInitials } from 'wdx';

interface IRequestTypeProps {
    title: string;
    imageSrc?: string;
}

export function LeaveRequestType({ title, imageSrc }: IRequestTypeProps): JSX.Element {
    return (
        <WStack direction="row" gap={1}>
            <WAvatar src={imageSrc} sx={{ width: '20px', height: '20px' }}>
                {getAvatarInitials(title)}
            </WAvatar>
            <WTypography component="span" variant="body2">
                {title}
            </WTypography>
        </WStack>
    );
}
