import { IRenderOptions } from 'const/AssetsTable.const';

function getChildById<T extends IRenderOptions>(data: T[], nodeId: string): ISelected[] | [] {
    let array: ISelected[] = [];

    function getAllChild<R extends IRenderOptions>(nodes: R | null): ISelected[] {
        if (nodes === null) {
            return [];
        }

        array.push({ id: nodes.id, name: nodes.name });
        if (Array.isArray(nodes.children)) {
            nodes.children.forEach((node) => {
                array = [...array, ...getAllChild(node)];
                array = array.filter((v, i) => array.indexOf(v) === i);
            });
        }

        return array;
    }

    function getNodeById<F extends IRenderOptions>(nodes: F[], id: string): F | null {
        let result: IRenderOptions | null = null;

        nodes.forEach((node) => {
            if (node.id === id) {
                return (result = node);
            } else if (Array.isArray(node.children)) {
                if (getNodeById(node.children, id)) {
                    result = getNodeById(node.children, id);
                }
            }

            return null;
        });

        return result;
    }

    return getAllChild(getNodeById(data, nodeId));
}

export interface ISelected {
    id: string;
    name: string;
}

interface IGetOnChange<T extends IRenderOptions> {
    checked: boolean;
    nodes: T;
    selected: ISelected[];
    setSelected: (selected: ISelected[]) => void;
    optionsList: T[];
}

export function getOnChange<T extends IRenderOptions>({
    checked,
    nodes,
    selected,
    setSelected,
    optionsList,
}: IGetOnChange<T>): void {
    const allNode: ISelected[] = getChildById(optionsList, nodes.id);

    let array = checked
        ? [...selected, ...allNode]
        : selected.filter((value) => {
              return allNode.every((node) => node.id !== value.id);
          });

    array = array.filter((v, i) => array.indexOf(v) === i);

    setSelected(array);
}
