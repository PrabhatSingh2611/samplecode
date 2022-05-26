import React from 'react';
import { TreeView, TreeViewProps } from '@mui/lab';

export type WTreeViewProps = TreeViewProps;

function WTreeView(props: WTreeViewProps): JSX.Element {
  return <TreeView {...props} />;
}

export default WTreeView;
