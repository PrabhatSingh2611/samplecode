---
sidebar_position: 3
---

# WDX Icons

## How to use WDX icons?

If you want to use **Material Icons** in your project, please use `<WIcon />` component.
It inherits all props from `<Icon />` and has one personal prop `name`

:::info
Type for `name` is **TIcons**
:::

## How add new icon

If you cannot see required icon in the list, you can add it by yourself

`frontend/packages/wdx/src/components/icon/icons.type.ts`

In this file add a name for an icon which you want to use

:::info
use kebab-case for `name`

e.g. **icon-calendar**
:::

Then add proper import to `frontend/packages/wdx/src/components/icon/icons.config.ts`

:::info
use your `name` from `TIcons` for object key
use imported icon from `@mui/material/ICONNAME` for value

e.g. **icon-calendar: IconCalendar**
:::

### Now you are able to use your icon
