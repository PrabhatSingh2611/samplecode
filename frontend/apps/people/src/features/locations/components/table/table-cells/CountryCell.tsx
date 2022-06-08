import React from 'react';

import { WCountryFlag, WList, WStack, WTansitionFade, WTooltip } from 'wdx';

import { LocationCountryForLocationsListFragment } from 'features/locations/graphql/queries/locationsForLocationsList.generated';

interface ICountryCellProps {
    country: LocationCountryForLocationsListFragment;
}

export function CountryCell({ country }: ICountryCellProps): JSX.Element {
    const { name, iconName } = country;

    return (
        <WTooltip
            title={name}
            TransitionComponent={WTansitionFade}
            TransitionProps={{ timeout: 600 }}
        >
            <WStack direction="row" alignItems="center">
                <WCountryFlag
                    countryCode={iconName}
                    svg={true}
                    style={{
                        width: '2em',
                        height: '2em',
                        paddingRight: '0.5em',
                    }}
                />

                <WList.ItemText primary={name} />
            </WStack>
        </WTooltip>
    );
}
