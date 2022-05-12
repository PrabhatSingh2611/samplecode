import React, { useState } from 'react';

import {
    Box,
    List,
    Badge,
    Button,
    Avatar,
    Tooltip,
    Divider,
    IconButton,
    Typography,
    ListItemText,
    ListSubheader,
    ListItemAvatar,
    ListItemButton,
} from '@mui/material';
import { noCase } from 'change-case';

import { _notifications } from '_mock';
import Iconify from 'components/Iconify';
import MenuPopover from 'components/MenuPopover';
import Scrollbar from 'components/Scrollbar';
import { IconButtonAnimate } from 'components/animate';
import { fToNow } from 'utils/formatTime';

export default function NotificationsPopover(): JSX.Element {
    const [notifications, setNotifications] = useState(_notifications);

    const totalUnRead = notifications.filter((item) => item.isUnRead).length;

    const [open, setOpen] = useState<HTMLElement | null>(null);

    const handleOpen = (event: React.MouseEvent<HTMLElement>): void => {
        setOpen(event.currentTarget);
    };

    const handleClose = (): void => {
        setOpen(null);
    };

    const handleMarkAllAsRead = (): void => {
        setNotifications(
            notifications.map((notification) => ({
                ...notification,
                isUnRead: false,
            })),
        );
    };

    return (
        <>
            <IconButtonAnimate
                color={open ? 'primary' : 'default'}
                onClick={handleOpen}
                sx={{ width: 40, height: 40 }}
            >
                <Badge badgeContent={totalUnRead} color="error">
                    <Iconify icon="eva:bell-fill" width={20} height={20} />
                </Badge>
            </IconButtonAnimate>

            <MenuPopover
                open={Boolean(open)}
                anchorEl={open}
                onClose={handleClose}
                sx={{ width: 360, mt: 1.5, ml: 0.75, p: 0 }}
            >
                <Box sx={{ display: 'flex', alignItems: 'center', py: 2, px: 2.5 }}>
                    <Box sx={{ flexGrow: 1 }}>
                        <Typography variant="subtitle1">Notifications</Typography>
                        <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                            You have {totalUnRead} unread messages
                        </Typography>
                    </Box>

                    {totalUnRead > 0 && (
                        <Tooltip title=" Mark all as read">
                            <IconButton color="primary" onClick={handleMarkAllAsRead}>
                                <Iconify icon="eva:done-all-fill" width={20} height={20} />
                            </IconButton>
                        </Tooltip>
                    )}
                </Box>

                <Divider sx={{ borderStyle: 'dashed' }} />

                <Scrollbar sx={{ height: { sm: 'auto', xs: 340 } }}>
                    <List
                        disablePadding
                        subheader={
                            <ListSubheader
                                disableSticky
                                sx={{ py: 1, px: 2.5, typography: 'overline' }}
                            >
                                New
                            </ListSubheader>
                        }
                    >
                        {notifications.slice(0, 2).map((notification) => (
                            <NotificationItem key={notification.id} notification={notification} />
                        ))}
                    </List>

                    <List
                        disablePadding
                        subheader={
                            <ListSubheader
                                disableSticky
                                sx={{ py: 1, px: 2.5, typography: 'overline' }}
                            >
                                Before that
                            </ListSubheader>
                        }
                    >
                        {notifications.slice(2, 5).map((notification) => (
                            <NotificationItem key={notification.id} notification={notification} />
                        ))}
                    </List>
                </Scrollbar>

                <Divider sx={{ borderStyle: 'dashed' }} />

                <Box sx={{ p: 1 }}>
                    <Button fullWidth disableRipple>
                        View All
                    </Button>
                </Box>
            </MenuPopover>
        </>
    );
}

interface INotificationItemProps {
    id: string;
    title: string;
    description: string;
    avatar: string | null;
    type: string;
    createdAt: Date;
    isUnRead: boolean;
}

function NotificationItem({ notification }: { notification: INotificationItemProps }): JSX.Element {
    const { avatar, title } = renderContent(notification);

    return (
        <ListItemButton
            sx={{
                mt: '1px',
                py: 1.5,
                px: 2.5,
                ...(notification.isUnRead && {
                    bgcolor: 'action.selected',
                }),
            }}
        >
            <ListItemAvatar>
                <Avatar sx={{ bgcolor: 'background.neutral' }}>{avatar}</Avatar>
            </ListItemAvatar>
            <ListItemText
                primary={title}
                secondary={
                    <Typography
                        variant="caption"
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            mt: 0.5,
                            color: 'text.disabled',
                        }}
                    >
                        <Iconify icon="eva:clock-outline" sx={{ width: 16, height: 16, mr: 0.5 }} />
                        {fToNow(notification.createdAt)}
                    </Typography>
                }
            />
        </ListItemButton>
    );
}

function renderContent(notification: INotificationItemProps): {
    avatar: JSX.Element | null;
    title: JSX.Element;
} {
    const title = (
        <Typography variant="subtitle2">
            {notification.title}
            <Typography component="span" variant="body2" sx={{ color: 'text.secondary' }}>
                &nbsp; {noCase(notification.description)}
            </Typography>
        </Typography>
    );

    if (notification.type === 'order_placed') {
        return {
            avatar: (
                <img
                    alt={notification.title}
                    src="https://minimal-assets-api-dev.vercel.app/assets/icons/ic_notification_package.svg"
                />
            ),
            title,
        };
    }
    if (notification.type === 'order_shipped') {
        return {
            avatar: (
                <img
                    alt={notification.title}
                    src="https://minimal-assets-api-dev.vercel.app/assets/icons/ic_notification_shipping.svg"
                />
            ),
            title,
        };
    }
    if (notification.type === 'mail') {
        return {
            avatar: (
                <img
                    alt={notification.title}
                    src="https://minimal-assets-api-dev.vercel.app/assets/icons/ic_notification_mail.svg"
                />
            ),
            title,
        };
    }
    if (notification.type === 'chat_message') {
        return {
            avatar: (
                <img
                    alt={notification.title}
                    src="https://minimal-assets-api-dev.vercel.app/assets/icons/ic_notification_chat.svg"
                />
            ),
            title,
        };
    }

    return {
        avatar: notification.avatar ? (
            <img alt={notification.title} src={notification.avatar} />
        ) : null,
        title,
    };
}
