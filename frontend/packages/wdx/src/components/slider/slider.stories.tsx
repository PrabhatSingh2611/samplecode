import { Meta, Story } from '@storybook/react';
import React from 'react';
import WSlider from '.';
import WBox from '../box';
import WTypography from '../typography';

const meta: Meta = {
  title: 'MUI/Inputs/Slider',
  component: WSlider,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {
    return (
        <WBox>
            <WTypography variant="h3">Horizontal</WTypography>
            <WBox display='grid' minHeight="200px" p={4} gap={4} alignItems="center" >
                <WSlider {...args} color="primary" orientation='horizontal'/>
                <WSlider {...args} color="secondary" orientation='horizontal' marks={[{label: '50%', value: 50}]}/>
            </WBox>
            <WTypography variant="h3">Vertical</WTypography>
            <WBox display='flex' minHeight="200px" p={4} gap={4} alignItems="center" >
                <WSlider {...args} color="primary" orientation='vertical' sx={{height: 200}} />
                <WSlider {...args} color="secondary" orientation='vertical' sx={{height: 200}} marks={[{label: '50%', value: 50}]}/>
            </WBox>
        </WBox>
    )
}

const TemplateRange: Story = args => {
    const [value, setValue] = React.useState([8, 86]);

    const handleChange = (event: Event, newValue: number | number[]) => {
      setValue(newValue as number[] || event);
    };

    return (
      <WBox sx={{ width: 300, mt: 8 }}>
        <WSlider
          {...args}
          value={value}
          onChange={handleChange}
          valueLabelDisplay="auto"
        />
      </WBox>
    );
  }

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
export const Group = TemplateRange.bind({});

Group.args = {
    disableSwap: false,
}