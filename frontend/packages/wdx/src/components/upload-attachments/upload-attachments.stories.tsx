import { Meta, Story } from '@storybook/react';
import React, { useState } from 'react';
import WButton from '../button';
import WUploadAttachments from './upload-attachments';

const meta: Meta = {
  title: 'Extra Components/Upload attachments',
  component: WUploadAttachments,
};

export default meta;

const Template: Story = () => {
  return (
    <>
      <WUploadAttachments attachments={null} />
    </>
  );
};

const TemplateSingle: Story = () => {
  return (
    <>
      <WUploadAttachments attachments={null} isSingle={true} />
    </>
  );
};

const TemplateWithCustomTrigger: Story = () => {
  const [inputRef, setInputRef] = useState<React.RefObject<HTMLInputElement>>();
  const onClick = (): void => {
    inputRef?.current?.click();
  };
  const onInputRef = (inputRef: React.RefObject<HTMLInputElement>): void => {
    setInputRef(inputRef);
  };
  return (
    <>
      <WUploadAttachments attachments={null} onInputRef={onInputRef} />
      <WButton onClick={onClick} variant="contained">
        Custon trigger
      </WButton>
    </>
  );
};

const TemplateSingleWithCustomTrigger: Story = () => {
  const [inputRef, setInputRef] = useState<React.RefObject<HTMLInputElement>>();
  const onClick = (): void => {
    inputRef?.current?.click();
  };
  const onInputRef = (inputRef: React.RefObject<HTMLInputElement>): void => {
    setInputRef(inputRef);
  };
  return (
    <>
      <WUploadAttachments
        attachments={null}
        onInputRef={onInputRef}
        isSingle={true}
      />
      <WButton onClick={onClick} variant="contained">
        Custon trigger
      </WButton>
    </>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
export const Single = TemplateSingle.bind({});
export const CustomTriggerMultiple = TemplateWithCustomTrigger.bind({});
export const CustomTriggerSingle = TemplateSingleWithCustomTrigger.bind({});
