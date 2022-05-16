import React from "react";

import Card, { CardProps } from '@mui/material/Card';
import CardActions, { CardActionsProps } from '@mui/material/CardActions';
import CardActionArea, {CardActionAreaProps } from "@mui/material/CardActionArea";
import CardContent, { CardContentProps } from '@mui/material/CardContent';
import CardHeader, { CardHeaderProps } from '@mui/material/CardHeader';
import CardMedia, { CardMediaProps } from '@mui/material/CardMedia';

export interface WCardProps extends CardProps {}

export interface WCardActionsProps extends CardActionsProps {}

export interface WCardActionAreaProps extends CardActionAreaProps {}

export interface WCardContentProps extends CardContentProps {}

export interface WCardHeaderProps extends CardHeaderProps {}

export interface WCardMediaProps extends CardMediaProps {}


export function WCardActions(props: WCardActionsProps):JSX.Element {
    return <CardActions {...props} />;
}

export function WCardActionArea(props: WCardActionAreaProps):JSX.Element {
    return <CardActionArea {...props} />;
}

export function WCardContent(props: WCardContentProps):JSX.Element {
    return <CardContent {...props} />;
}

export function WCardHeader(props: WCardHeaderProps):JSX.Element {
    return <CardHeader {...props} />;
}

export function WCardMedia(props: WCardMediaProps):JSX.Element {
    return <CardMedia {...props} />;
}

function WCard(props: WCardProps):JSX.Element {
    return <Card {...props} />;
}

export default WCard;