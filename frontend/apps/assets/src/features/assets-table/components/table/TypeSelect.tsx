import React, { useEffect } from 'react';

import { WIcon, WTreeView, WTreeItem, wStyled, WCheckbox, WForm } from 'wdx';

import { ITypeOptions, TYPE_OPTIONS } from 'const/AssetsTable.const';
import { getOnChange, ISelected } from 'features/assets-table/utils/renderOptions.utils';

interface ITypeSelect {
    setTypes: (value: ISelected[]) => void;
}

export default function TypeSelect({ setTypes }: ITypeSelect): JSX.Element {
    const [selected, setSelected] = React.useState<ISelected[]>([]);

    useEffect(() => {
        setTypes(selected);
    }, [selected, setTypes]);

    const renderTree = (nodes: ITypeOptions[]): JSX.Element[] =>
        nodes.map((node) => (
            <WTreeItem
                key={node.id}
                nodeId={node.id}
                label={
                    <WForm.ControlLabel
                        control={
                            <WCheckbox
                                checked={selected.some((element) => element.id === node.id)}
                                onChange={(event: React.FormEvent<HTMLInputElement>): void =>
                                    getOnChange({
                                        checked: event.currentTarget.checked,
                                        nodes: node,
                                        selected,
                                        setSelected,
                                        optionsList: TYPE_OPTIONS,
                                    })
                                }
                                onClick={(e: React.MouseEvent<HTMLButtonElement>): void =>
                                    e.stopPropagation()
                                }
                            />
                        }
                        label={<>{node.name}</>}
                        key={node.id}
                    />
                }
            >
                {!!Array.isArray(node.children) && renderTree(node.children)}
            </WTreeItem>
        ));

    const StyledWTreeView = wStyled(WTreeView)(() => ({
        '& .MuiTreeItem-root': {
            '& .MuiTreeItem-content': {
                flexDirection: 'row-reverse',
            },
        },
    }));

    return (
        <StyledWTreeView
            defaultCollapseIcon={<WIcon name="expand-less" sx={{ width: 20, height: 20 }} />}
            defaultExpandIcon={<WIcon name="expand-more" sx={{ width: 20, height: 20 }} />}
        >
            {renderTree(TYPE_OPTIONS)}
        </StyledWTreeView>
    );
}
