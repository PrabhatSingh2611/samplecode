const ModuleFederationPlugin = require('webpack/lib/container/ModuleFederationPlugin');
const { getShareableDeps } = require('./shareable-dependencies');
const deps = getShareableDeps();

module.exports = {
    devServer: {
        port: 3000,
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
        publicPath: 'http://localhost:3000/',
    },
    plugins: [
        new ModuleFederationPlugin({
            name: 'host',
            remotes: {
                people: 'people@http://localhost:3001/remoteEntry.js',
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
