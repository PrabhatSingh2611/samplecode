import SettingsDrawer from 'components/settings/drawer';
import ThemeContrast from 'components/settings/ThemeContrast';
import ThemeRtlLayout from 'components/settings/ThemeRtlLayout';
import ThemeColorPresets from 'components/settings/ThemeColorPresets';

type Props = {
    children: React.ReactNode;
};

export default function ThemeSettings({ children }: Props) {
    return (
        <ThemeColorPresets>
            <ThemeContrast>
                <ThemeRtlLayout>
                    {children}
                    <SettingsDrawer />
                </ThemeRtlLayout>
            </ThemeContrast>
        </ThemeColorPresets>
    );
}
