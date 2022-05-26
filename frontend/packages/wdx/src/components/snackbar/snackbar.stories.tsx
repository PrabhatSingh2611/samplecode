import { Meta, Story } from '@storybook/react';
import React from 'react';
import WAlert from '../alert';
import WBox from '../box';
import WButton from '../button';
import WStack from '../stack';
import WSnackbar, { WSnackbarOrigin } from './index';

const meta: Meta = {
  title: 'MUI/Feedback/Snackbar',
  component: WSnackbar,
  parameters: {
    controls: { expanded: true },
  },
};

export default meta;

const Template: Story = () => {
    const [state, setState] = React.useState<WSnackbarOrigin & {open: boolean}>({
        open: false,
        vertical: 'top',
        horizontal: 'center',
      });
      const { vertical, horizontal, open } = state;

      const handleClick = (newState: WSnackbarOrigin) => () => {
        setState({ open: true, ...newState });
      };

      const handleClose = () => {
        setState({ ...state, open: false });
      };
    return (
        <WBox width="90vw" height="90vh" display="flex" alignItems="center" justifyContent="center">
            <WStack direction="row">
                <WButton
                    onClick={handleClick({
                    vertical: 'top',
                    horizontal: 'center',
                })}
                >
                    Top-Center
                </WButton>
                <WButton
                    onClick={handleClick({
                    vertical: 'top',
                    horizontal: 'right',
                })}
                >
                    Top-Right
                </WButton>
                <WButton
                    onClick={handleClick({
                    vertical: 'bottom',
                    horizontal: 'right',
                })}
                >
                    Bottom-Right
                </WButton>
                <WButton
                    onClick={handleClick({
                    vertical: 'bottom',
                    horizontal: 'center',
                })}
                >
                    Bottom-Center
                </WButton>
                <WButton
                    onClick={handleClick({
                    vertical: 'bottom',
                    horizontal: 'left',
                })}
                >
                    Bottom-Left
                </WButton>
                <WButton
                    onClick={handleClick({
                    vertical: 'top',
                    horizontal: 'left',
                })}
                >
                    Top-Left
                </WButton>
            </WStack>
            <WSnackbar
                anchorOrigin={{ vertical, horizontal }}
                open={open}
                onClose={handleClose}
                action={<WButton>Action</WButton>}
                key={vertical + horizontal}
            >{<div><WAlert variant='outlined' severity='success'>I'm snackbar</WAlert></div>}</WSnackbar>
        </WBox>
    )
}

// By passing using the Args format for exported stories, you can control the props for a component for reuse in a test
// https://storybook.js.org/docs/react/workflows/unit-testing
export const Default = Template.bind({});
