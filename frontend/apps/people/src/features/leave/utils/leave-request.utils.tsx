import { formatDateByTemplate, deleteUtcFromDate, castToString, WTypography } from 'wdx';

import { LeaveRequestActionButtons } from 'features/leave/components/leave-request-action-buttons/LeaveRequestActionButtons.component';
import { LeaveRequestAuthor } from 'features/leave/components/leave-request-author/LeaveRequestAuthor.component';
import { LeaveRequestItemStatus } from 'features/leave/components/leave-request-item-status/LeaveRequestItemStatus.component';
import { LeaveRequestType } from 'features/leave/components/leave-request-type/LeaveRequestType.component';
import { LeaveRequestForRequestsListFragment } from 'features/leave/graphql/queries/getLeaveRequestsForRequestsList.generated';

const getRangeDates = (startDate: string, endDate: string): string => {
    return `${formatDateByTemplate(
        new Date(deleteUtcFromDate(startDate)),
        'dd.MM',
    )} - ${formatDateByTemplate(new Date(deleteUtcFromDate(endDate)), 'dd.MM')}`;
};

interface IRequestsListRowData {
    id: string;
    name: JSX.Element;
    timeRange: JSX.Element;
    description: JSX.Element;
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
        name: (
            <LeaveRequestAuthor
                name={`${employee.firstName} ${employee.lastName}`}
                position={employee.position?.name}
            />
        ),
        timeRange: (
            <>
                {getRangeDates(startDate, endDate)}
                <WTypography variant="body2" sx={{ color: 'text.secondary' }}>
                    {getRangeDays(numberOfDays)}
                </WTypography>
            </>
        ),
        description: (
            <>
                <LeaveRequestType title={type.name} />
                <WTypography variant="body2" sx={{ color: 'text.secondary' }}>
                    {castToString(comment)}
                </WTypography>
            </>
        ),
        status: <LeaveRequestItemStatus status={status} />,
        аctions: <LeaveRequestActionButtons leaveRequestId={id} status={status} />,
    };
};

const getRangeDays = (numberOfDays: number): string => {
    if (numberOfDays === 1) {
        return 'a day';
    }

    return `${numberOfDays} days`;
};
