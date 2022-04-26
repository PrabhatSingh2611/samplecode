---
sidebar_position: 2
---

# Peer review

Peer review is a set of steps to do whole Code Review.

## Why?

It's much easier to handle and do big Code Review in small and easy steps

### Order of Steps:

- Tech Design review
- Pre push checks
- Lint review on PR step
- Quality review on PR step
- Test review on PR step
- Manual Code review

### Tech Design review

#### Why?

- We can see how Feature will be Done in the Very start of Developement.
- It's much more easier and _cheaper_ to fix solution before the implementation than after.
- Saving Developer Effort.
- Knowledge sharing.
- It's much more faster to understand How Feature will be implemented with reading small amount of text than in PR review reading code.
- Feature Documentation stores in Jira Ticket. Easy to remind about why and how Feature was implemented with Git Annotation and correct commits with Jira Ticket code.

#### How?

1. Before starting to solve any task, the developer writes about how he will implement it.
2. Tech Design review should be written in Jira Ticket below _Tech Design_ heading.
3. Developer should folow Tech Design Checklist (Will be done progressively).
4. Developer sends link to Jira Ticket with Tech Design and asks seniour developers to review it.
5. Seniour or Expert developers review Tech Design and Approve or Reject with Comments or Solution in Jira Ticket Comment
6. Only after Approve Developer starts Feature implementation
7. Developer should follow Tech Design from Jira Ticket
8. On PR review step Seniour developers can check that implementation fits Tech Design

:::caution
If Seniour developer Rejects Tech Design Developer should start from step 2.
:::

:::caution
If Developer during implementation understans that implementation can't meet Tech Design he should start from step 2.
:::

:::info
Tech Design should be written in text format in 2-5 sentences depending on the complexity and size of the Feature
:::

### Pre push review

Pre push review should be done with Husky and should do Lint review and Test review

:::note
Would be nice to add Quality review check to Pre push checks
:::

### Lint review

Lint review should be done with ESLint and included to CI/CD check

### Quality review

Quality review should be done with SonarQube and included to CI/CD check

### Test review

Quality review should be done with Jest and Code Coverage report and included to CI/CD check
