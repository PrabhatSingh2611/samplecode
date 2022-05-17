import React from "react";

import { Radio, RadioGroup, RadioGroupProps, RadioProps } from "@mui/material";

export interface WRadioProps extends RadioProps {}

export interface WRadioGroupProps extends RadioGroupProps {}

function WRadio(props: WRadioProps): JSX.Element {
    return <Radio {...props} />;
}

export function WRadioGroup(props: WRadioGroupProps): JSX.Element {
    return <RadioGroup {...props} />;
}

export default WRadio;