import { initialize } from 'wdx';

import { ApolloClient, InMemoryCache, NormalizedCacheObject } from '@apollo/client';

import { getApolloLink } from './links';

const apolloLink = getApolloLink();

// TODO: move it to separate file, should be called before line 8 (TD)
initialize();

export const getClient = (inIsolation: boolean): ApolloClient<NormalizedCacheObject> => {
    const cache = inIsolation ? new InMemoryCache() : window.__shared__.__apollo_cache__;

    return new ApolloClient({
        cache,
        link: apolloLink,
    });
};
