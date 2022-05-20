import { WBox, WForm, Yup } from 'wdx';

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
            <WBox display="flex" flexDirection="column" flexWrap="wrap" gap={2}>
                <WForm.Control>
                    <WForm.Input name="title" placeholder="Title" />
                </WForm.Control>

                <WForm.Control>
                    <WForm.Label>Upload</WForm.Label>
                    <WForm.UploadSingle name="file" />
                </WForm.Control>
            </WBox>
        </WForm>
    );
}

const validation = Yup.object().shape({
    title: Yup.string().required('Title is required'),
    file: Yup.mixed().required('File is required'),
});
