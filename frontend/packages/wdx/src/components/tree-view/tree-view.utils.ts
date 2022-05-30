import { IGetOnChange, ILocation, IRenderOptions, ISelected } from './types';

function getChildById(
  optionsList: IRenderOptions[],
  nodeId: string
): ISelected[] | [] {
  let array: ISelected[] = [];

  function getAllChild<R extends IRenderOptions>(nodes: R | null): ISelected[] {
    if (nodes === null) {
      return [];
    }

    array.push({ id: nodes.id, name: nodes.name });

    if (Array.isArray(nodes.locations)) {
      nodes.locations.map((node) => {
        const arrWithChild = [...array, ...getAllChild(node)];

        return arrWithChild.filter((v, i) => array.indexOf(v) === i);
      });
    }

    return array;
  }

  function getNodeById<F extends IRenderOptions>(
    optionsList: F[],
    nodeId: string
  ): F | ILocation | null {
    for (const node of optionsList) {
      if (node.id === nodeId) {
        return node;
      }

      if (Array.isArray(node.locations)) {
        const childNode = getNodeById(node.locations, nodeId);

        if (childNode) {
          return childNode;
        }
      }
    }

    return null;
  }

  return getAllChild(getNodeById(optionsList, nodeId));
}

export function setSelectedOnChange<T extends IRenderOptions>({
  checked,
  node,
  selected,
  setSelected,
  optionsList,
}: IGetOnChange<T>): void {
  const allNode: ISelected[] = getChildById(optionsList, node.id);

  const array = checked
    ? [...selected, ...allNode]
    : selected.filter((value) => {
        return allNode.every((node) => node.id !== value.id);
      });

  const selectedValues = array.filter((v, i) => array.indexOf(v) === i);

  setSelected(selectedValues);
}
