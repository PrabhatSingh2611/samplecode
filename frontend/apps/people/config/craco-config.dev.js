const ModuleFederationPlugin = require('webpack/lib/container/ModuleFederationPlugin');

const { getShareableDeps } = require('./shareable-dependencies');
const deps = getShareableDeps();

module.exports = {
    devServer: {
        port: 3001,
        historyApiFallback: {
            index: '/index.html',
        },
        devMiddleware: {
            writeToDisk: true,
        },
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': '*',
            'Access-Control-Allow-Headers': '*',
        },
    },
    output: {
        publicPath: 'http://localhost:3001/',
    },
    plugins: [
        new ModuleFederationPlugin({
            name: 'people',
            library: { type: 'var', name: 'people' },
            filename: 'remoteEntry.js',
            exposes: {
                './PeopleApp': './src/bootstrap',
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
