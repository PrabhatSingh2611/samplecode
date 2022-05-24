import React from "react";

import Tabs, { TabsProps } from '@mui/material/Tabs';
import Tab, { TabProps } from '@mui/material/Tab';
import TabContext, { TabContextProps } from '@mui/lab/TabContext';
import TabList, { TabListProps } from '@mui/lab/TabList';
import TabPanel, { TabPanelProps } from '@mui/lab/TabPanel';
import TabScrollButton, { TabScrollButtonProps } from '@mui/material/TabScrollButton';

// Experimental Components
export interface WTabContextProps extends TabContextProps {}
export function WTabContext(props: WTabContextProps):JSX.Element {
    return <TabContext {...props} />;
}

export interface WTabListProps extends TabListProps {}
export function WTabList(props: WTabListProps):JSX.Element {
    return <TabList {...props} />;
}

// Base Components
export interface WTabProps extends TabProps {}
export function WTab(props: WTabProps):JSX.Element {
    return <Tab {...props} />;
}

export interface WTabPanelProps extends TabPanelProps {}
export function WTabPanel(props: WTabPanelProps):JSX.Element {
    return <TabPanel {...props} />;
}

export interface WTabScrollButtonProps extends TabScrollButtonProps {}
export function WTabScrollButton(props: WTabScrollButtonProps):JSX.Element {
    return <TabScrollButton {...props} />;
}

export interface WTabsProps extends TabsProps {}
function WTabs(props: WTabsProps):JSX.Element {
    return <Tabs {...props} />;
}

export default WTabs;