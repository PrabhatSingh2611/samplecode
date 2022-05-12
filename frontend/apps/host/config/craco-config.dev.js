const ModuleFederationPlugin = require('webpack/lib/container/ModuleFederationPlugin');
const devRemotes = require('./dev-remotes');
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
            remotes: devRemotes,
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
