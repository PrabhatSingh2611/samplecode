const ModuleFederationPlugin = require('webpack/lib/container/ModuleFederationPlugin');

const { getShareableDeps } = require('./shareable-dependencies');
const deps = getShareableDeps();

module.exports = {
    devServer: {
        // Specify different Port here
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
        // And here
        publicPath: 'http://localhost:3000/',
    },
    plugins: [
        new ModuleFederationPlugin({
            name: 'remote-example',
            library: { type: 'var', name: 'remote-example' },
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
};
