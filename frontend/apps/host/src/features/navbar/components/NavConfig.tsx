import React from 'react';

import SvgIconStyle from 'components/SvgIconStyle';
import { EAssetsRouterLink } from 'models/assets-router-link';
import { EPeopleRouterLink } from 'models/people-router-link';
import { ERecruitmentRouterLink } from 'models/recruitment-router-llink';
import { ESettingsRouterLink } from 'models/settings-router-link';

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

const settingsPeoplePath = ESettingsRouterLink.Settings + ESettingsRouterLink.People;

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
                path: ESettingsRouterLink.Settings,
                icon: ICONS.user,
                children: [
                    {
                        title: 'Positions',
                        path: settingsPeoplePath + ESettingsRouterLink.Positions,
                    },
                    {
                        title: 'Asset Types',
                        path:
                            ESettingsRouterLink.Settings +
                            ESettingsRouterLink.Assets +
                            ESettingsRouterLink.Types,
                    },
                    {
                        title: 'Locations',
                        path: settingsPeoplePath + ESettingsRouterLink.Locations,
                    },
                    { title: 'Admins', path: settingsPeoplePath + ESettingsRouterLink.Me },
                ],
            },
        ],
    },
];

export default navConfig;
