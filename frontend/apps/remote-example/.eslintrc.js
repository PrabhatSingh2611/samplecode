const defaultConfig = require('config/eslint-preset');
const prettierRule = require('./.prettierrc.js');

module.exports = {
    ...defaultConfig,
    rules: {
        ...defaultConfig.rules,
        'prettier/prettier': [2, prettierRule],
    }
}