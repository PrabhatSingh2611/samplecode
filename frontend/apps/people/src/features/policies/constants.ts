import { AlignTableCell } from 'wdx';

export const POLICIES_ORDER_BY = 'publicationDate';
export const POLICIES_TABLE_DEFAULT_ROWS_COUNT = 5;
export const MIN_COUNT_OF_SYMBOLS_TO_SEARCH = 3;

export const POLICY_TABLE_HEADER_DATA = [
    {
        id: 'title',
        label: 'Policy Name',
        align: AlignTableCell.LEFT,
        minWidth: '300px',
        sx: { pl: 12 },
    },
    { id: 'owner', label: 'Owner', align: AlignTableCell.LEFT },
    { id: 'publicationDate', label: 'Publication Date', align: AlignTableCell.LEFT },
    { id: 'status', label: 'Status', align: AlignTableCell.LEFT, width: '150px' },
    { id: 'actions', label: '', align: AlignTableCell.LEFT, width: '76px' },
];

export const POLICIES_URL_SEARCH_PARAMS_KEYS = [
    'order',
    'orderBy',
    'searchValue',
    'currentPage',
    'policiesPerPage',
];
