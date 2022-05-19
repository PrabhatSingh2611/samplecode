---
sidebar_position: 2
---

import { ExternalLink, TeamAll, TeamLelekaMore, TeamAvengers, TeamAchivers } from '../index.js'

# Environments

## Develop Environments:

### Main Develop

- Status: ✅ **ACTIVE**
- Purpose: **Test latest Develop**
- URL (_FrontEnd_): <ExternalLink url="https://dev-audra.dev.topazdigital.cloud"/>
- Jenkinks (_FrontEnd_): <ExternalLink url="https://jenkins.dev.topazdigital.cloud/view/Audra-Dev/job/Audra-front/"/>
- URL (_API Schema_): <ExternalLink url="https://audra-service.dev.topazdigital.cloud/graphiql"/>
- URI (_API_): <ExternalLink url="https://audra-service.dev.topazdigital.cloud/graphql"/>
- Main Develop Env redeploys automatically on each push/merge to `develop` branch: 🚀 **Auto Deploy**
- Automatically redeploys only affected/updated/changed MF's: 🚀 **Incremental Auto Deploy**

### Documentation

- Status: 🚧 **UNDER CONSTRUCTION**
- Purpose: **Read Documentation**
- URL (_FrontEnd_): <ExternalLink url="https://dev-audra-docs.dev.topazdigital.cloud"/>
- Jenkinks (_FrontEnd_): N/A
- Owner: <TeamAll/>
- Documentation Env redeploys automatically on each push/merge to `develop` branch: 🚀 **Auto Deploy**

### Develop #1

- Status: 🚧 **UNDER CONSTRUCTION**
- Purpose: **Test unrelesed feature** _OR_ **Test feature under development**
- URL (_FrontEnd_): <ExternalLink url="https://dev-audra-1.dev.topazdigital.cloud"/>
- Jenkinks (_FrontEnd_): N/A
- Owner: <TeamLelekaMore/>
- Develop #1 Env deploys manually on demand: 🛵 **Manual Deploy**
- Redeploy of all MF's _OR_ some of them should be chosen manually: **Full Deploy** _OR_ 🛵 **Incremental Manual Deploy**

### Develop #2

- Status: 🚧 **UNDER CONSTRUCTION**
- Purpose: **Test unrelesed feature** _OR_ **Test feature under development**
- URL (_FrontEnd_): <ExternalLink url="https://dev-audra-2.dev.topazdigital.cloud"/>
- Jenkinks (_FrontEnd_): N/A
- Owner: <TeamAvengers/>
- Develop #2 Env deploys manually on demand: 🛵 **Manual Deploy**
- Redeploy of all MF's _OR_ some of them should be chosen manually: **Full Deploy** _OR_ 🛵 **Incremental Manual Deploy**

### Develop #3

- Status: 🚧 **UNDER CONSTRUCTION**
- Purpose: **Test unrelesed feature** _OR_ **Test feature under development**
- URL (_FrontEnd_): <ExternalLink url="https://dev-audra-3.dev.topazdigital.cloud"/>
- Jenkinks (_FrontEnd_): N/A
- Owner: <TeamAchivers/>
- Develop #3 Env deploys manually on demand: 🛵 **Manual Deploy**
- Redeploy of all MF's _OR_ some of them should be chosen manually: **Full Deploy** _OR_ 🛵 **Incremental Manual Deploy**
