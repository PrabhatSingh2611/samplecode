---
sidebar_position: 3
---

# How to add new MF App

#### Variables to use in Steps:

1. `NEW_MF_NAME` should be _PascalCase_ => `People`, `MyNewSuperCoolMF`
2. `NEW_MF_NAME_LOWERCASE` should be _kebab-case_ => `people`, `my-new-super-cool-mf`
3. `NEW_MF_PORT` should be between 3001 and 3099 => `3001`, `3002`

#### Steps:

1. `cd frontend/apps`
2. `cp remote-example {NEW_MF_NAME_LOWERCASE}`
3. Using **Search & Replace** replace all `remote-example` with `NEW_MF_NAME_LOWERCASE`
4. Using **Search & Replace** replace all `RemoteExample` with `NEW_MF_NAME`
5. Using **Search & Replace** replace all `Remote Example` with `NEW_MF_NAME`
6. Using **Search & Replace** replace all `3000` with `NEW_MF_PORT`
7. Rename `.package.json` to `package.json`
8. Update packages if needed. Remove `react-router-dom` if needed.
9. `cd frontend`
10. `pnpm install`
11. `pnpm dev`
