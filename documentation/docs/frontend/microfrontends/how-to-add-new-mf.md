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
2. Remove `node_modules` from `frontend/remote-example`
3. `cp -R remote-example {NEW_MF_NAME_LOWERCASE}`
4. Rename file `src/core/RemoteExampleObservables.tsx` to `src/core/[NEW_MF_NAME]Observables.tsx`
4. Using **Search & Replace** replace all `remote-example` with `NEW_MF_NAME_LOWERCASE`
5. Using **Search & Replace** replace all `RemoteExample` with `NEW_MF_NAME`
6. Using **Search & Replace** replace all `Remote Example` with `NEW_MF_NAME`
7. Using **Search & Replace** replace all `3000` with `NEW_MF_PORT`
8. Update packages if needed. Remove `react-router-dom` if needed.
9. Add your new MF to `plugins.ModuleFederationPlugin.remotes` list in `frontend/apps/host/config/craco-config.dev.js` as `{NEW_MF_NAME_LOWERCASE}: '{NEW_MF_NAME_LOWERCASE}@http://localhost:{NEW_MF_PORT}/remoteEntry.js',`
10. Add your new MF to `plugins.ModuleFederationPlugin.remotes` list in `frontend/apps/host/config/craco-config.prod.js` as `{NEW_MF_NAME_LOWERCASE}: '{NEW_MF_NAME_LOWERCASE}@${domain}/{NEW_MF_NAME_LOWERCASE}/latest/remoteEntry.js'` don't forget to change "'" to "`".
11. `cd frontend`
12. `pnpm install`
13. `pnpm dev`
