import React from 'react';

import { addDecorator } from '@storybook/react';
import ThemeProvider from '../src/theme';

// https://storybook.js.org/docs/react/writing-stories/parameters#global-parameters
export const parameters = {
  // https://storybook.js.org/docs/react/essentials/actions#automatically-matching-args
  actions: { argTypesRegex: '^on.*' },
};

addDecorator((story) => (
  <ThemeProvider><div className='wdx-body'>{story()}</div></ThemeProvider>
));

