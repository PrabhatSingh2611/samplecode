import React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import WDivider from '../../components/divider';
import { SxProps } from '@mui/material';
import { Theme } from '@mui/material/styles';

export interface IWTab {
  id: string;
  label: string;
}

export interface IWTabs {
  activeTab?: string;
  onTabChange: (id: string) => void;
  tabs: IWTab[];
  allowScrollButtonsMobile?: boolean;
  variant?: string;
  scrollButtons?: any;
  sx?: SxProps<Theme>;
}

export default function WTabs({
  activeTab,
  onTabChange,
  tabs,
  sx,
}: IWTabs): JSX.Element {
  return (
    <>
      <Tabs
        value={activeTab}
        // TODO :Fix type (vs)
        onChange={(_: any, value: string): void => {
          onTabChange(value);
        }}
        sx={{ px: 2, bgcolor: 'background.neutral', ...sx }}
      >
        {tabs.map(
          (tab: any): JSX.Element => (
            <Tab disableRipple key={tab.id} label={tab.label} value={tab.id} />
          )
        )}
      </Tabs>
      <WDivider />
    </>
  );
}
