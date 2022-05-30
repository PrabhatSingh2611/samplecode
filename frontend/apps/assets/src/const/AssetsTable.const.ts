import getStaticAssetUrl from 'utils/staticAssetUrl';
import { AlignTableCell } from 'wdx';

export interface ITypeOptions {
    id: string;
    name: string;

    children?: ITypeOptions[];
}

export interface IRenderOptions {
    id: string;
    name: string;
    flagIcon?: string;
    children?: IRenderOptions[];
}

export const TYPE_OPTIONS: ITypeOptions[] = [
    {
        id: '0',
        name: 'License',
        children: [
            {
                id: '1',
                name: 'Windows',
            },
            {
                id: '2',
                name: 'Mac OS',
            },
            {
                id: '3',
                name: 'Software',
            },
        ],
    },
    {
        id: '4',
        name: 'Laptop',
        children: [
            {
                id: '5',
                name: 'Windows',
            },
            {
                id: '6',
                name: 'Mac OS',
            },
            {
                id: '7',
                name: 'Software',
            },
        ],
    },
    {
        id: '8',
        name: 'Smartphone',
        children: [
            {
                id: '9',
                name: 'Windows',
            },
            {
                id: '10',
                name: 'Mac OS',
            },
            {
                id: '11',
                name: 'Software',
            },
        ],
    },
];

export interface ILocationOptions {
    id: string;
    name: string;
    flagIcon?: string;
    children?: ILocationOptions[];
}

export const LOCATION_OPTIONS_LIST: ILocationOptions[] = [
    {
        id: '0',
        name: 'United Kingdom',
        flagIcon: getStaticAssetUrl('/assets/icons/flags/ic_flag_en.svg'),
        children: [
            {
                id: '1',
                name: 'London',
            },
            {
                id: '2',
                name: 'Manchester',
            },
            {
                id: '3',
                name: 'Liverpool',
            },
        ],
    },
];

interface IHeaderCellData {
    id: string;
    align?: AlignTableCell;
    width?: string;
    minWidth?: string;
    label?: string;
}

export const TABLE_HEAD: IHeaderCellData[] = [
    { id: 'type', label: 'Type', align: AlignTableCell.LEFT },
    { id: 'nameAndSerialNumber', label: 'Name and serial number', align: AlignTableCell.LEFT },
    { id: 'assignee', label: 'Assignee', align: AlignTableCell.LEFT },
    { id: 'dateOfWaybill', label: 'Date of Waybill', align: AlignTableCell.CENTER },
    { id: 'location', label: 'Location', align: AlignTableCell.LEFT },
    { id: 'tagNumber', label: 'Tag Number', align: AlignTableCell.LEFT },
    { id: '' },
];
