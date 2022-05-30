import {
    WBaseTable,
    AlignTableCell,
    WCard,
    WTabs,
    WTab,
    WDivider,
    WSubHeader,
    castToInt,
} from 'wdx';

import {
    useGetLeaveRequestList,
    useLeaveRequestSearchParams,
} from 'features/leave/hooks/leave-request-list.hooks';
import { getLeaveRequestListRowData } from 'features/leave/utils/leave-request.utils';

const tabs = ['All'];

const headerData = [
    { id: 'type', label: 'Type', align: AlignTableCell.LEFT },
    { id: 'name', label: 'Name and Position', align: AlignTableCell.LEFT },
    { id: 'timeRange', label: 'Time Range', align: AlignTableCell.LEFT },
    { id: 'days', label: 'N° of Days', align: AlignTableCell.RIGHT, width: '108px' },
    { id: 'comment', label: 'Comment', align: AlignTableCell.LEFT },
    { id: 'status', label: 'Status', align: AlignTableCell.LEFT, width: '108px' },
    { id: 'аctions', label: 'Actions', align: AlignTableCell.RIGHT },
];

export default function RequestsList(): JSX.Element {
    const { currentPage, requestsPerPage, onSetCurrentPage, onSetRowsPerPage } =
        useLeaveRequestSearchParams();

    const [leaveRequestList, { loading, data }] = useGetLeaveRequestList({
        itemsPerPage: requestsPerPage,
        pageNumber: currentPage,
    });

    const bodyData = leaveRequestList.map((item) => getLeaveRequestListRowData(item));

    const totalItems = castToInt(data?.leaveRequests.totalItems);

    return (
        <>
            <WSubHeader title="Requests" subtitle={`${totalItems} Requests`} />
            <WCard>
                <WTabs
                    allowScrollButtonsMobile
                    variant="scrollable"
                    value={tabs[0]}
                    scrollButtons="auto"
                    sx={{ px: 2, bgcolor: 'background.neutral' }}
                >
                    {tabs.map((tab) => (
                        <WTab disableRipple key={tab} label={tab} value={tab} />
                    ))}
                </WTabs>

                <WDivider sx={{ mb: 3 }} />

                <WBaseTable
                    bodyData={bodyData}
                    headerData={headerData}
                    currentPage={currentPage}
                    setCurrentPage={onSetCurrentPage}
                    rowsPerPage={requestsPerPage}
                    setRowsPerPage={onSetRowsPerPage}
                    totalPageCount={totalItems}
                    isCheckable={false}
                    isLoading={loading}
                />
            </WCard>
        </>
    );
}
