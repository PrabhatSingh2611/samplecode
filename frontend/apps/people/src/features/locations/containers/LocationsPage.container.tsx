import React, { Suspense } from 'react';

import { WButton, WPage } from 'wdx';

import LocationsHeader from 'features/locations/components/LocationsHeader';
import { LocationsTableContainer } from 'features/locations/containers/LocationsTable.container';
import { useIsAddLocationModalOpen } from 'features/locations/hooks/locationModals.hooks';

const AddLocationModal = React.lazy(() => import('features/locations/components/AddLocationModal'));

export default function LocationsPage(): JSX.Element {
    const [isAddLocationModalOpen, setIsAddLocationModalOpen] = useIsAddLocationModalOpen();

    const openAddLocationModal = (): void => {
        setIsAddLocationModalOpen(!isAddLocationModalOpen);
    };

    return (
        <WPage title="Locations">
            <LocationsHeader
                heading="Locations"
                action={
                    <WButton variant="contained" onClick={openAddLocationModal}>
                        Add location
                    </WButton>
                }
            />
            <LocationsTableContainer />

            <Suspense fallback={<div>Loading...</div>}>
                {isAddLocationModalOpen && <AddLocationModal />}
            </Suspense>
        </WPage>
    );
}
