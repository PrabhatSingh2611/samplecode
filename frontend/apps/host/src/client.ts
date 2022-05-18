import { initialize } from 'wdx';

import { ApolloClient, InMemoryCache } from '@apollo/client';

const API_URI = process.env.REACT_APP_GATEWAY_HTTP_URI;

// TODO: move it to separate file, should be called before line 8 (TD)
initialize();

const sharedCache = new InMemoryCache({});

export const client = new ApolloClient({
    cache: sharedCache,
    uri: API_URI,
});

window.__shared__.__apollo_cache__ = sharedCache;
