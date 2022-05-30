import React, { ReactNode } from 'react';
import { TreeItem, TreeItemProps, TreeView, TreeViewProps } from '@mui/lab';

export interface WTreeItemProps extends TreeItemProps {}

function WTreeItem(props: WTreeItemProps) {
  return <TreeItem {...props} />;
}

export type WTreeViewProps = TreeViewProps;

interface IWTreeViewProps {
  defaultCollapseIcon: JSX.Element;
  defaultExpandIcon: JSX.Element;
  children: ReactNode;
  props?: WTreeViewProps;
}
function WTreeView({
  defaultCollapseIcon,
  defaultExpandIcon,
  children,
  props,
}: IWTreeViewProps) {
  return (
    <TreeView
      defaultCollapseIcon={defaultCollapseIcon}
      defaultExpandIcon={defaultExpandIcon}
      {...props}
    >
      {children}
    </TreeView>
  );
}

WTreeView.Item = WTreeItem;

export default WTreeView;
