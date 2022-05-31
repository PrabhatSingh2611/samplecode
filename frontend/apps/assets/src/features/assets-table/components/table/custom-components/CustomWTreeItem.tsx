import { WTreeView, wStyled } from 'wdx';

export const CustomWTreeItem = wStyled(WTreeView.Item)(() => ({
    '& .MuiTreeItem-content': {
        flexDirection: 'row-reverse',
    },
}));
