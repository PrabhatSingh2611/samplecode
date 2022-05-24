import { Meta, Story } from '@storybook/react';
import React from 'react';
import WTabs, { WTab, WTabContext, WTabList, WTabPanel } from './index';
import WBox from '../box';
import WTypography from '../typography';

const meta: Meta = {
  title: 'MUI/Navigation/Tabs',
  component: WTabs,
  argTypes: {
    children: {
      control: {
        type: 'contained',
      },
    },
  },
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

interface TabPanelProps {
  children?: React.ReactNode;
  index: number;
  value: number;
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <WBox sx={{ p: 3 }}>
          <WTypography>{children}</WTypography>
        </WBox>
      )}
    </div>
  );
}

function a11yProps(index: number) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}

const TemplateExperimental: Story = ( args ) => {
  const [value, setValue] = React.useState('1');
  const handleChange = (_event: React.SyntheticEvent, newValue: string) => {
    setValue(newValue);
  };

  return (
    <WBox sx={{ width: '100%', typography: 'body1' }}>
      <WTabContext {...args} value={value}>
        <WBox sx={{ borderBottom: 1, borderColor: 'divider' }}>
          <WTabList onChange={handleChange} aria-label="lab API tabs example">
            <WTab label="Item One" value="1" />
            <WTab label="Item Two" value="2" />
            <WTab label="Item Three" value="3" />
          </WTabList>
        </WBox>
        <WTabPanel value="1">Item One</WTabPanel>
        <WTabPanel value="2">Item Two</WTabPanel>
        <WTabPanel value="3">Item Three</WTabPanel>
      </WTabContext>
    </WBox>
  );
}

const Template: Story = ( args ) => {
  const [value, setValue] = React.useState(0);
  const handleChange = (_event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  return (
    <WBox sx={{ width: '100%' }}>
    <WBox sx={{ borderBottom: 1, borderColor: 'divider' }}>
      <WTabs {...args} value={value} onChange={handleChange} aria-label="basic tabs example">
        <WTab label="Item One" {...a11yProps(0)} />
        <WTab label="Item Two" {...a11yProps(1)} />
        <WTab label="Item Three" {...a11yProps(2)} />
        <WTab label="Item 4" disabled={true} {...a11yProps(3)} />
        <WTab label="Item 5" {...a11yProps(4)} />
        <WTab label="Item Lorem Text text text long text" {...a11yProps(5)} />
      </WTabs>
    </WBox>
    <TabPanel value={value} index={0}>
      Item One
    </TabPanel>
    <TabPanel value={value} index={1}>
      Item Two
    </TabPanel>
    <TabPanel value={value} index={2}>
      Item Three
    </TabPanel>
    <TabPanel value={value} index={3}>
      Item 4
    </TabPanel>
    <TabPanel value={value} index={4}>
      Item 5
    </TabPanel>
    <TabPanel value={value} index={5}>
      Item Lorem text text Lorem Text
    </TabPanel>
  </WBox>
    );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
export const Experimental = TemplateExperimental.bind({});

Default.args = {
  orientation: 'horizontal',
  variant: 'standard',
  textColor: 'primary',
  size: 'medium',
  scrollButtons: false,
  centered: false,
  visibleScrollbar: false,
};
