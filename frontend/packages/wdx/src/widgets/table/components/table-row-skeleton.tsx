import React from 'react';
import WTable from '../../../components/table';
import WSkeleton from '../../../components/skeleton';

interface IWTableRowSkeletonProps {
  columnLength: number;
}

export function WTableRowSkeleton({
  columnLength,
}: IWTableRowSkeletonProps): JSX.Element {
  return (
    <WTable.Row>
      {Array.from({ length: columnLength }, (_, index) => (
        <WTable.Cell key={index}>
          <WSkeleton variant="text" width="100%" height={40} />
        </WTable.Cell>
      ))}
    </WTable.Row>
  );
}
