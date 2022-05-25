import { Meta, Story } from '@storybook/react';
import React, { useState } from 'react';
import WBox from '../box';
import WTypography from '../typography';

import WDatePicker, {
  WAdapterDateFns,
  WDateTimePicker,
  WLocalizationProvider,
} from '../date-picker';
import WTextField from '../text-field';

const meta: Meta = {
  title: 'MUI/Inputs/DatePicker',
  component: WDatePicker,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = () => {
  const [value, setValue] = useState<Date | null>(new Date());
  return (
    <WLocalizationProvider dateAdapter={WAdapterDateFns}>
      <WBox>
        <WTypography variant="h3">Date Picker</WTypography>
        <WDatePicker
          views={['day']}
          label="Date Picker"
          value={value}
          onChange={(newValue: any) => {
            setValue(newValue);
          }}
          renderInput={(params: any) => (
            <WTextField
              {...params}
              fullWidth
              margin="normal"
              helperText={null}
            />
          )}
        />
        <WTypography variant="h3">Date and Time Picker</WTypography>
        <WDateTimePicker
          label="Date and Time Picker"
          value={value}
          onChange={(newValue: any) => {
            setValue(newValue);
          }}
          renderInput={(params: any) => (
            <WTextField
              {...params}
              fullWidth
              margin="normal"
              helperText={null}
            />
          )}
        />
      </WBox>
    </WLocalizationProvider>
  );
};

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
