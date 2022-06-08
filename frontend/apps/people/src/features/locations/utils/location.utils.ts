import { castToString } from 'wdx';

import { ICountryType } from 'features/locations/constants/countryCodes';
import { CountryForAddLocationModalFragment } from 'features/locations/graphql/queries/locationCountriesForAddLocationModal.generated';

export const getIsCountryAlreadyExist = (
    countries: CountryForAddLocationModalFragment[],
    countryValues: ICountryType,
): boolean => {
    return countries.some(
        (country: CountryForAddLocationModalFragment) => country.name === countryValues.name,
    );
};

export const getListedCountryId = (
    countries: CountryForAddLocationModalFragment[],
    countryValues: ICountryType,
): string => {
    const selectedCountry = countries.find((country) => country.name === countryValues.name);

    return castToString(selectedCountry?.id);
};
