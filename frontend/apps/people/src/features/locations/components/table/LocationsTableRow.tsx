import React from 'react';

import { AlignTableCell, WTable } from 'wdx';

import { CountryCell } from 'features/locations/components/table/table-cells/CountryCell';
import { LocationCell } from 'features/locations/components/table/table-cells/LocationCell';
import { MoreMenuCell } from 'features/locations/components/table/table-cells/MoreMenuCell';
import { LocationForLocationsListFragment } from 'features/locations/graphql/queries/locationsForLocationsList.generated';

interface ILocationTableRow {
    row: LocationForLocationsListFragment;
    onEditRow: VoidFunction;
    onDeleteRow: VoidFunction;
    onViewDetails: VoidFunction;
}

export default function LocationsTableRow({
    row,
    onEditRow,
    onDeleteRow,
    onViewDetails,
}: ILocationTableRow): JSX.Element {
    const { name, country } = row;

    return (
        <WTable.Row>
            <WTable.Cell align={AlignTableCell.LEFT}>
                {!!name && <LocationCell name={name} />}
            </WTable.Cell>

            <WTable.Cell align={AlignTableCell.LEFT}>
                <CountryCell country={country} />
            </WTable.Cell>

            <MoreMenuCell
                onDeleteRow={onDeleteRow}
                onEditRow={onEditRow}
                onViewDetails={onViewDetails}
            />
        </WTable.Row>
    );
}
