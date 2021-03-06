import React, { useState } from 'react';

import { WStack, WTextField } from 'wdx';

import { AssetMenuItem } from 'features/assets-table/components/table/AssetMenuItem';
import { AssetsListSearch } from 'features/assets-table/components/table/AssetsListSearch';
import LocationSelect from 'features/assets-table/components/table/LocationSelect';
import TypeSelect from 'features/assets-table/components/table/TypeSelect';
import { ISelected } from 'features/assets-table/utils/renderOptions.utils';

export default function UserTableToolbar(): JSX.Element {
    const [types, setTypes] = React.useState<ISelected[]>([]);
    const [locations, setLocations] = React.useState<ISelected[]>([]);

    const initialFilterValue = 'All';

    const [filterType, setFilterType] = useState<string>(initialFilterValue);
    const handleFilterType = (event: React.ChangeEvent<HTMLInputElement>): void => {
        return setFilterType(event.target.value);
    };

    const [filterLocation, setFilterLocation] = useState<string>(initialFilterValue);
    const handleFilterLocation = (event: React.ChangeEvent<HTMLInputElement>): void => {
        setFilterLocation(event.target.value);
    };

    const typeNames = types.map((value) => value.name).join(', ');
    const locationsName = locations.map((value) => value.name).join(', ');

    return (
        <WStack spacing={2} direction={{ xs: 'column', sm: 'row' }} sx={{ py: 2.5, px: 3 }}>
            <WTextField
                fullWidth
                select
                label="Type"
                value={types.length ? typeNames : filterType}
                onChange={handleFilterType}
                SelectProps={{
                    MenuProps: {
                        sx: { '& .MuiPaper-root': { maxHeight: 260 } },
                    },
                }}
                sx={{
                    display: 'flex',
                    alignItems: 'center',
                    maxWidth: { sm: 240 },
                    textTransform: 'capitalize',
                }}
            >
                {AssetMenuItem({ fieldName: typeNames })}
                <TypeSelect setTypes={setTypes} />
            </WTextField>

            <WTextField
                fullWidth
                select
                label="Location"
                value={locations.length ? locationsName : filterLocation}
                onChange={handleFilterLocation}
                SelectProps={{
                    MenuProps: {
                        sx: { '& .MuiPaper-root': { maxHeight: 260 } },
                    },
                }}
                sx={{
                    display: 'flex',
                    alignItems: 'center',
                    maxWidth: { sm: 240 },
                    textTransform: 'capitalize',
                }}
            >
                {AssetMenuItem({ fieldName: locationsName })}
                <LocationSelect setLocations={setLocations} />
            </WTextField>

            <AssetsListSearch />
        </WStack>
    );
}
