import { Meta, Story } from '@storybook/react';
import React from 'react';
import { TreeViewCollapseIcon } from '../../theme/overrides/CustomIcons';
import WList from './index';
import WBox from '../box';
import WDivider from '../divider';

const meta: Meta = {
    title: 'MUI/Data Display/List',
    component: WList,
    parameters: {
        controls: { expanded: true },
    },
};

export default meta;

const TemplateDefault: Story = args => {

    return (
        <WBox sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.neutral', m: 'auto' }}>
            <nav aria-label="main mailbox folders">
                <WList {...args}>
                    <WList.Item disablePadding>
                        <WList.ItemButton>
                            <WList.ItemIcon>
                                <TreeViewCollapseIcon />
                            </WList.ItemIcon>
                            <WList.ItemText primary="Inbox" />
                        </WList.ItemButton>
                    </WList.Item>
                    <WList.Item disablePadding>
                        <WList.ItemButton>
                            <WList.ItemIcon>
                                <TreeViewCollapseIcon />
                            </WList.ItemIcon>
                            <WList.ItemText primary="Drafts" />
                        </WList.ItemButton>
                    </WList.Item>
                </WList>
            </nav>
            <WDivider />
            <nav aria-label="secondary mailbox folders">
                <WList>
                    <WList.Item disablePadding>
                        <WList.ItemButton>
                            <WList.ItemText primary="Trash" />
                        </WList.ItemButton>
                    </WList.Item>
                    <WList.Item disablePadding>
                        <WList.ItemButton>
                            <WList.ItemText primary="Spam" />
                        </WList.ItemButton>
                    </WList.Item>
                </WList>
            </nav>
        </WBox>
    );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = TemplateDefault.bind({});