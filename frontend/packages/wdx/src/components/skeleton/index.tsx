import React from 'react';

import { Skeleton, SkeletonProps } from '@mui/material';

export type IWSkeletonProps = SkeletonProps;

export default function WSkeleton(props: IWSkeletonProps): JSX.Element {
  return <Skeleton {...props} />;
}
