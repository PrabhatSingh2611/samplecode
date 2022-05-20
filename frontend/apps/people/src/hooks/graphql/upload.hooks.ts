import { useCallback } from 'react';

import { FetchResult } from '@apollo/client';

import {
    UploadResourceMutation,
    UploadResourceMutationResult,
    useUploadResourceMutation,
} from 'graphql/documents/mutations/uploadResource.generated';

export const useUploadResource = (): [
    (resource: File) => Promise<FetchResult<UploadResourceMutation>>,
    UploadResourceMutationResult,
] => {
    const [uploadResource, uploadResourceMutation] = useUploadResourceMutation();

    const upload = useCallback(
        (resource: File) => {
            return uploadResource({
                variables: {
                    resource,
                },
            });
        },
        [uploadResource],
    );

    return [upload, uploadResourceMutation];
};
