import { Meta, Story } from '@storybook/react';
import React from 'react';
import WMarkdown from './index';
import WStack from '../../components/stack';
import WTypography from '../../components/typography';
import WCard, { WCardHeader } from '../card';
import WBox from '../box';

const meta: Meta = {
  title: 'Extra Components/Markdown',
  component: WMarkdown,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = args => {

  return (
    <WStack direction="row" spacing={3} justifyContent="space-evenly">
      <WCard sx={{ boxShadow: 0, bgcolor: 'background.neutral' }}>
        <WCardHeader title="Preview Plain Text" />
        <WBox sx={{ p: 3 }}>
          <WMarkdown {...args} children="<h1>Hi</h1><img src='https://picsum.photos/500' /><hr/><ol><li>Item</li><li>Item</li><li>Item</li></ol>" />
        </WBox>
      </WCard>
      <WCard sx={{ height: 1, boxShadow: 0, bgcolor: 'background.neutral' }}>
        <WCardHeader title="Preview Html" />
        <WTypography component="pre" sx={{ p: 3 }}>
          {`<h1>Hi</h1>
                <hr/>
                <img src="https://picsum.photos/100" />
                <ol>
                  <li>Item</li>
                  <li>Item</li>
                  <li>Item</li>
                </ol>`
          }
        </WTypography>
      </WCard>
    </WStack>
  );
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
