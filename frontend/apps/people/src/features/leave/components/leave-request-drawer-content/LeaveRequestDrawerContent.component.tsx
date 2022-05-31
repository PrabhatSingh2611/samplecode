import { useState } from 'react';

import { isBefore, addHours } from 'date-fns';
import { WActionsDrawer, WButton, WGrid, WMenuItem, WTextField, WForm, WStack, Yup } from 'wdx';

import { useCreateLeaveRequest, useGetLeaveTypes } from 'features/leave/hooks/leave.hooks';
import { LeaveRequestStatus } from 'graphql-generated-types/types';

const leaveRequestFormId = 'leave-request-form';
const oneHour = 1;
const currentDate = new Date();
const nextDate = addHours(currentDate, oneHour);

const defaultValues = {
    leaveType: '',
    startDate: currentDate,
    endDate: nextDate,
    comment: undefined,
};

interface IFormData {
    leaveType: string;
    startDate: Date;
    endDate: Date;
    comment?: string;
}

interface ILeaveRequestDrawerContentProps {
    onClose: () => void;
}

export function LeaveRequestDrawerContent({
    onClose,
}: ILeaveRequestDrawerContentProps): JSX.Element {
    const [startDate, setStartDate] = useState(currentDate);
    const [endDate, setEndDate] = useState(nextDate);
    const [leaveTypes] = useGetLeaveTypes();
    const [createLeaveRequest] = useCreateLeaveRequest();

    const onFormSubmit = (values: IFormData): void => {
        createLeaveRequest({
            period: {
                startDate: values.startDate,
                endDate: values.endDate,
            },
            status: LeaveRequestStatus.New,
            comment: values.comment,
            type: {
                id: values.leaveType,
            },
        });
        onClose();
    };

    const isEndDateBeforeStart = isBefore(endDate, startDate);
    const endDateBeforeStartDateErrorMessage = isEndDateBeforeStart
        ? 'End date must be later than start date'
        : '';

    return (
        <WActionsDrawer.Content>
            <WForm
                id={leaveRequestFormId}
                onSubmit={onFormSubmit}
                validation={validation}
                defaultValues={defaultValues}
            >
                <WGrid container={true} rowSpacing={2.5} columnSpacing={2}>
                    <WGrid item={true} xs={12}>
                        <WForm.Control fullWidth={true}>
                            <WForm.Select
                                label="Select Leave Type"
                                select={true}
                                fullWidth={true}
                                name="leaveType"
                            >
                                {leaveTypes.map(({ id, name }) => (
                                    <WMenuItem value={id} key={id}>
                                        {name}
                                    </WMenuItem>
                                ))}
                            </WForm.Select>
                        </WForm.Control>
                    </WGrid>
                    <WGrid item={true} xs={12} md={6}>
                        <WForm.Control fullWidth={true}>
                            <WForm.DatePicker
                                name="startDate"
                                views={['day']}
                                label="Start Date"
                                onChange={(newValue: unknown): void => {
                                    setStartDate(newValue as Date);
                                }}
                                renderInput={(params, error): JSX.Element => (
                                    <WTextField
                                        {...params}
                                        fullWidth={true}
                                        margin="normal"
                                        error={!!error}
                                        helperText={error?.message}
                                    />
                                )}
                            />
                        </WForm.Control>
                    </WGrid>
                    <WGrid item={true} xs={12} md={6}>
                        <WForm.Control fullWidth={true}>
                            <WForm.DatePicker
                                name="endDate"
                                views={['day']}
                                label="End Date"
                                onChange={(newValue: unknown): void => {
                                    setEndDate(newValue as Date);
                                }}
                                renderInput={(params, error): JSX.Element => (
                                    <WTextField
                                        {...params}
                                        fullWidth={true}
                                        margin="normal"
                                        error={!!error || isEndDateBeforeStart}
                                        helperText={
                                            error?.message || endDateBeforeStartDateErrorMessage
                                        }
                                    />
                                )}
                            />
                        </WForm.Control>
                    </WGrid>
                    <WGrid item={true} xs={12}>
                        <WForm.Control fullWidth={true}>
                            <WForm.Input
                                label="Comment (Optional)"
                                name="comment"
                                rows={4}
                                multiline={true}
                                fullWidth={true}
                            />
                        </WForm.Control>
                    </WGrid>
                    <WGrid item={true} xs={12}>
                        <WStack direction="row" alignItems="center" justifyContent="end" gap={2}>
                            <WButton onClick={onClose} variant="outlined">
                                Cancel
                            </WButton>
                            <WForm.Submit form={leaveRequestFormId}>Request</WForm.Submit>
                        </WStack>
                    </WGrid>
                </WGrid>
            </WForm>
        </WActionsDrawer.Content>
    );
}

const validation = Yup.object().shape({
    leaveType: Yup.string().required('Leave Type is required'),
    comment: Yup.string(),
    startDate: Yup.date().nullable().typeError('Invalid Date').required('Start Date is required'),
    endDate: Yup.date().nullable().typeError('Invalid Date').required('End Date is required'),
});
