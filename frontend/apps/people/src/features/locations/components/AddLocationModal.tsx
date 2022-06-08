import React, { HTMLAttributes } from 'react';

import {
    WBox,
    WButton,
    WCountryFlag,
    WDialog,
    WFieldError,
    WForm,
    WStack,
    WTextField,
    WTextFieldProps,
    Yup,
} from 'wdx';

import { ALL_COUNTRIES, ICountryType } from 'features/locations/constants/countryCodes';
import {
    useAddNewCountryAndLocation,
    useIsAddLocationModalOpen,
} from 'features/locations/hooks/locationModals.hooks';

export interface INewLocation {
    country: ICountryType;
    location: string;
}

const defaultValues = {
    country: {
        name: '',
        code: '',
    },
    location: '',
};

const validation = Yup.object().shape({
    country: Yup.lazy((value) => {
        switch (typeof value) {
            case 'object':
                return Yup.object({
                    name: Yup.string().required(),
                    code: Yup.string(),
                });
            default:
                return Yup.string().min(2);
        }
    }),
    location: Yup.string().min(2, 'Location is required'),
});

export default function AddLocationModal(): JSX.Element {
    const [isAddLocationModalOpen, setIsAddLocationModalOpen] = useIsAddLocationModalOpen();
    const addNewCountryAndLocation = useAddNewCountryAndLocation();

    const addLocationFormId = 'add-new-location-form';

    const onFormSubmit = (values: INewLocation): void => {
        addNewCountryAndLocation(values);
        closeAddLocationModal();
    };

    const closeAddLocationModal = (): void => {
        setIsAddLocationModalOpen(!isAddLocationModalOpen);
    };

    const renderCountrySelectOption = (
        props: HTMLAttributes<HTMLLIElement>,
        option: ICountryType,
    ): JSX.Element => {
        return (
            <WBox component="li" sx={{ '& > img': { flexShrink: 0, mr: 2 } }} {...props}>
                <WCountryFlag
                    countryCode={option.code}
                    svg={true}
                    style={{
                        width: '1.5em',
                        height: '1.5em',
                        paddingRight: '0.2em',
                    }}
                />
                {option.name}
            </WBox>
        );
    };

    return (
        <WDialog open={isAddLocationModalOpen} onClose={closeAddLocationModal}>
            <WDialog.Title>Add new location</WDialog.Title>
            <WDialog.Content>
                <WForm
                    id={addLocationFormId}
                    validation={validation}
                    defaultValues={defaultValues}
                    onSubmit={onFormSubmit}
                >
                    <WStack spacing={2} sx={{ width: { sm: '500px', xs: 'auto' }, pt: 2.5 }}>
                        <WForm.Control>
                            <WForm.Autocomplete
                                name="country"
                                options={ALL_COUNTRIES}
                                getOptionLabel={(option: ICountryType): string => option.name}
                                renderOption={(props, option): JSX.Element =>
                                    renderCountrySelectOption(props, option)
                                }
                                renderInput={(
                                    params: WTextFieldProps,
                                    error?: WFieldError,
                                ): JSX.Element => (
                                    <WTextField
                                        {...params}
                                        label="Choose a country"
                                        error={!!error}
                                        helperText={error && 'Country is required'}
                                    />
                                )}
                            />
                        </WForm.Control>
                        <WForm.Control fullWidth={true}>
                            <WForm.Input label="Location" name="location" fullWidth={true} />
                        </WForm.Control>
                        <WStack
                            direction="row"
                            alignItems="center"
                            justifyContent="end"
                            gap={2}
                            sx={{ pt: 2 }}
                        >
                            <WButton onClick={closeAddLocationModal} variant="outlined">
                                Cancel
                            </WButton>
                            <WForm.Submit form={addLocationFormId}>Add location</WForm.Submit>
                        </WStack>
                    </WStack>
                </WForm>
            </WDialog.Content>
        </WDialog>
    );
}
