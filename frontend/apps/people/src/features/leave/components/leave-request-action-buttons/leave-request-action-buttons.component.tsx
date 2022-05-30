import { useMemo, useState } from 'react';

import { WButton, WStack, WMenu, WMenuItem, WIcon } from 'wdx';

import { usePatchLeaveRequestStatus } from 'features/leave/hooks/leave-request-list.hooks';
import { LeaveRequestStatus } from 'graphql-generated-types/types';

interface IActionButtons {
    status: LeaveRequestStatus;
    leaveRequestId: string;
}

export function LeaveRequestActionButtons({ status, leaveRequestId }: IActionButtons): JSX.Element {
    const [patchLeaveRequestStatus] = usePatchLeaveRequestStatus();
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const isOpen = !!anchorEl;

    const iconStyles = useMemo(
        () => ({ transform: isOpen ? 'rotateX(180deg)' : undefined }),
        [isOpen],
    );

    if (status === LeaveRequestStatus.Approved || status === LeaveRequestStatus.Declined) {
        const ariaControlsValue = isOpen ? 'basic-menu' : undefined;
        const ariaExpandedValue = isOpen ? 'true' : undefined;
        const isRejected = status === LeaveRequestStatus.Declined;
        const activeLabel = isRejected ? 'Rejected' : 'Approved';

        const handleClick = (event: React.MouseEvent<HTMLButtonElement>): void => {
            setAnchorEl(event.currentTarget);
        };
        const handleClose = (): void => {
            setAnchorEl(null);
        };

        const onAction = (status: LeaveRequestStatus): void => {
            patchLeaveRequestStatus({
                id: leaveRequestId,
                status,
            });
            handleClose();
        };

        return (
            <>
                <WButton
                    onClick={handleClick}
                    aria-controls={ariaControlsValue}
                    aria-haspopup="true"
                    aria-expanded={ariaExpandedValue}
                    variant="outlined"
                    color={isRejected ? 'error' : 'primary'}
                    endIcon={<WIcon name="chevron-down" sx={iconStyles} />}
                >
                    {activeLabel}
                </WButton>
                <WMenu
                    open={isOpen}
                    anchorEl={anchorEl}
                    onClose={handleClose}
                    MenuListProps={{
                        'aria-labelledby': 'rejected-action-button',
                    }}
                >
                    <WMenuItem
                        onClick={(): void => {
                            onAction(LeaveRequestStatus.Declined);
                        }}
                        selected={isRejected}
                    >
                        Rejected
                    </WMenuItem>
                    <WMenuItem
                        onClick={(): void => {
                            onAction(LeaveRequestStatus.Approved);
                        }}
                        selected={!isRejected}
                    >
                        Approved
                    </WMenuItem>
                </WMenu>
            </>
        );
    }

    return (
        <WStack direction="row" gap={1} justifyContent="flex-end">
            <WButton variant="outlined" color="error">
                Reject
            </WButton>
            <WButton variant="contained">Approve</WButton>
        </WStack>
    );
}
