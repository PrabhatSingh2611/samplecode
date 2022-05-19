---
sidebar_position: 3
---

# API Design

## Why?

:::info
API Design should be done for every major GraphQL Schema update to be sure that Schema will be _enough_ for Feature implementation from both FE and BE
:::

## How to

:::warning
Never do API Design more than 1,5 hour! If you haven't been able to finish with one 1,5 hour session schedule new in 30 mins or next day.
:::

:::info
Always both BE and FE representatives should be on API Design, PO is recomended to participate also, to answer questions about Functional requirements.
:::

1. One of participants should _Lead_ the API Design and share his/her screen.
2. Open Documentaion "API > Schema Conventions" page.

:::caution
Always follow Schema Conventions while design.
:::

3. Open Jira Ticket and read with all participants Description, **ASK** questions.
4. Open Figma and go throught the Design with all participants, **ASK** questions.
5. Start Design Schema, create empty GraphQL file in your editor.
6. First go from type to type. **ASK** questions.
7. Than go to Queries if needed. **ASK** questions.
8. Than go to Mutations if needed. **ASK** questions.
9. Than go to Subscriptions if needed. **ASK** questions.
10. After you finished check one more time your designed Schema with Functional requirements and Figma designs. Change Schema if needed.
11. After all thing are covered and commited copy all file Contents and paste them in Jira ticket Comments section with GraphQL styling (type three Backticks).
12. Repeat the same for all Jira Tickets.
