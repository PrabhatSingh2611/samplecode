import { useContext } from 'react';

import { SettingsContextProps } from 'components/settings/type';
import { SettingsContext } from 'contexts/SettingsContext';

export default function useSettings(): SettingsContextProps {
    return useContext(SettingsContext);
}
