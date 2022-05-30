import { WPill, LabelColor } from 'wdx';

import { LeaveRequestStatus } from 'graphql-generated-types/types';

interface IRequestStatus {
    status: LeaveRequestStatus;
}

const LeaveRequestItemStatusPillDictionary = {
    [LeaveRequestStatus.Approved]: {
        color: 'success' as LabelColor,
        text: 'Approved',
    },
    [LeaveRequestStatus.Declined]: {
        color: 'error' as LabelColor,
        text: 'Rejected',
    },
    [LeaveRequestStatus.New]: {
        color: 'warning' as LabelColor,
        text: 'Pending',
    },
};

export function LeaveRequestItemStatus({ status }: IRequestStatus): JSX.Element {
    const { color, text } = LeaveRequestItemStatusPillDictionary[status];

    return (
        <WPill color={color} variant="ghost">
            {text}
        </WPill>
    );
}
