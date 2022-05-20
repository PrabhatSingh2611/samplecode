// TODO: Use styled from WDX (AU)
import { styled } from '@mui/material';
import { WForm, WStack, Yup } from 'wdx';

export const AddPolicyFormId = 'add-policy-form';

export interface IAddPolicyFormValues {
    title: string;
    file: File;
}

interface IProps {
    onSubmit: (values: IAddPolicyFormValues) => void;
}

const LabelStyle = styled(WForm.Label)(({ theme }): any => ({
    ...theme.typography.subtitle1,
    color: theme.palette.text.primary,
    marginBottom: theme.spacing(2),
}));

export default function AddPolicyForm({ onSubmit }: IProps): JSX.Element {
    return (
        <WForm id={AddPolicyFormId} onSubmit={onSubmit} validation={validation}>
            <WStack gap={2}>
                <WForm.Control>
                    <LabelStyle>Search engine</LabelStyle>
                    <WForm.Input name="title" placeholder="Title" />
                </WForm.Control>

                <WForm.Control>
                    <LabelStyle>Upload</LabelStyle>
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
