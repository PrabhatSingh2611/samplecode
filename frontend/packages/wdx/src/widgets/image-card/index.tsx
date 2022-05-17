// TODO: Fix types to Theme (AU)
// @ts-nocheck
import React from 'react';

import WTypography from '../../components/typography';
import WCard, { WCardContent } from '../../components/card';
import WImage from '../../components/image';
import WBox from '../../components/box';
import { styled } from '@mui/material/styles';
import { WGallery } from './types';
import { fDate } from '../../utils/formatTime';
import cssStyles from '../../utils/cssStyles';

export interface WImageCardProps {
  image: WGallery;
  actions: JSX.Element
  onClick: () => void;
}

function WImageCard({
  image,
  actions,
  onClick,
}: WImageCardProps): JSX.Element {
  const { imageUrl, title, postAt } = image;
  const WCaptionStyle = styled(WCardContent)(({ theme }):any => ({
    ...cssStyles().bgBlur({ blur: 2, color: theme.palette.grey[900] }),
    bottom: 0,
    width: '100%',
    display: 'flex',
    alignItems: 'center',
    position: 'absolute',
    justifyContent: 'space-between',
    color: theme.palette.common.white,
  }));

  return (
    <WCard sx={{ cursor: 'pointer', position: 'relative' }}>
      <WImage
        ratio="1/1"
        src={imageUrl}
        onClick={() => onClick}
        alt="Card with image"
        tabIndex={0}
        role="button"
        aria-label="Open card details"
      />

      <WCaptionStyle>
        <WBox component={'div'} sx={{maxWidth: "80%"}}>
          <WTypography variant="subtitle1" noWrap={true}>{title}</WTypography>
          <WTypography variant="body2" sx={{ opacity: 0.72 }}>
            {fDate(postAt)}
          </WTypography>
        </WBox>
        <WBox component={'div'} sx={{display: 'flex', alignItems: 'center'}}>
          {actions}
        </WBox>
      </WCaptionStyle>
    </WCard>
  );
}

export default WImageCard;
