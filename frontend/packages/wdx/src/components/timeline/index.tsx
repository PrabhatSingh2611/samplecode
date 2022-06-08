import React from 'react';
import Timeline from '@mui/lab/Timeline';
import TimelineItem, { TimelineItemProps } from '@mui/lab/TimelineItem';
import TimelineConnector, {
  TimelineConnectorProps,
} from '@mui/lab/TimelineConnector';
import TimelineContent, {
  TimelineContentProps,
} from '@mui/lab/TimelineContent';
import TimelineDot, { TimelineDotProps } from '@mui/lab/TimelineDot';
import TimelineOppositeContent, {
  TimelineOppositeContentProps,
} from '@mui/lab/TimelineOppositeContent';
import TimelineSeparator, {
  TimelineSeparatorProps,
} from '@mui/lab/TimelineSeparator';

export interface WTimelineProps extends React.ComponentProps<typeof Timeline> {}

export interface WTimelineItemProps extends TimelineItemProps {}
export interface WTimelineConnectorProps extends TimelineConnectorProps {}
export interface WTimelineContentProps extends TimelineContentProps {}
export interface WTimelineDotProps extends TimelineDotProps {}
export interface WTimelineOppositeContentProps
  extends TimelineOppositeContentProps {}
export interface WTimelineSeparatorProps extends TimelineSeparatorProps {}

function WTimeline (props: WTimelineProps): JSX.Element {
    return <Timeline {...props} />;
}


WTimeline.Item = function WTimelineItem(props: WTimelineItemProps): JSX.Element {
  return <TimelineItem {...props} />;
}

WTimeline.Connector = function WTimelineConnector(props: WTimelineConnectorProps) {
  return <TimelineConnector {...props} />;
}

WTimeline.Content = function WTimelineContent(props: WTimelineContentProps) {
  return <TimelineContent {...props} />;
}

WTimeline.Dot = function WTimelineDot(props: WTimelineDotProps) {
  return <TimelineDot {...props} />;
}

WTimeline.OppositeContent = function WTimelineOppositeContent(props: WTimelineOppositeContentProps) {
  return <TimelineOppositeContent {...props} />;
}

WTimeline.Separator = function WTimelineSeparator(props: WTimelineSeparatorProps) {
  return <TimelineSeparator {...props} />;
}

export default WTimeline;
