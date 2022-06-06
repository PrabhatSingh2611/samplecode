// TODO: Fix types to Theme (AU)
// @ts-nocheck
import React from 'react';
import ReactMarkdown, { Options } from 'react-markdown';
import rehypeRaw from 'rehype-raw';
import { styled } from '@mui/material/styles';
import WTypography from '../typography';
import WDivider from '../divider';
import WImage from '../image';
import WLink from '../link';

const WMarkdownStyle = styled('div')(({ theme }) => {
    const isLight = theme.palette.mode === 'light';

    return {
        // List
        '& ul, & ol': {
            ...theme.typography.body1,
            paddingLeft: theme.spacing(5),
            '& li': {
                lineHeight: 2,
            },
        },

        // Blockquote
        '& blockquote': {
            lineHeight: 1.5,
            fontSize: '1.5em',
            margin: '40px auto',
            position: 'relative',
            fontFamily: 'Georgia, serif',
            padding: theme.spacing(3, 3, 3, 8),
            borderRadius: Number(theme.shape.borderRadius) * 2,
            backgroundColor: theme.palette.background.neutral,
            color: `${theme.palette.text.secondary} !important`,
            [theme.breakpoints.up('md')]: {
                width: '80%',
            },
            '& p, & span': {
                marginBottom: '0 !important',
                fontSize: 'inherit !important',
                fontFamily: 'Georgia, serif !important',
                color: `${theme.palette.text.secondary} !important`,
            },
            '&:before': {
                left: 16,
                top: -8,
                display: 'block',
                fontSize: '3em',
                content: '"\\201C"',
                position: 'absolute',
                color: theme.palette.text.disabled,
            },
        },

        // Code Block
        '& pre, & pre > code': {
            fontSize: 16,
            overflowX: 'auto',
            whiteSpace: 'pre',
            padding: theme.spacing(2),
            color: theme.palette.common.white,
            borderRadius: theme.shape.borderRadius,
            backgroundColor: isLight ? theme.palette.grey[900] : theme.palette.grey[500_16],
        },
        '& code': {
            fontSize: 14,
            borderRadius: 4,
            whiteSpace: 'pre',
            padding: theme.spacing(0.2, 0.5),
            color: theme.palette.warning[isLight ? 'darker' : 'lighter'],
            backgroundColor: theme.palette.warning[isLight ? 'lighter' : 'darker'],
            '&.hljs': { padding: 0, backgroundColor: 'transparent' },
        },
    };
});


const components = {
    h1: ({ ...props }) => <WTypography variant="h1" {...props} />,
    h2: ({ ...props }) => <WTypography variant="h2" {...props} />,
    h3: ({ ...props }) => <WTypography variant="h3" {...props} />,
    h4: ({ ...props }) => <WTypography variant="h4" {...props} />,
    h5: ({ ...props }) => <WTypography variant="h5" {...props} />,
    h6: ({ ...props }) => <WTypography variant="h6" {...props} />,
    hr: ({ ...props }) => <WDivider sx={{ my: 3 }} {...props} />,
    img: ({ ...props }) => (
        <WImage alt={props.alt} ratio="16/9" sx={{ borderRadius: 2, my: 5 }} {...props} />
    ),
    a: ({ ...props }) =>
        props.href.includes('http') ? (
            <WLink target="_blank" rel="noopener" {...props} />
        ) : (
            <WLink {...props} />
        ),
};

export interface WIMarkdownProps extends Options{};
function WMarkdown({ ...other }: WIMarkdownProps) {
    return (
        <WMarkdownStyle>
            <ReactMarkdown rehypePlugins={[rehypeRaw]} components={components} {...other} />
        </WMarkdownStyle>
    );
}

export default WMarkdown;