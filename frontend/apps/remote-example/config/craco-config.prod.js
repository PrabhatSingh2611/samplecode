const ModuleFederationPlugin = require('webpack/lib/container/ModuleFederationPlugin');
const { getShareableDeps } = require('./shareable-dependencies');
const deps = getShareableDeps();

module.exports = {
    output: {
        filename: '[name].[contenthash].js',
        // publicPath: 'http://localhost:3000/', // Use for local prodiction test with "build:serve" script
        publicPath: '/remote-example/latest/', // Use for production
    },
    plugins: [
        new ModuleFederationPlugin({
            name: 'remote-example',
            filename: 'remoteEntry.js',
            exposes: {
                './RemoteExampleApp': './src/bootstrap',
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
