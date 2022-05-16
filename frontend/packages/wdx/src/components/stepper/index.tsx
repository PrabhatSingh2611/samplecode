import React from "react";

import MobileStepper, { MobileStepperProps } from '@mui/material/MobileStepper';
import Step, { StepProps } from '@mui/material/Step';
import StepButton, { StepButtonProps } from '@mui/material/StepButton';
import StepConnector, { StepConnectorProps } from '@mui/material/StepConnector';
import StepContent, { StepContentProps } from '@mui/material/StepContent';
import StepIcon, { StepIconProps } from '@mui/material/StepIcon';
import StepLabel, { StepLabelProps } from '@mui/material/StepLabel';
import Stepper, { StepperProps } from '@mui/material/Stepper';

export interface WStepperProps extends StepperProps {}
export interface WMobileStepperProps extends MobileStepperProps {}
export interface WStepProps extends StepProps {}
export interface WStepButtonProps extends StepButtonProps {}
export interface WStepConnectorProps extends StepConnectorProps {}
export interface WStepContentProps extends StepContentProps {}
export interface WStepIconProps extends StepIconProps {}
export interface WStepLabelProps extends StepLabelProps {}

export function WMobileStepper(props: WMobileStepperProps):JSX.Element {
    return <MobileStepper {...props} />;
}

export function WStep(props: WStepProps):JSX.Element {
    return <Step {...props} />;
}

export function WStepButton(props: StepButtonProps):JSX.Element {
    return <StepButton {...props} />;
}

export function WStepConnector(props: WStepConnectorProps):JSX.Element {
    return <StepConnector {...props} />;
}

export function WStepContent(props: WStepContentProps):JSX.Element {
    return <StepContent {...props} />;
}

export function WStepIcon(props: WStepIconProps):JSX.Element {
    return <StepIcon {...props} />;
}

export function WStepLabel(props: WStepLabelProps):JSX.Element {
    return <StepLabel {...props} />;
}

function WStepper(props: WStepperProps):JSX.Element {
    return <Stepper {...props} />;
}

export default WStepper;