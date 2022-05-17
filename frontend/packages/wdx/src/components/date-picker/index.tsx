import React from "react";

import {
    DatePicker, DateTimePicker, LocalizationProvider, LocalizationProviderProps, DatePickerProps, DateTimePickerProps
} from '@mui/lab';

export { default as WAdapterDateFns } from '@mui/lab/AdapterDateFns';

export interface WDatePickerProps extends DatePickerProps {}

function WDatePicker(props: WDatePickerProps): JSX.Element {
    return <DatePicker {...props} />;
}

export interface WDateTimePickerProps extends DateTimePickerProps {}

export function WDateTimePicker(props: WDateTimePickerProps): JSX.Element {
    return <DateTimePicker {...props} />;
}

export interface WLocalizationProviderProps extends LocalizationProviderProps {}

export function WLocalizationProvider(props: WLocalizationProviderProps): JSX.Element {
    return <LocalizationProvider {...props} />
}

export default WDatePicker;