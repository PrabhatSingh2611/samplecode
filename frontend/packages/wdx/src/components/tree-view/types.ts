export interface ILocation {
  id: string;
  country: string;
  name: string;
}

export interface IRenderOptions {
  id: string;
  name: string;
  locations?: ILocation[];
}

export interface ISelected {
  id: string;
  name: string;
}

export interface IGetOnChange<T extends IRenderOptions> {
  checked: boolean;
  node: T;
  selected: ISelected[];
  setSelected: (selected: ISelected[]) => void;
  optionsList: T[];
}
