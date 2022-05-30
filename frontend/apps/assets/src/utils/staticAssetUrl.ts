// TODO: Remove this after static assets will be on our private CDN
export default function getStaticAssetUrl(url: string): string {
    if (process.env.NODE_ENV === 'development') {
        return url;
    }

    return `/assets/latest${url}`;
}
