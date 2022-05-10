import SvgIconStyle from 'components/SvgIconStyle';

const getIcon = (name: string) => (
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
            { title: 'People', path: '/people', icon: ICONS.user },
            { title: 'Assets', path: '/assets', icon: ICONS.dashboard },
            { title: 'Organization', path: '/organization', icon: ICONS.booking },
            { title: 'Recruitment', path: '/recruitment', icon: ICONS.invoice },
            { title: 'Grow', path: '/grow', icon: ICONS.analytics },
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
