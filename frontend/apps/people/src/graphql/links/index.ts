import { createUploadLink } from 'apollo-upload-client';

import { ApolloLink } from '@apollo/client';

export const API_URI = process.env.REACT_APP_GATEWAY_HTTP_URI;

export const getApolloLink = (): ApolloLink => {
    return createUploadLink({
        uri: API_URI,
    });
};
