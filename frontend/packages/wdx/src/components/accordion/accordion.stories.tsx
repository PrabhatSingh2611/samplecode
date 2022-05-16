import { Meta, Story } from '@storybook/react';
import React from 'react';
import Typography from '../typography';
import { TreeViewCollapseIcon } from '../../theme/overrides/CustomIcons';
import WButton from '../button';
import WAccordion, { WAccordionActions, WAccordionDetails, WAccordionSummary } from './index';

const meta: Meta = {
    title: 'MUI/Surfaces/Accordion',
    component: WAccordion,
    parameters: {
        controls: { expanded: true },
    },
};

export default meta;

const TemplateDefault: Story = args => {

    return (
        <>
            <WAccordion {...args}>
                <WAccordionSummary
                    expandIcon={<TreeViewCollapseIcon />}
                    aria-controls="panel1a-content"
                >
                    <Typography>Accordion 1</Typography>
                </WAccordionSummary>
                <WAccordionDetails>
                    <Typography>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse
                        malesuada lacus ex, sit amet blandit leo lobortis eget.
                    </Typography>
                </WAccordionDetails>
            </WAccordion>
            <WAccordion {...args}>
                <WAccordionSummary
                    expandIcon={<TreeViewCollapseIcon />}
                    aria-controls="panel2a-content"
                >
                    <Typography>Accordion 2</Typography>
                </WAccordionSummary>
                <WAccordionDetails>
                    <Typography>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse
                        malesuada lacus ex, sit amet blandit leo lobortis eget.
                    </Typography>
                </WAccordionDetails>
            </WAccordion>
            <WAccordion {...args}>
                <WAccordionSummary
                    expandIcon={<TreeViewCollapseIcon />}
                    aria-controls="panel3a-content"
                >
                    <Typography>Disabled Accordion</Typography>
                </WAccordionSummary>
            </WAccordion>
            <WAccordion {...args}>
                <WAccordionSummary
                    aria-controls="panel4a-content"
                >
                    <WAccordionActions><WButton
                    >
                        Accordion
                    </WButton></WAccordionActions>

                </WAccordionSummary>
                <WAccordionDetails>
                    <Typography>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse
                        malesuada lacus ex, sit amet blandit leo lobortis eget.
                    </Typography>
                </WAccordionDetails>
            </WAccordion>
        </>
    )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = TemplateDefault.bind({});

Default.args = {
    variant: 'elevation',
    disabled: false,
};