const ModuleFederationPlugin = require('webpack/lib/container/ModuleFederationPlugin');

const { getShareableDeps } = require('./shareable-dependencies');
const deps = getShareableDeps();

module.exports = {
    devServer: {
        port: 3002,
        historyApiFallback: {
            index: '/index.html',
        },
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': '*',
            'Access-Control-Allow-Headers': '*',
        },
    },
    output: {
        publicPath: 'http://localhost:3002/',
    },
    plugins: [
        new ModuleFederationPlugin({
            name: 'assets',
            library: { type: 'var', name: 'assets' },
            filename: 'remoteEntry.js',
            exposes: {
                './AssetsApp': './src/bootstrap',
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
};
