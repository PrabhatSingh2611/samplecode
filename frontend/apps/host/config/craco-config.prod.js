const ModuleFederationPlugin = require('webpack/lib/container/ModuleFederationPlugin');
const { getShareableDeps } = require('./shareable-dependencies');
const deps = getShareableDeps();

const domain = process.env.REACT_APP_PRODUCTION_DOMAIN;

module.exports = {
    output: {
        filename: '[name].[contenthash].js',
        // publicPath: 'http://localhost:3000/', // Use for local production test with "build:serve" script
        publicPath: '/host/latest/', // Use for Production
    },
    plugins: [
        new ModuleFederationPlugin({
            name: 'host',
            remotes: {
                // people: `people@http://localhost:3001/remoteEntry.js`, // Use for local production test with "build:serve" script
                // people: `assets@http://localhost:3002/remoteEntry.js`, // Use for local production test with "build:serve" script
                people: `people@${domain}/people/latest/remoteEntry.js`, // Use for Production
                assets: `assets@${domain}/assets/latest/remoteEntry.js`, // Use for Production
            },
            shared: {
                ...deps,
                react: {
                    singleton: true,
                    requiredVersion: deps['react'],
                },
                'react-dom': {
                    singleton: true,
                    requiredVersion: deps['react-dom'],
                },
            },
        }),
    ],
    // Set bundle budget error
    // TODO: Decide about size and how to calulcate in GZIP (IM)
    performance: {
        maxAssetSize: 500000,
        maxEntrypointSize: 500000,
        hints: 'error',
    },
};
