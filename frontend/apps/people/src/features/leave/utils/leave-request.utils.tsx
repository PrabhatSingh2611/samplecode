import { formatDateByTemplate, deleteUtcFromDate, castToString } from 'wdx';

import { LeaveRequestActionButtons } from 'features/leave/components/leave-request-action-buttons/leave-request-action-buttons.component';
import { LeaveRequestAuthor } from 'features/leave/components/leave-request-author/leave-request-author.component';
import { LeaveRequestItemStatus } from 'features/leave/components/leave-request-item-status/leave-request-item-status.component';
import { LeaveRequestType } from 'features/leave/components/leave-request-type/leave-request-type.component';
import { LeaveRequestForRequestsListFragment } from 'features/leave/graphql/queries/getLeaveRequestsForRequestsList.generated';

const getRangeDates = (startDate: string, endDate: string): string => {
    return `${formatDateByTemplate(
        new Date(deleteUtcFromDate(startDate)),
        'dd.MM',
    )} - ${formatDateByTemplate(new Date(deleteUtcFromDate(endDate)), 'dd.MM')}`;
};

interface IRequestsListRowData {
    id: string;
    type: JSX.Element;
    name: JSX.Element;
    timeRange: string;
    days: number;
    comment: string;
    status: JSX.Element;
    аctions: JSX.Element;
}

export const getLeaveRequestListRowData = ({
    id,
    type,
    employee,
    startDate,
    endDate,
    numberOfDays,
    comment,
    status,
}: LeaveRequestForRequestsListFragment): IRequestsListRowData => {
    return {
        id,
        type: <LeaveRequestType title={type.name} />,
        name: (
            <LeaveRequestAuthor
                name={`${employee.firstName} ${employee.lastName}`}
                position={employee.position?.name}
            />
        ),
        timeRange: getRangeDates(startDate, endDate),
        days: numberOfDays,
        comment: castToString(comment),
        status: <LeaveRequestItemStatus status={status} />,
        аctions: <LeaveRequestActionButtons leaveRequestId={id} status={status} />,
    };
};
