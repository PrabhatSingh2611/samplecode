import React from 'react';

import SvgIconStyle from 'components/SvgIconStyle';

const getIcon = (name: string): JSX.Element => (
    <SvgIconStyle src={`/assets/icons/navbar/${name}.svg`} sx={{ width: 1, height: 1 }} />
);

const ICONS = {
    user: getIcon('ic_user'),
    ecommerce: getIcon('ic_ecommerce'),
    analytics: getIcon('ic_analytics'),
    dashboard: getIcon('ic_dashboard'),
    invoice: getIcon('ic_invoice'),
    booking: getIcon('ic_booking'),
};

const navConfig = [
    // NAVIGATION

    {
        subheader: 'Navigation',
        items: [
            { title: 'Home', path: '/people/me', icon: ICONS.user },
            { title: 'Policy', path: '/people/policy', icon: ICONS.dashboard },
            { title: 'Employees', path: '/people/employees', icon: ICONS.dashboard },
            { title: 'Leave Requests', path: '/people/leaves', icon: ICONS.booking },
            { title: 'Assets', path: '/assets', icon: ICONS.invoice },
            { title: 'Recruitment', path: '/recruitment', icon: ICONS.invoice },
        ],
    },

    // MANAGEMENT

    {
        subheader: 'management',
        items: [
            {
                title: 'user',
                path: '/dashboard/user',
                icon: ICONS.user,
                children: [
                    { title: 'Four', path: '/dashboard/user/four' },
                    { title: 'Five', path: '/dashboard/user/five' },
                    { title: 'Six', path: '/dashboard/user/six' },
                ],
            },
        ],
    },
];

export default navConfig;
