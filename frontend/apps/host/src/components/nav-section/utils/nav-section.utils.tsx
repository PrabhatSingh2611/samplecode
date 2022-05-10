import { matchPath } from 'react-router-dom';

export const isExternalLink = (path: string) => path.includes('http');

export const getActive = (path: string, pathname: string) =>
    path ? !!matchPath(pathname, { path }) : false;
