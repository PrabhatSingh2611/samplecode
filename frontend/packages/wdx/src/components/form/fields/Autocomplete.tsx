import { Controller, useFormContext } from 'react-hook-form';
import React, { HTMLAttributes, SyntheticEvent } from 'react';

import WAutocomplete, {
  WAutocompleteProps,
  WAutocompleteRenderInputParams,
  WAutocompleteValue,
} from '../../autocomplete';
import { AutocompleteFreeSoloValueMapping } from '@mui/base';
import { WFieldError } from './FormDatePicker';
import { WTextFieldProps } from '../../text-field';

export type IAutocompleteFreeSoloValueMapping<
  FreeSolo extends boolean | undefined = undefined
> = AutocompleteFreeSoloValueMapping<FreeSolo>;

type WFormAutocompleteProps<
  T,
  Multiple extends boolean | undefined = undefined,
  DisableClearable extends boolean | undefined = undefined,
  FreeSolo extends boolean | undefined = undefined
> = Omit<
  WAutocompleteProps<T, Multiple, DisableClearable, FreeSolo>,
  'renderInput'
> & {
  name: string;
  options: T[];
  getOptionLabel: (
    option: any | IAutocompleteFreeSoloValueMapping<FreeSolo>
  ) => string;
  renderInput: (
    props: WTextFieldProps,
    error?: WFieldError
  ) => React.ReactElement;
  renderOption?: (
    props: HTMLAttributes<HTMLLIElement>,
    option: any
  ) => React.ReactElement;
  freeSolo?: boolean;
  isFilter?: boolean;
};

function WFormAutocomplete<
  T,
  Multiple extends boolean | undefined = undefined,
  DisableClearable extends boolean | undefined = undefined,
  FreeSolo extends boolean | undefined = undefined
>({
  name,
  options,
  getOptionLabel,
  renderInput,
  freeSolo,
  renderOption,
}: WFormAutocompleteProps<
  T,
  Multiple,
  DisableClearable,
  FreeSolo
>): JSX.Element {
  const { control } = useFormContext();

  return (
    <Controller
      name={name}
      control={control}
      render={({ field, fieldState: { error } }) => (
        <WAutocomplete
          {...field}
          options={options}
          getOptionLabel={getOptionLabel}
          renderOption={renderOption}
          renderInput={(
            props: WAutocompleteRenderInputParams
          ): React.ReactElement => renderInput(props, error)}
          isOptionEqualToValue={(option: any, value: any) =>
            option.id === value.id
          }
          onChange={(_: SyntheticEvent, data: WAutocompleteValue) =>
            field.onChange(data)
          }
          onInputChange={(_: SyntheticEvent, data: string) =>
            freeSolo && field.onChange(data)
          }
        />
      )}
    />
  );
}

export default WFormAutocomplete;
