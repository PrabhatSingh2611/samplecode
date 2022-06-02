import React, { useCallback, useState, useEffect, useMemo } from 'react';
import { Accept, DropzoneOptions, useDropzone } from 'react-dropzone';
import { styled, SxProps, Theme } from '@mui/material/styles';
import getFileData from '../../utils/getFileData';
import UploadFilePreview from './upload-file-preview';
import WIcon from '../icon';
import { WCustomFile } from '../upload/types';
import WStack from '../stack';
import { castToArray } from '../../utils/maybeUtils';

const DropZoneStyle = styled('div')(({ theme }: any) => ({
  width: 64,
  height: 64,
  fontSize: 24,
  display: 'flex',
  cursor: 'pointer',
  alignItems: 'center',
  justifyContent: 'center',
  margin: theme.spacing(0.5),
  borderRadius: theme.shape.borderRadius,
  border: `dashed 1px ${theme.palette.divider}`,
  '&:hover': { opacity: 0.72 },
}));

const defaultAccept = {
  'image/*': [],
};

export type WTAccept = Accept;

export interface WUploadAttachmentsProps {
  attachments?: WCustomFile[] | null;
  isSingle?: boolean;
  onInputRef?: (inputRef: React.RefObject<HTMLInputElement>) => void;
  accept?: WTAccept;
  sx?: SxProps<Theme>;
}

export default function WUploadAttachments({
  attachments,
  isSingle = false,
  onInputRef,
  accept = defaultAccept,
  sx,
}: WUploadAttachmentsProps) {
  const [files, setFiles] = useState(castToArray(attachments));
  const isShowUploadButtonForSingle = isSingle && !files.length && !onInputRef;
  const isShowUploadButtonForMultiple = !!files.length || !onInputRef;
  const isShowUploadButton = isSingle
    ? isShowUploadButtonForSingle
    : isShowUploadButtonForMultiple;

  const handleRemove = (file: WCustomFile | string) => {
    const filteredItems = files?.filter((_file) => _file !== file);
    setFiles(filteredItems);
  };

  const handleDrop = useCallback(
    (acceptedFiles: WCustomFile[]) => {
      const newFiles = acceptedFiles.map((file: WCustomFile) =>
        Object.assign(file, {
          preview: URL.createObjectURL(file),
        })
      );

      if (isSingle) {
        setFiles([...newFiles]);
      } else {
        setFiles([...files, ...newFiles]);
      }
    },
    [files]
  );

  return (
    <WStack direction="row" sx={{ ...sx }}>
      {files?.map((file, index) => {
        const { key } = getFileData(file, index);

        return (
          <UploadFilePreview
            key={key}
            file={file}
            onRemove={(): void => handleRemove(file)}
          />
        );
      })}
      <UploadFile
        onDrop={handleDrop}
        multiple={!isSingle}
        onInputRef={onInputRef}
        isShowUploadButton={isShowUploadButton}
        accept={accept}
      />
    </WStack>
  );
}

type UploadFileProps = DropzoneOptions & {
  onInputRef?: (inputRef: React.RefObject<HTMLInputElement>) => void;
  isShowUploadButton: boolean;
};

function UploadFile({
  onInputRef,
  isShowUploadButton,
  ...other
}: UploadFileProps) {
  const { getRootProps, getInputProps, isDragActive, inputRef } = useDropzone({
    ...other,
  });

  const dropZoneStyles = useMemo(
    () => ({ ...(isDragActive && { opacity: 0.72 }) }),
    [isDragActive]
  );

  useEffect(() => {
    onInputRef && onInputRef(inputRef);
  }, [inputRef, onInputRef]);

  return (
    <>
      {isShowUploadButton && (
        <DropZoneStyle {...getRootProps()} sx={dropZoneStyles}>
          <WIcon name="add" sx={{ color: 'text.disabled' }} />
        </DropZoneStyle>
      )}
      <input {...getInputProps()} />
    </>
  );
}
