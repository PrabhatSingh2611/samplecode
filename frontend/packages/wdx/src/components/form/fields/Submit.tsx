import React, { ReactNode } from 'react';

import WButton, { WButtonProps } from '../../button';

type WFormSubmitProps = Omit<WButtonProps, 'type'> & {
  form: string;
  children: ReactNode;
};

function WFormSubmit({
  variant = 'contained',
  ...otherProps
}: WFormSubmitProps) {
  return <WButton {...otherProps} type="submit" variant={variant} />;
}

export default WFormSubmit;
