import { WAvatar, WBox, WStack, WTypography, getAvatarInitials } from 'wdx';

interface IRequestAuthorProps {
    name: string;
    position?: string | null;
    imageSrc?: string;
}

export function LeaveRequestAuthor({ name, imageSrc, position }: IRequestAuthorProps): JSX.Element {
    return (
        <WStack direction="row" alignItems="center">
            <WAvatar alt={name} src={imageSrc}>
                {getAvatarInitials(name)}
            </WAvatar>

            <WBox sx={{ ml: 1 }}>
                <WTypography variant="subtitle2"> {name} </WTypography>
                {position && (
                    <WTypography variant="body2" sx={{ color: 'text.secondary' }}>
                        {position}
                    </WTypography>
                )}
            </WBox>
        </WStack>
    );
}
