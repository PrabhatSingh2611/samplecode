import React from 'react';
import { Autocomplete, AutocompleteProps } from '@mui/material';

export interface WAutocompleteProps<
  T,
  Multiple extends boolean | undefined,
  DisableClearable extends boolean | undefined,
  FreeSolo extends boolean | undefined
> extends AutocompleteProps<T, Multiple, DisableClearable, FreeSolo> {}

export default function WAutocomplete<
  T,
  Multiple extends boolean | undefined = undefined,
  DisableClearable extends boolean | undefined = undefined,
  FreeSolo extends boolean | undefined = undefined
>(
  props: WAutocompleteProps<T, Multiple, DisableClearable, FreeSolo>
): JSX.Element {
  return <Autocomplete {...props} />;
}
