import React from 'react';

import SvgIconStyle from 'components/SvgIconStyle';
import { EAssetsRouterLink } from 'models/assets-router-link';
import { EPeopleRouterLink } from 'models/people-router-link';
import { ERecruitmentRouterLink } from 'models/recruitment-router-llink';

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
    {
        subheader: 'Navigation',
        items: [
            {
                title: 'Home',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Me,
                icon: ICONS.user,
            },
            {
                title: 'Employees',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Employees,
                icon: ICONS.dashboard,
            },
            {
                title: 'Leave Requests',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Leaves,
                icon: ICONS.booking,
            },
            { title: 'Assets', path: EAssetsRouterLink.Assets, icon: ICONS.invoice },
            { title: 'Recruitment', path: ERecruitmentRouterLink.Recruitment, icon: ICONS.invoice },
            {
                title: 'Policies',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Policies,
                icon: ICONS.dashboard,
            },
            {
                title: 'Playbooks',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Playbooks,
                icon: ICONS.invoice,
            },
            {
                title: 'Settings',
                path: '/',
                icon: ICONS.user,
                children: [
                    {
                        title: 'Positions',
                        path: EPeopleRouterLink.People + EPeopleRouterLink.Positions,
                    },
                    {
                        title: 'Asset Types',
                        path: EAssetsRouterLink.Assets + EAssetsRouterLink.Types,
                    },
                    {
                        title: 'Locations',
                        path: EPeopleRouterLink.People + EPeopleRouterLink.Locations,
                    },
                    { title: 'Admins', path: EPeopleRouterLink.People + EPeopleRouterLink.Admins },
                ],
            },
        ],
    },
];

export default navConfig;
