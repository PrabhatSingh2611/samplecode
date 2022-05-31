import { Meta, Story } from '@storybook/react';
import React from 'react';
import { TreeViewEndIcon } from '../../theme/overrides/CustomIcons';
import WLink from '../link';
import WStack from '../stack';
import WTypography from '../typography';
import WBreadcrumbs from './index';

const meta: Meta = {
    title: 'MUI/Navigation/Breadcrumbs',
    component: WBreadcrumbs,
    parameters: {
        controls: { expanded: true },
    },
};

export default meta;

const TemplateDefault: Story = args => {

    return (
        <WBreadcrumbs {...args} aria-label="breadcrumb">
            <WLink underline="hover" color="inherit" href="/">
                MUI
            </WLink>
            <WLink
                underline="hover"
                color="inherit"
                href="/material-ui/getting-started/installation/"
            >
                Core
            </WLink>
            <WTypography color="text.primary">Breadcrumbs</WTypography>
        </WBreadcrumbs>
    )
}

const TemplateCustomSeparator: Story = args => {
    const breadcrumbs = [
        <WLink underline="hover" key="1" color="inherit" href="/">
            MUI
        </WLink>,
        <WLink
            underline="hover"
            key="2"
            color="inherit"
            href="/material-ui/getting-started/installation/"
        >
            Core
        </WLink>,
        <WTypography key="3" color="text.primary">
            Breadcrumb
        </WTypography>,
    ];

    return (
        <WStack spacing={2}>
            <WBreadcrumbs {...args} separator="›" aria-label="breadcrumb">
                {breadcrumbs}
            </WBreadcrumbs>
            <WBreadcrumbs {...args} separator="•" aria-label="breadcrumb">
                {breadcrumbs}
            </WBreadcrumbs>
            <WBreadcrumbs
                {...args}
                separator={<TreeViewEndIcon fontSize="small" />}
                aria-label="breadcrumb"
            >
                {breadcrumbs}
            </WBreadcrumbs>
        </WStack>
    )
}

const TemplateCollapsedBreadcrumbs: Story = args => {

    return (
        <WBreadcrumbs {...args} maxItems={2} aria-label="breadcrumb">
            <WLink underline="hover" color="inherit" href="#">
                Home
            </WLink>
            <WLink underline="hover" color="inherit" href="#">
                Catalog
            </WLink>
            <WLink underline="hover" color="inherit" href="#">
                Accessories
            </WLink>
            <WLink underline="hover" color="inherit" href="#">
                New Collection
            </WLink>
            <WTypography color="text.primary">Belts</WTypography>
        </WBreadcrumbs>
    )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = TemplateDefault.bind({});
export const CustomSeparator = TemplateCustomSeparator.bind({});
export const CollapsedBreadcrumbs = TemplateCollapsedBreadcrumbs.bind({});

