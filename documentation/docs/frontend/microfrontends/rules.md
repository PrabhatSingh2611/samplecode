---
sidebar_position: 1
---

# Rules

Microfrontend inflexible rules

## #1 Zero coupling between Remote apps

- No importing of functions/objects/classes

- No shared state

- One Remote should not rely on another Remote

- Shared libraries through MF is ok

- Reusability is a nice “side effect” not the target.

## #2 Near-zero coupling between Host and Remote apps

- Host should not assume that Remote is using a particular framework.

- Host will keep and share some global state about auth and current user + context.

- Any necessary communication done with Communication Layer.

- No state management library between Host and Remotes.

## #3 Dependency sharing

- Both Host + Remotes should load only data that needs to be rendered.

- MF should not load dependency if it was already loaded by other MF.

## #4 Both Host + Remotes need routing features

- Users can navigate between different Remotes using routing logic build in into the Host

- Users can navigate around in a Remote using routing logic build in into the Remote

- Not all Remotes will require routing

- New routes added to Remote should not require a redeployment of the Host

- We might need to show two or more MFs at the same time

- We want to use off-the-shelf routing solutions. Some amount of custom coding is OK.

- We need routing features for Remotes in both hosted mode and in isolation. Developers should at once see what pass they are using.

- Each app might be using a completely different routing framework.

- If different apps need to communicate information about routing, it should be done in as generic fashion as possible. We might swap out or upgrade navigation libraries all the time - should not require a rewrite of the rest of the app.

## #5 We should always consider that Remote may not load or take a long time to load.

- If one MF does not load the remaining apps should work.

- Handle Loading state

- Handle Error state

## #6 MFs should be tested Independently

- Have unit and integration tests for each MF rendering in isolation.

- Run integration tests for MFs rendering inside the Host applications as part of end-to-end testing.

## #7 Micro-Frontends should be versioned

- Use Custom versioning.

- Use a specific version or “latest”.

- Host should be able to decide to always use the latest version of MF or specify the specific version.

## #8 CSS (Cascading Style Sheets) from one project should not affect another

- Use scoped CSS (CSS-in-JS, CSS Modules, BEM)

- Manually build the Component Library (i.e., AntD) and apply namespacing to it.

## #9 CI/CD Requirements

- Each MF should be deployed independently

- Keep previous versions of MFs

- Invalidate caches after MF deploy.

## #10 Version control (monorepo vs separate) should not have any impact on the overall project.

- We can use monorepo

- We can use separate repos

- We can use hybrid of both.

## #11 Shared static data/assets

- We need to store shared assets (Fonts, Images) on shared CDN public or private to load them only once.
