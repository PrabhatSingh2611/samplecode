import React, { ReactNode } from 'react';

import WButton, { WButtonProps } from '../../button';

type WFormSubmitProps = Omit<WButtonProps, 'type' | 'variant'> & {
  form: string;
  children: ReactNode;
};

function WFormSubmit(props: WFormSubmitProps) {
  return <WButton {...props} type="submit" variant="contained" />;
}

export default WFormSubmit;
