---
sidebar_position: 3
---

# How to add new MF App

1. `cd frontend/apps`
2. `pnpm dlx create-react-app {APP_NAME} --template typescript`
3. `cd {APP_NAME}`
4. `rm package-lock.json`
5. `rm -rf node_modules`
6. `pnpm add -D @babel/core @babel/eslint-parser @typescript-eslint/eslint-plugin @typescript-eslint/parser eslint eslint-config-airbnb eslint-config-airbnb-typescript eslint-config-prettier eslint-config-react-app eslint-import-resolver-typescript eslint-loader eslint-plugin-flowtype eslint-plugin-import eslint-plugin-jsx-a11y eslint-plugin-prettier eslint-plugin-react eslint-plugin-react-hooks prettier`
7. Be sure that `eslint` package has `8.14.0` version oh higher
8. Add to `devDependencies` in `package.json`:
   ```json
   {
     "config": "workspace:*",
     "tsconfig": "workspace:*"
   }
   ```
9. Move to from `dependencies` to `devDependencies` in `package.json`:

```json
{
  "@testing-library/jest-dom": "^5.16.4",
  "@testing-library/react": "^13.1.1",
  "@testing-library/user-event": "^13.5.0",
  "@types/jest": "^27.4.1",
  "@types/node": "^16.11.27",
  "@types/react": "^18.0.6",
  "@types/react-dom": "^18.0.2",
  "typescript": "^4.6.3"
}
```

11. `pnpm install`
12. Add to `scripts` in `package.json`:

```json
{
  "lint": "eslint --ext .ts,.tsx ./src",
  "lint:fix": "eslint --fix --ext .ts,.tsx ./src",
  "clear-all": "rm -rf build node_modules",
  "re-start": "rm -rf build node_modules && pnpm install && pnpm start",
  "re-build": "rm -rf build node_modules && pnpm install && pnpm build"
}
```

11. Add `.eslintignore` with contents:
    ```
    // .eslintignore
    build/*
    public/*
    src/react-app-env.d.ts
    src/reportWebVitals.ts
    src/service-worker.ts
    src/serviceWorkerRegistration.ts
    src/setupTests.ts
    ```
12. Add `eslintrc` with contents:
    ```json
    {
      "plugins": ["prettier", "@typescript-eslint"],
      "extends": ["airbnb-typescript", "react-app", "prettier"],
      "parser": "@typescript-eslint/parser",
      "parserOptions": {
        "project": ["**/tsconfig.json"]
      },
      "settings": {
        "import/resolver": {
          "typescript": {
            "alwaysTryTypes": true
          }
        }
      },
      "rules": {
        "react/jsx-key": 2,
        "arrow-body-style": 1,
        "import/no-duplicates": 1,
        "react/self-closing-comp": 1,
        "@typescript-eslint/no-shadow": 0,
        "import/no-useless-path-segments": 1,
        "import/no-extraneous-dependencies": 0,
        "@typescript-eslint/naming-convention": 0,
        "object-curly-spacing": [1, "always"],
        "@typescript-eslint/no-unused-vars": [
          1,
          {
            "vars": "all",
            "args": "none"
          }
        ],
        "prefer-destructuring": [
          1,
          {
            "object": true,
            "array": false
          }
        ]
      }
    }
    ```
13. Add `.prettierrc` with contents:
    ````json
    {
      "printWidth": 100,
      "singleQuote": true,
      "trailingComma": "es5",
      "tabWidth": 2
    }
    ```1. `cd frontend/apps`
    ````
14. `pnpm dlx create-react-app {APP_NAME} --template typescript`
15. `cd {APP_NAME}`
16. `rm package-lock.json`
17. `rm -rf node_modules`
18. `pnpm add -D @babel/core @babel/eslint-parser @typescript-eslint/eslint-plugin @typescript-eslint/parser eslint eslint-config-airbnb eslint-config-airbnb-typescript eslint-config-prettier eslint-config-react-app eslint-import-resolver-typescript eslint-loader eslint-plugin-flowtype eslint-plugin-import eslint-plugin-jsx-a11y eslint-plugin-prettier eslint-plugin-react eslint-plugin-react-hooks prettier`
19. Be sure that `eslint` package has `8.14.0` version oh higher
20. Add to `devDependencies` in `package.json`:
    ```json
    {
      "config": "workspace:*",
      "tsconfig": "workspace:*"
    }
    ```
21. Move to from `dependencies` to `devDependencies` in `package.json`:

```json
{
  "@testing-library/jest-dom": "^5.16.4",
  "@testing-library/react": "^13.1.1",
  "@testing-library/user-event": "^13.5.0",
  "@types/jest": "^27.4.1",
  "@types/node": "^16.11.27",
  "@types/react": "^18.0.6",
  "@types/react-dom": "^18.0.2",
  "typescript": "^4.6.3"
}
```

11. `pnpm install`
12. Add to `scripts` in `package.json`:

```json
{
  "lint": "eslint --ext .ts,.tsx ./src",
  "lint:fix": "eslint --fix --ext .ts,.tsx ./src",
  "clear-all": "rm -rf build node_modules",
  "re-start": "rm -rf build node_modules && pnpm install && pnpm start",
  "re-build": "rm -rf build node_modules && pnpm install && pnpm build"
}
```

11. Add `.eslintignore` with contents:
    ```
    // .eslintignore
    build/*
    public/*
    src/react-app-env.d.ts
    src/reportWebVitals.ts
    src/service-worker.ts
    src/serviceWorkerRegistration.ts
    src/setupTests.ts
    ```
12. Add `eslintrc` with contents:
    ```json
    {
      "plugins": ["prettier", "@typescript-eslint"],
      "extends": ["airbnb-typescript", "react-app", "prettier"],
      "parser": "@typescript-eslint/parser",
      "parserOptions": {
        "project": ["**/tsconfig.json"]
      },
      "settings": {
        "import/resolver": {
          "typescript": {
            "alwaysTryTypes": true
          }
        }
      },
      "rules": {
        "react/jsx-key": 2,
        "arrow-body-style": 1,
        "import/no-duplicates": 1,
        "react/self-closing-comp": 1,
        "@typescript-eslint/no-shadow": 0,
        "import/no-useless-path-segments": 1,
        "import/no-extraneous-dependencies": 0,
        "@typescript-eslint/naming-convention": 0,
        "object-curly-spacing": [1, "always"],
        "@typescript-eslint/no-unused-vars": [
          1,
          {
            "vars": "all",
            "args": "none"
          }
        ],
        "prefer-destructuring": [
          1,
          {
            "object": true,
            "array": false
          }
        ]
      }
    }
    ```
13. Add `.prettierrc` with contents:
    ```json
    {
      "printWidth": 100,
      "singleQuote": true,
      "trailingComma": "es5",
      "tabWidth": 2
    }
    ```
