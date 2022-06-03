
import { Meta, Story } from '@storybook/react';
import React, { useState } from 'react';
import WBox from '../../components/box';
import WCard, { WCardContent, WCardHeader } from '../../components/card';
import WContainer from '../../components/container';
import WGrid from '../../components/grid';
import WMarkdown from '../../components/markdown';
import WStack from '../../components/stack';
import WTypography from '../../components/typography';
import WEditor from './index';

const meta: Meta = {
  title: 'Widgets/Editor',
  component: WEditor,
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

const Template: Story = (args) => {
  const [quillSimple, setQuillSimple] = useState('');
  const [quillFull, setQuillFull] = useState('');

  return (
    <WContainer>
      <WGrid container spacing={3}>
        <WGrid item xs={12} md={8}>
          <WCard>
            <WCardHeader title="Editor Simple" />
            <WCardContent>
              <WEditor
                {...args}
                simple={true}
                id="simple-editor"
                value={quillSimple}
                onChange={(value) => setQuillSimple(value)}
              />
            </WCardContent>
          </WCard>
        </WGrid>

        <WGrid item xs={12} md={4}>
          <WStack spacing={3} sx={{ height: 1 }}>
            <WCard sx={{ height: 1, boxShadow: 0, bgcolor: 'background.neutral' }}>
              <WCardHeader title="Preview Plain Text" />
              <WBox sx={{ p: 3 }}>
                <WMarkdown children={quillSimple} />
              </WBox>
            </WCard>
            <WCard sx={{ height: 1, boxShadow: 0, bgcolor: 'background.neutral' }}>
              <WCardHeader title="Preview Html" />
              <WTypography sx={{ p: 3 }}>{quillSimple}</WTypography>
            </WCard>
          </WStack>
        </WGrid>
      </WGrid>

      <WGrid container sx={{ mt: 3 }}>
        <WGrid item xs={12} md={8}>
          <WCard>
            <WCardHeader title="Editor Full" />
            <WCardContent>
              <WEditor
                id="full-editor"
                value={quillFull}
                onChange={(value: any) => setQuillFull(value)}
                helperText={<WTypography pt={2}>Helper text</WTypography>}
              />
            </WCardContent>
          </WCard>
        </WGrid>
      </WGrid>
    </WContainer>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
