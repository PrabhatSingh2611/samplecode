// TODO: Fix types to Theme (AU)
// @ts-nocheck
import React, { ReactNode } from 'react';
import ReactQuill, { ReactQuillProps } from 'react-quill';
import { styled } from '@mui/material/styles';
import { Box, BoxProps } from '@mui/material';
import WEditorToolbar, { formats, redoChange, undoChange } from './components/editor-toolbar';
import 'react-quill/dist/quill.snow.css';

const WEditorContainer = styled(Box)(({ theme }) => ({
  borderRadius: theme.shape.borderRadius,
  border: `solid 1px ${theme.palette.grey[500_32]}`,
  '& .ql-container.ql-snow': {
    borderColor: 'transparent',
    ...theme.typography.body1,
    fontFamily: theme.typography.fontFamily,
  },
  '& .ql-editor': {
    minHeight: 200,
    maxHeight: 640,
    '&.ql-blank::before': {
      fontStyle: 'normal',
      color: theme.palette.text.disabled,
    },
    '& pre.ql-syntax': {
      ...theme.typography.body2,
      padding: theme.spacing(2),
      borderRadius: theme.shape.borderRadius,
      backgroundColor: theme.palette.grey[900],
    },
  },
}));
export interface WIEditorProps extends ReactQuillProps {
  id?: string;
  error?: boolean;
  simple?: boolean;
  helperText?: ReactNode;
  placeholder?: string;
  sx?: BoxProps;
}

function WEditor({
  id = 'minimal-quill',
  error,
  value,
  onChange,
  simple = false,
  helperText,
  placeholder = "Write something awesome...",
  sx,
  ...other
}: WIEditorProps) {
  const modules = {
    toolbar: {
      container: `#${id}`,
      handlers: {
        undo: undoChange,
        redo: redoChange,
      },
    },
    history: {
      delay: 500,
      maxStack: 100,
      userOnly: true,
    },
    syntax: false,
    clipboard: {
      matchVisual: false,
    },
  };

  return (
    <>
      <WEditorContainer
        sx={{
          ...(error && {
            border: (theme) => `solid 1px ${theme.palette.error.main}`,
          }),
          ...sx,
        }}
      >
        <WEditorToolbar id={id} isSimple={simple} />
        <ReactQuill
          value={value}
          onChange={onChange}
          modules={modules}
          formats={formats}
          placeholder={placeholder}
          {...other}
        />
      </WEditorContainer>

      {helperText}
    </>
  );
}

export default WEditor;
