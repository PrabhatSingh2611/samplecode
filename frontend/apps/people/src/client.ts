import { initialize } from 'wdx';

import { ApolloClient, InMemoryCache, NormalizedCacheObject } from '@apollo/client';

const API_URI = process.env.REACT_APP_GATEWAY_HTTP_URI;

// TODO: move it to separate file, should be called before line 8 (TD)
initialize();

export const getClient = (inIsolation: boolean): ApolloClient<NormalizedCacheObject> => {
    const cache = inIsolation ? new InMemoryCache() : window.__shared__.__apollo_cache__;

    return new ApolloClient({
        cache,
        uri: API_URI,
    });
};
