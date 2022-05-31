import React, { useEffect } from 'react';

import { WCheckbox, WForm, WIcon, WTreeView } from 'wdx';

import { ITypeOptions, TYPE_OPTIONS } from 'const/assets-table';
import { CustomWTreeItem } from 'features/assets-table/components/table/custom-components/CustomWTreeItem';
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
            <CustomWTreeItem
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
            </CustomWTreeItem>
        ));

    return (
        <WTreeView
            defaultCollapseIcon={<WIcon name="expand-less" sx={{ width: 20, height: 20 }} />}
            defaultExpandIcon={<WIcon name="expand-more" sx={{ width: 20, height: 20 }} />}
        >
            {renderTree(TYPE_OPTIONS)}
        </WTreeView>
    );
}
