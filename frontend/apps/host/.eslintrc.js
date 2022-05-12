const defaultConfig = require('config/eslint-preset');
const prettierRule = require('./.prettierrc.js');
const devRemotes = require('./config/dev-remotes');

const remotes =  Object.keys(devRemotes).map((remote) => `${remote}/.*$`);

module.exports = {
    ...defaultConfig,
    rules: {
        ...defaultConfig.rules,
        'prettier/prettier': [2, prettierRule],
        // allow Module Federation
        'import/no-unresolved': [2, { ignore: remotes }],
    }
}