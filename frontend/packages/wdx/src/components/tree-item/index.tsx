import React from 'react';
import { TreeItem, TreeItemProps } from '@mui/lab';

export type WTreeItemProps = TreeItemProps;

function WTreeItem(props: WTreeItemProps): JSX.Element {
  return <TreeItem {...props} />;
}

export default WTreeItem;
