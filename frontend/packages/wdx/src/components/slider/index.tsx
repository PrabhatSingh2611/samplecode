import React from "react";

import { Slider, SliderProps } from "@mui/material";

export interface WSliderProps extends SliderProps {}

function WSlider(props: WSliderProps): JSX.Element {
    return <Slider {...props} />;
}


export default WSlider;