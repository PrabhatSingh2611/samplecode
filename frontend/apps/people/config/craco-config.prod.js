const ModuleFederationPlugin = require('webpack/lib/container/ModuleFederationPlugin');
const { getShareableDeps } = require('./shareable-dependencies');
const deps = getShareableDeps();

module.exports = {
    output: {
        filename: '[name].[contenthash].js',
        // publicPath: 'http://localhost:3001/', // Use for local prodiction test with "build:serve" script
        publicPath: '/people/latest/', // Use for production
    },
    plugins: [
        new ModuleFederationPlugin({
            name: 'people',
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
