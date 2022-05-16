import React from "react";

import Accordion, { AccordionProps } from '@mui/material/Accordion';
import AccordionActions, { AccordionActionsProps } from '@mui/material/AccordionActions';
import AccordionDatails, { AccordionDetailsProps } from '@mui/material/AccordionDetails';
import AccordionSummary, { AccordionSummaryProps } from '@mui/material/AccordionSummary';

export interface WAccordionProps extends AccordionProps {}

export interface WAccordionActionsProps extends AccordionActionsProps {}

export interface WAccordionDetailsProps extends AccordionDetailsProps {}

export interface WAccordionSummaryProps extends AccordionSummaryProps {}


export function WAccordionActions(props: WAccordionActionsProps):JSX.Element {
    return <AccordionActions {...props} />;
}

export function WAccordionDetails(props: WAccordionDetailsProps):JSX.Element {
    return <AccordionDatails {...props} />;
}

export function WAccordionSummary(props: WAccordionSummaryProps):JSX.Element {
    return <AccordionSummary {...props} />;
}

function WAccordion(props: WAccordionProps):JSX.Element {
    return <Accordion {...props} />;
}

export default WAccordion;