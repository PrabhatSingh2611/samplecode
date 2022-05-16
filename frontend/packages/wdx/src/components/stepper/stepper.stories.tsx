import { Meta, Story } from '@storybook/react';
import React, { ReactNode, useState } from 'react';
import WBox from '../box';
import WButton from '../button';
import WTypography from '../typography';
import WPaper from '../paper';
import WStepper, { WMobileStepper, WStep, WStepLabel } from './index';
import Box from '../box';

const meta: Meta = {
    title: 'MUI/Navigation/Stepper',
    component: WStepper,
    parameters: {
        controls: { expanded: true },
    },
};

export default meta;

const steps = ['Select campaign settings', 'Create an ad group', 'Create an ad'];

const stepsMob = [
    {
        label: 'Select campaign settings',
        description: `For each ad campaign that you create, you can control how much
                you're willing to spend on clicks and conversions, which networks
                and geographical locations you want your ads to show on, and more.`,
    },
    {
        label: 'Create an ad group',
        description:
            'An ad group contains one or more ads which target a shared set of keywords.',
    },
    {
        label: 'Create an ad',
        description: `Try out different ad text to see what brings in the most customers,
                and learn how to enhance your ads using features like ad extensions.
                If you run into any problems with your ads, find out how to tell if
                they're running and how to resolve approval issues.`,
    },
];

const TemplateDefault: Story = args => {
    const [activeStep, setActiveStep] = useState(0);
    const [skipped, setSkipped] = useState(new Set<number>());

    const isStepOptional = (step: number) => {
        return step === 1;
    };

    const isStepSkipped = (step: number) => {
        return skipped.has(step);
    };

    const handleNext = () => {
        let newSkipped = skipped;
        if (isStepSkipped(activeStep)) {
            newSkipped = new Set(newSkipped.values());
            newSkipped.delete(activeStep);
        }

        setActiveStep((prevActiveStep) => prevActiveStep + 1);
        setSkipped(newSkipped);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    const handleSkip = () => {
        if (!isStepOptional(activeStep)) {
            // You probably want to guard against something like this,
            // it should never occur unless someone's actively trying to break something.
            throw new Error("You can't skip a step that isn't optional.");
        }

        setActiveStep((prevActiveStep) => prevActiveStep + 1);
        setSkipped((prevSkipped) => {
            const newSkipped = new Set(prevSkipped.values());
            newSkipped.add(activeStep);
            return newSkipped;
        });
    };

    const handleReset = () => {
        setActiveStep(0);
    };

    return (
        <WPaper elevation={4} sx={{ p: 3 }}>
            <WStepper {...args} activeStep={activeStep}>
                {steps.map((label, index) => {
                    const stepProps: { completed?: boolean } = {};
                    const labelProps: {
                        optional?: ReactNode;
                    } = {};
                    if (isStepOptional(index)) {
                        labelProps.optional = (
                            <WTypography variant="caption">Optional</WTypography>
                        );
                    }
                    if (isStepSkipped(index)) {
                        stepProps.completed = false;
                    }
                    return (
                        <WStep key={label} {...stepProps}>
                            <WStepLabel {...labelProps}>{label}</WStepLabel>
                        </WStep>
                    );
                })}
            </WStepper>
            {activeStep === steps.length ? (
                <>
                    <WTypography sx={{ mt: 2, mb: 1 }}>
                        All steps completed - you&apos;re finished
                    </WTypography>
                    <WBox sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                        <WBox sx={{ flex: '1 1 auto' }} />
                        <WButton onClick={handleReset}>Reset</WButton>
                    </WBox>
                </>
            ) : (
                <>
                    <WTypography sx={{ mt: 2, mb: 1 }}>Step {activeStep + 1}</WTypography>
                    <WBox sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                        <WButton
                            color="inherit"
                            disabled={activeStep === 0}
                            onClick={handleBack}
                            sx={{ mr: 1 }}
                        >
                            Back
                        </WButton>
                        <WBox sx={{ flex: '1 1 auto' }} />
                        {isStepOptional(activeStep) && (
                            <WButton color="inherit" onClick={handleSkip} sx={{ mr: 1 }}>
                                Skip
                            </WButton>
                        )}
                        <WButton onClick={handleNext}>
                            {activeStep === steps.length - 1 ? 'Finish' : 'Next'}
                        </WButton>
                    </WBox>
                </>
            )}
        </WPaper>
    )
}

const TemplateTextMobileStepper: Story = args => {
    const [activeStep, setActiveStep] = React.useState(0);
    const maxSteps = steps.length;

    const handleNext = () => {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };
    return (
        <WBox sx={{ maxWidth: 400, flexGrow: 1, border: 1, m: 'auto' }}>
            <WPaper
                square
                elevation={3}
                sx={{
                    display: 'flex',
                    alignItems: 'center',
                    height: 50,
                    pl: 2,
                    bgcolor: 'background.default',
                }}
            >
                <WTypography>{stepsMob[activeStep].label}</WTypography>
            </WPaper>
            <Box sx={{ height: 255, maxWidth: 400, width: '100%', p: 2 }}>
                {stepsMob[activeStep].description}
            </Box>
            <WMobileStepper
                {...args}
                variant="text"
                steps={maxSteps}
                position="static"
                activeStep={activeStep}
                nextButton={
                    <WButton
                        size="small"
                        onClick={handleNext}
                        disabled={activeStep === maxSteps - 1}
                    >
                        Next
                    </WButton>
                }
                backButton={
                    <WButton size="small" onClick={handleBack} disabled={activeStep === 0}>
                        Back
                    </WButton>
                }
            />
        </WBox>
    )
}

const TemplateProgressStepper: Story = args => {
    const [activeStep, setActiveStep] = React.useState(0);

    const handleNext = () => {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };
    return (
        <WPaper
            elevation={3}
            sx={{
                p: 3,
                m: 'auto',
                maxWidth: 400
            }}>
            <WMobileStepper
                {...args}
                variant="progress"
                steps={6}
                position="static"
                activeStep={activeStep}
                sx={{ maxWidth: 400, flexGrow: 1 }}
                nextButton={
                    <WButton size="small" onClick={handleNext} disabled={activeStep === 5}>
                        Next
                    </WButton>
                }
                backButton={
                    <WButton size="small" onClick={handleBack} disabled={activeStep === 0}>
                        Back
                    </WButton>
                }
            />
        </WPaper>
    )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = TemplateDefault.bind({});
export const TextMobileStepper = TemplateTextMobileStepper.bind({});
export const ProgressStepper = TemplateProgressStepper.bind({});

Default.args = {
    alternativeLabel: false,
    nonLinear: false,
    orientation: 'horizontal'
};

TextMobileStepper.args = {
    alternativeLabel: false,
    nonLinear: false,
    orientation: 'horizontal'
};