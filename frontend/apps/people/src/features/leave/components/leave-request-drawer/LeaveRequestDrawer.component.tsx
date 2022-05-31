import { WActionsDrawer } from 'wdx';

import { LeaveRequestDrawerContent } from 'features/leave/components/leave-request-drawer-content/LeaveRequestDrawerContent.component';

interface IProps {
    isOpened: boolean;
    setIsOpened: (isOpened: boolean) => void;
}

export default function LeaveRequestDrawer({ isOpened, setIsOpened }: IProps): JSX.Element {
    const onClose = (): void => {
        setIsOpened(false);
    };

    return (
        <WActionsDrawer isOpened={isOpened} onClose={onClose} title="Request a Leave">
            <LeaveRequestDrawerContent onClose={onClose} />
        </WActionsDrawer>
    );
}
