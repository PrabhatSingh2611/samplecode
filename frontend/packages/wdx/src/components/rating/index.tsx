import React from "react";

import Rating, { RatingProps } from '@mui/material/Rating';

export interface WRatingProps extends RatingProps {}

function WRating(props: WRatingProps):JSX.Element {
    return <Rating {...props}  />;
};

export default WRating;