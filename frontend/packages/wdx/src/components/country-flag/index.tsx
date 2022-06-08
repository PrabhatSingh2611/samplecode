import React from 'react';
import ReactCountryFlag, { ReactCountryFlagProps } from 'react-country-flag';

function WCountryFlag(props: ReactCountryFlagProps): JSX.Element {
  return <ReactCountryFlag {...props} />;
}

export default WCountryFlag;
