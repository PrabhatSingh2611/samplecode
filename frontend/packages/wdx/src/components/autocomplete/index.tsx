import React from 'react';
import {
  Autocomplete,
  AutocompleteProps,
  AutocompleteRenderInputParams,
  createFilterOptions,
} from '@mui/material';
import { AutocompleteValue } from '@mui/base';

export const WCreateFilterOptions = createFilterOptions;
export interface WAutocompleteRenderInputParams
  extends AutocompleteRenderInputParams {}

export type WAutocompleteValue = AutocompleteValue<
  unknown,
  WAutocompleteProp,
  WAutocompleteProp,
  WAutocompleteProp
>;

export interface WAutocompleteProps<
  T,
  Multiple extends boolean | undefined,
  DisableClearable extends boolean | undefined,
  FreeSolo extends boolean | undefined
> extends AutocompleteProps<T, Multiple, DisableClearable, FreeSolo> {}

export type WAutocompleteProp = boolean | undefined;

const WAutocomplete = React.forwardRef<
  HTMLSelectElement,
  WAutocompleteProps<
    unknown,
    WAutocompleteProp,
    WAutocompleteProp,
    WAutocompleteProp
  >
>((props, ref): JSX.Element => {
  return <Autocomplete {...props} ref={ref} />;
});

export default WAutocomplete;
