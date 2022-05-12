module.exports = {
    plugins: ['import', 'no-only-tests', 'react-hooks', 'promise', 'mui'],
    extends: [
        'plugin:react/recommended', // Uses the recommended rules from @eslint-plugin-react
        'plugin:@typescript-eslint/recommended', // Uses the recommended rules from the @typescript-eslint/eslint-plugin
        'plugin:promise/recommended',
        'plugin:import/errors',
        'plugin:import/warnings',
        'plugin:import/typescript',
        'plugin:prettier/recommended', // Enables eslint-plugin-prettier and displays prettier errors as ESLint errors. Make sure this is always the last configuration in the extends array.
    ],
    parser: '@typescript-eslint/parser', // Specifies the ESLint parser
    parserOptions: {
        ecmaVersion: 2018, // Allows for the parsing of modern ECMAScript features
        sourceType: 'module', // Allows for the use of imports
        project: ['**/tsconfig.json'],
    },
    settings: {
        react: {
            version: 'detect', // Tells eslint-plugin-react to automatically detect the version of React to use
        },
        'import/resolver': {
            node: {
                paths: ['src'],
            },
        },
    },
    rules: {
        'no-console': 1,
        '@typescript-eslint/no-var-requires': 0,
        'promise/always-return': 0,
        'promise/catch-or-return': 2,
        'promise/no-nesting': 2,
        'promise/no-callback-in-promise': 0,
        'no-extra-boolean-cast': 2,
        'no-useless-return': 2,
        curly: [2, 'all'],
        'newline-before-return': 2,
        'react-hooks/rules-of-hooks': 'error',
        'react-hooks/exhaustive-deps': 'warn',
        'no-unused-vars': 1,
        'no-use-before-define': 'off',
        '@typescript-eslint/no-use-before-define': ['off'],
        'react/react-in-jsx-scope': 'off',
        '@typescript-eslint/naming-convention': [
            'error',
            {
                selector: 'interface',
                format: ['PascalCase'],
                custom: {
                    regex: '^I[A-Z]',
                    match: true,
                },
            },
        ],
        '@typescript-eslint/explicit-function-return-type': 'error',
        '@typescript-eslint/no-unnecessary-condition': 2,
        'linebreak-style': ['error', 'unix'],
        'import/order': [
            'warn',
            {
                groups: ['builtin', 'external', 'internal', 'parent', 'sibling', 'index'],
                pathGroups: [
                    {
                        pattern: 'react*',
                        group: 'external',
                        position: 'before',
                    },
                    {
                        pattern: 'react*/**',
                        group: 'external',
                        position: 'before',
                    },
                    {
                        pattern: '@apollo',
                        group: 'external',
                        position: 'after',
                    },
                    {
                        pattern: '@apollo*/**',
                        group: 'external',
                        position: 'after',
                    },
                    {
                        pattern: 'graphql/**',
                        group: 'external',
                        position: 'after',
                    },
                ],
                pathGroupsExcludedImportTypes: ['builtin'],
                alphabetize: {
                    order: 'asc',
                    caseInsensitive: false,
                },
                'newlines-between': 'always',
            },
        ],
        'react/prop-types': 0,
        // Place to specify ESLint rules. Can be used to overwrite rules specified from the extended configs
        // e.g. "@typescript-eslint/explicit-function-return-type": "off",
        'react/self-closing-comp': [
            'error',
            {
                component: true,
                html: true,
            },
        ],
        'react/no-unused-prop-types': 2,
        'no-only-tests/no-only-tests': 'error',
        'prefer-template': 1,
        ...require('./mui-style-order.config'),
    },
};
