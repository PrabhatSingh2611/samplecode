import { matchPath } from 'react-router-dom';

export const isExternalLink = (path: string): boolean => path.includes('http');

export const getActive = (path: string, pathname: string): boolean =>
    path ? !!matchPath(pathname, { path }) : false;
