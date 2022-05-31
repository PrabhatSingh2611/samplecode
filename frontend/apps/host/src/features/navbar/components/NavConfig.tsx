import React from 'react';

import { WIcon } from 'wdx';

import SvgIconStyle from 'components/SvgIconStyle';
import { EAssetsRouterLink } from 'models/assets-router-link';
import { EPeopleRouterLink } from 'models/people-router-link';
import { ERecruitmentRouterLink } from 'models/recruitment-router-llink';

// TODO: Fix this to fetch from correct path on production [IM]
const getIcon = (name: string): JSX.Element => (
    <SvgIconStyle src={`/assets/icons/navbar/${name}.svg`} sx={{ width: 1, height: 1 }} />
);

const ICONS = {
    home: <WIcon name="badge" />,
    employees: <WIcon name="people-alt" />,
    people: <WIcon name="nature-people" />,
    computer: <WIcon name="computer" />,
    connect: <WIcon name="connect-without-contact" />,
    policy: <WIcon name="policy" />,
    book: <WIcon name="menu-book" />,
    settings: <WIcon name="settings" />,
};

const navConfig = [
    {
        subheader: 'Navigation',
        items: [
            {
                title: 'Home',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Me,
                icon: ICONS.home,
            },
            {
                title: 'Employees',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Employees,
                icon: ICONS.employees,
            },
            {
                title: 'Leave Requests',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Leaves,
                icon: ICONS.people,
            },
            {
                title: 'Assets',
                path: EAssetsRouterLink.Assets + EAssetsRouterLink.AssetsList,
                icon: ICONS.computer,
            },
            { title: 'Recruitment', path: ERecruitmentRouterLink.Recruitment, icon: ICONS.connect },
            {
                title: 'Policies',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Policies,
                icon: ICONS.policy,
            },
            {
                title: 'Playbooks',
                path: EPeopleRouterLink.People + EPeopleRouterLink.Playbooks,
                icon: ICONS.book,
            },
            {
                title: 'Settings',
                path: '/',
                icon: ICONS.settings,
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
