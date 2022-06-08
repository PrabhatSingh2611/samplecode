import { useCallback } from 'react';

import { castToArray, castToString, EFetchPolicy } from 'wdx';

import { FetchResult, useReactiveVar } from '@apollo/client';

import { INewLocation } from 'features/locations/components/AddLocationModal';
import {
    CreateCountryForAddLocationModalMutation,
    CreateCountryForAddLocationModalMutationResult,
    useCreateCountryForAddLocationModalMutation,
} from 'features/locations/graphql/mutations/createCountryForAddCountryModal.generated';
import {
    CreateLocationForAddLocationModalMutationResult,
    useCreateLocationForAddLocationModalMutation,
} from 'features/locations/graphql/mutations/createLocationForAddLocationModal.generated';
import {
    CountryForAddLocationModalFragment,
    GetLocationCountriesForAddLocationModalQueryHookResult,
    useGetLocationCountriesForAddLocationModalQuery,
} from 'features/locations/graphql/queries/locationCountriesForAddLocationModal.generated';
import { useGetLocationsTableList } from 'features/locations/hooks/locationsTable.hooks';
import {
    getIsCountryAlreadyExist,
    getListedCountryId,
} from 'features/locations/utils/location.utils';
import {
    CreateLocationCountryInput,
    CreateLocationInput,
    LocationCountrySort,
} from 'graphql-generated-types/types';
import { isAddLocationModalOpenVar } from 'local-state/reactiveVars';

interface IUseGetCountriesForAddNewLocationModalResult {
    locationCountriesListQuery: GetLocationCountriesForAddLocationModalQueryHookResult;
    locationCountries: CountryForAddLocationModalFragment[] | [];
}

const useGetCountriesForAddNewLocationModal = (): IUseGetCountriesForAddNewLocationModalResult => {
    const allListedCountriesCount = 100;

    const locationCountriesListQuery = useGetLocationCountriesForAddLocationModalQuery({
        variables: {
            input: {
                pagination: {
                    itemsPerPage: allListedCountriesCount,
                },
                sort: [LocationCountrySort.NameAsc],
            },
        },
    });

    const locationCountries = castToArray(locationCountriesListQuery.data?.locationCountries.items);

    return {
        locationCountriesListQuery,
        locationCountries,
    };
};

export const useIsAddLocationModalOpen = (): [
    boolean,
    (isAddLocationModalOpen: boolean) => void,
] => {
    return [useReactiveVar(isAddLocationModalOpenVar), isAddLocationModalOpenVar];
};

const useAddCountry = (): [
    (
        values: CreateLocationCountryInput,
    ) => Promise<FetchResult<CreateCountryForAddLocationModalMutation>>,
    CreateCountryForAddLocationModalMutationResult,
] => {
    const [create, createCountryResult] = useCreateCountryForAddLocationModalMutation();
    const createCountry = useCallback(
        (
            input: CreateLocationCountryInput,
        ): Promise<FetchResult<CreateCountryForAddLocationModalMutation>> => {
            return create({
                variables: {
                    input,
                },
            });
        },
        [create],
    );

    return [createCountry, createCountryResult];
};

const useAddLocation = (
    onCompleted?: () => void,
): [(values: CreateLocationInput) => void, CreateLocationForAddLocationModalMutationResult] => {
    const [create, createLocationResult] = useCreateLocationForAddLocationModalMutation({
        onCompleted,
    });
    const createLocation = useCallback(
        (input: CreateLocationInput): void => {
            create({
                variables: {
                    input,
                },
            });
        },
        [create],
    );

    return [createLocation, createLocationResult];
};

export const useAddNewCountryAndLocation = (): ((values: INewLocation) => void) => {
    const { locationCountries: countries } = useGetCountriesForAddNewLocationModal();
    const { locationsListQuery } = useGetLocationsTableList(EFetchPolicy.CACHE_AND_NETWORK);

    const onComplete = (): void => {
        locationsListQuery.refetch();
    };

    const [addLocation] = useAddLocation(onComplete);
    const [addCountry] = useAddCountry();

    return useCallback(
        (values: INewLocation) => {
            const { location, country } = values;
            const isCountryAlreadyExist = getIsCountryAlreadyExist(countries, country);

            const addNewLocation = (location: string, countryId: string): void => {
                addLocation({
                    name: location,
                    country: {
                        id: countryId,
                    },
                });
            };

            if (isCountryAlreadyExist) {
                const countryId = getListedCountryId(countries, country);

                addNewLocation(location, countryId);
            } else {
                addCountry({
                    name: country.name,
                    iconName: country.code,
                })
                    .then((res) => {
                        addNewLocation(
                            location,
                            castToString(res.data?.createLocationCountry.locationCountry?.id),
                        );
                    })
                    .catch((err) => console.error(err));
            }
        },
        [addCountry, addLocation, countries],
    );
};
