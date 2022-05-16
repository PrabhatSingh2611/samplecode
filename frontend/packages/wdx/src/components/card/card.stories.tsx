
import { Meta, Story } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import React from 'react';
import WButton from '../button';
import WTypography from '../typography';
import WCard, { WCardActionArea, WCardActions, WCardContent, WCardHeader, WCardMedia } from './index';

const meta: Meta = {
  title: 'MUI/Surfaces/Card',
  component: WCard,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const cardData = {
    title: 'Card title',
    subtitle: 'Card subtitle',
    description: 'Card description',
    imageSrc: 'https://images.unsplash.com/photo-1551963831-b3b1ca40c98e',
    actions: <WButton>Confirm</WButton>
}

const TemplateDefault: Story = args => {
  return (
        <WCard {...args}>
            <WCardContent>
                <WTypography variant="body2" color="text.secondary">
                    {cardData.description}
                </WTypography>
            </WCardContent>
        </WCard>
  )
}
const TemplateWithHeader: Story = args => {
    return (
        <WCard {...args}>
            <WCardHeader
                title={cardData.title}
                subheader={cardData.subtitle}
            />
            <WCardContent>
                <WTypography variant="body2" color="text.secondary">
                    {cardData.description}
                </WTypography>
            </WCardContent>
        </WCard>
    )
}

const TemplateWithImage: Story = args => {
    return (
        <WCard {...args}>
            <WCardHeader
                title={cardData.title}
                subheader={cardData.subtitle}
            />
            <WCardMedia
                // TODO: figure out with type (YD)
                // @ts-ignore
                component="img"
                height="194"
                image={cardData.imageSrc}
                alt="breakfast"
            />
            <WCardContent>
                <WTypography variant="body2" color="text.secondary">
                    {cardData.description}
                </WTypography>
            </WCardContent>
        </WCard>
    )
}

const TemplateWithActions: Story = args => {
    return (
        <WCard {...args}>
            <WCardHeader
                title={cardData.title}
                subheader={cardData.subtitle}
            />
            <WCardMedia
                // TODO: figure out with type (YD)
                // @ts-ignore
                component="img"
                height="194"
                image={cardData.imageSrc}
                alt="breakfast"
            />
            <WCardContent>
                <WTypography variant="body2" color="text.secondary">
                    {cardData.description}
                </WTypography>
            </WCardContent>
            <WCardActions>
                {cardData.actions}
            </WCardActions>
        </WCard>
    )
}

const TemplateWithActionArea: Story = args => {
    return (
        <WCard {...args}>
            <WCardActionArea onClick={action('OnClick')}>
                <WCardMedia
                    // TODO: figure out with type (YD)
                    // @ts-ignore
                    component="img"
                    height="194"
                    image={cardData.imageSrc}
                    alt="breakfast"
                />
                <WCardContent>
                    <WTypography variant="body2" color="text.secondary">
                        {cardData.description}
                    </WTypography>
                </WCardContent>
            </WCardActionArea>
            <WCardActions>
                {cardData.actions}
            </WCardActions>
        </WCard>
    )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = TemplateDefault.bind({});
export const CardWithHeader = TemplateWithHeader.bind({});
export const CardWithImage = TemplateWithImage.bind({});
export const CardWithActions = TemplateWithActions.bind({});
export const CardWithActionArea = TemplateWithActionArea.bind({});
