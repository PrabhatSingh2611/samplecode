import { Meta, Story } from '@storybook/react';
import React from 'react';
import WBox from '../box';
import WSwitch from '../switch';
import WTansitionCollapse, { WTansitionFade, WTansitionGrow, WTansitionSlide, WTansitionZoom } from './index';

const meta: Meta = {
    title: 'MUI/Utils/Transition',
    component: WTansitionCollapse,
    parameters: {
        controls: { expanded: true },
    },
};

export default meta;

const TemplateDefault: Story = (args) => {
    const [checked, setChecked] = React.useState(false);
    const handleChange = () => {
        setChecked((prev) => !prev);
    };

    return (
        <WBox sx={{ height: 300 }}>
            <WSwitch checked={checked} onChange={handleChange} />
            <WBox
                sx={{
                    '& > :not(style)': {
                        display: 'flex',
                        justifyContent: 'space-around',
                        height: 120,
                        width: 250,
                    },
                }}
            >
                <WBox>
                    <WTansitionCollapse {...args} in={checked}><div>Collapse</div></WTansitionCollapse>
                </WBox>
            </WBox>
        </WBox>
    );
}

const TemplateFade: Story = (args) => {
    const [checked, setChecked] = React.useState(false);
    const handleChange = () => {
        setChecked((prev) => !prev);
    };

    return (
        <WBox sx={{ height: 300 }}>
            <WSwitch checked={checked} onChange={handleChange} />
            <WBox sx={{ display: 'flex' }}>
                <WTansitionFade {...args} in={checked}><div>Fade</div></WTansitionFade>
            </WBox>
        </WBox>
    );
}

const TemplateGrow: Story = (args) => {
    const [checked, setChecked] = React.useState(false);
    const handleChange = () => {
        setChecked((prev) => !prev);
    };

    return (
        <WBox sx={{ height: 300 }}>
            <WSwitch checked={checked} onChange={handleChange} />
            <WTansitionGrow in={checked}><div>Grow</div></WTansitionGrow>
            {/* Conditionally applies the timeout prop to change the entry speed. */}
            <WTansitionGrow
                {...args}
                in={checked}
                style={{ transformOrigin: '0 0 0' }}
                {...(checked ? { timeout: 1000 } : {})}
            >
                <div>Grow</div>
            </WTansitionGrow>
        </WBox>
    );
}

const TemplateSlide: Story = (args) => {
    const [checked, setChecked] = React.useState(false);
    const handleChange = () => {
        setChecked((prev) => !prev);
    };

    return (
        <WBox sx={{ height: 300 }}>
            <WSwitch checked={checked} onChange={handleChange} />
            <WTansitionSlide {...args} direction="up" in={checked} mountOnEnter unmountOnExit>
                <div>Grow</div>
            </WTansitionSlide>
        </WBox>
    );
}

const TemplateZoom: Story = (args) => {
    const [checked, setChecked] = React.useState(false);
    const handleChange = () => {
        setChecked((prev) => !prev);
    };

    return (
        <WBox sx={{ height: 100, width: 100 }}>
            <WSwitch checked={checked} onChange={handleChange} />
            <WTansitionZoom {...args}  in={checked}><div>Zoom</div></WTansitionZoom>
            <WTansitionZoom {...args}  in={checked} style={{ transitionDelay: checked ? '500ms' : '0ms' }}>
                <div>Zoom</div>
            </WTansitionZoom>
        </WBox>
    );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Collapse = TemplateDefault.bind({});
export const Fade = TemplateFade.bind({});
export const Grow = TemplateGrow.bind({});
export const Slide = TemplateSlide.bind({});
export const Zoom = TemplateZoom.bind({});