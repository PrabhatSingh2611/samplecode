import AddPolicyForm, {
    AddPolicyFormId,
    IAddPolicyFormValues,
} from 'features/policies/components/add-policy-form/AddPolicyForm';
import {
    useCreatePolicy,
    useIsAddPolicyDrawerOpened,
} from 'features/policies/hooks/api-policies.hooks';
import { WForm, WActionsDrawer, WButton } from 'wdx';

interface IAddPolicyDrawer {
    refetchPolicies: () => void;
}

export default function AddPolicyDrawer({ refetchPolicies }: IAddPolicyDrawer): JSX.Element {
    const [isOpened, setIsOpened] = useIsAddPolicyDrawerOpened();
    const [createPolicy] = useCreatePolicy(refetchPolicies);

    const onFormSubmit = (values: IAddPolicyFormValues): void => {
        createPolicy(values);
        setIsOpened(false);
    };

    return (
        <WActionsDrawer
            title="Add policy"
            isOpened={isOpened}
            onClose={(): void => setIsOpened(false)}
        >
            <WActionsDrawer.Content>
                <AddPolicyForm onSubmit={onFormSubmit} />
            </WActionsDrawer.Content>

            <WActionsDrawer.Footer
                rightContent={
                    <>
                        <WButton onClick={(): void => setIsOpened(false)}>Cancel</WButton>
                        <WForm.Submit form={AddPolicyFormId}>Save</WForm.Submit>
                    </>
                }
            />
        </WActionsDrawer>
    );
}
