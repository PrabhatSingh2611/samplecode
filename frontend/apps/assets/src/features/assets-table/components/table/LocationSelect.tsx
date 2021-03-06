import React, { useEffect } from 'react';

import { WCheckbox, WForm, WIcon, WImage, WStack, WTreeView } from 'wdx';

import { ILocationOptions, LOCATION_OPTIONS_LIST } from 'const/assets-table';
import { CustomWTreeItem } from 'features/assets-table/components/table/custom-components/CustomWTreeItem';
import { getOnChange, ISelected } from 'features/assets-table/utils/renderOptions.utils';

interface ILocationSelect {
    setLocations: (value: ISelected[]) => void;
}

export default function LocationSelect({ setLocations }: ILocationSelect): JSX.Element {
    const [selected, setSelected] = React.useState<ISelected[]>([]);

    useEffect(() => {
        setLocations(selected);
    }, [selected, setLocations]);

    const renderTree = (nodes: ILocationOptions[]): JSX.Element[] =>
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
                                        optionsList: LOCATION_OPTIONS_LIST,
                                    })
                                }
                                onClick={(e: React.MouseEvent<HTMLButtonElement>): void =>
                                    e.stopPropagation()
                                }
                            />
                        }
                        label={
                            <WStack direction="row" alignItems="center">
                                {!!node.flagIcon && (
                                    <WImage
                                        disabledEffect
                                        src={node.flagIcon}
                                        alt="flag icon"
                                        sx={{ width: 24, height: 18, marginRight: 1 }}
                                    />
                                )}
                                {node.name}
                            </WStack>
                        }
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
            {renderTree(LOCATION_OPTIONS_LIST)}
        </WTreeView>
    );
}
