// TODO: Fix types to Theme (AU)
import { WForm, WStack, Yup } from 'wdx';

export const AddPolicyFormId = 'add-policy-form';

export interface IAddPolicyFormValues {
    title: string;
    file: File;
}

interface IProps {
    onSubmit: (values: IAddPolicyFormValues) => void;
}

export default function AddPolicyForm({ onSubmit }: IProps): JSX.Element {
    return (
        <WForm id={AddPolicyFormId} onSubmit={onSubmit} validation={validation}>
            <WStack gap={2}>
                <WForm.Control component="fieldset">
                    <WForm.Label component="legend" variant="bold">
                        Search engine
                    </WForm.Label>
                    <WForm.Input name="title" placeholder="Title" />
                </WForm.Control>

                <WForm.Control component="fieldset">
                    <WForm.Label component="legend" variant="bold">
                        Upload
                    </WForm.Label>
                    <WForm.UploadSingle name="file" />
                </WForm.Control>
            </WStack>
        </WForm>
    );
}

const validation = Yup.object().shape({
    title: Yup.string().required('Title is required'),
    file: Yup.mixed().required('File is required'),
});
