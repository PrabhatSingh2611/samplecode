import React from "react";

import Collapse, { CollapseProps } from '@mui/material/Collapse';
import Fade, { FadeProps } from '@mui/material/Fade';
import Grow, { GrowProps } from '@mui/material/Grow';
import Slide, { SlideProps } from '@mui/material/Slide';
import Zoom, { ZoomProps } from '@mui/material/Zoom';

export interface WCollapseProps extends CollapseProps {}
export interface WFadeProps extends FadeProps {}
export interface WGrowProps extends GrowProps {}
export interface WSlideProps extends SlideProps {}
export interface WZoomProps extends ZoomProps {}

export function WTansitionFade(props: WFadeProps):JSX.Element {
    return <Fade {...props} />;
}

export function WTansitionGrow(props: WGrowProps):JSX.Element {
    return <Grow {...props} />;
}

export function WTansitionSlide(props: WSlideProps):JSX.Element {
    return <Slide {...props} />;
}

export function WTansitionZoom(props: WZoomProps):JSX.Element {
    return <Zoom {...props} />;
}

export function WTansitionCollapse(props: WCollapseProps):JSX.Element {
    return <Collapse {...props} />;
}

export default WTansitionCollapse;