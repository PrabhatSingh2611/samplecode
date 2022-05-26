import React from 'react';

import { WStack, wStyled, WBadge, WAvatar, WList, getAvatarInitials, castToString } from 'wdx';

import { AssetsAssigneeFragment } from 'features/assets-table/graphql/queries/assetsForAssetsList.generated';

interface IAssigneeCellProps {
    assignee: AssetsAssigneeFragment;
}

export function AssigneeCell({ assignee }: IAssigneeCellProps): JSX.Element {
    const StyledBadge = wStyled(WBadge)(() => ({
        '& .MuiBadge-badge': {
            backgroundColor: '#44b700',
            color: '#44b700',
            marginRight: 15,
            '&::after': {
                position: 'absolute',
                top: 0,
                left: 0,
                width: '100%',
                height: '100%',
                borderRadius: '50%',
                animation: 'ripple 1.2s infinite ease-in-out',
                border: '1px solid currentColor',
                content: '""',
            },
        },
        '@keyframes ripple': {
            '0%': {
                transform: 'scale(.8)',
                opacity: 1,
            },
            '100%': {
                transform: 'scale(2.4)',
                opacity: 0,
            },
        },
    }));

    const { firstName, lastName, avatar, position } = assignee;

    const userInitials = getAvatarInitials(`${firstName} ${lastName}`);
    const assigneeFullName = `${firstName} ${lastName}`;

    return (
        <WStack direction="row" alignItems="center">
            <StyledBadge
                overlap="circular"
                anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
                variant="dot"
            >
                <WAvatar alt={assigneeFullName} src={castToString(avatar)} sx={{ mr: 2 }}>
                    {userInitials}
                </WAvatar>
            </StyledBadge>
            <WList.ItemText primary={assigneeFullName} secondary={position?.name} />
        </WStack>
    );
}
