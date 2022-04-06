# GraphQL Schema Conventions

We need to follow conventions to make our Schema cosistent and easy to expand, maintain and work with.
Below conventios should be followed both from FE and BE side during API development and API design.

## Open questions

- FE should check how Apollo Client in Microfrontend batch Subscriptions. If each MF will use separate WS connection we nned to reduce amount of Subscriptions in Schema
- BE should check how to trigger Subscription for only requested from FE fields.
- BE should check how to handle "undefined" update in Subscriptions. **Preliminary solution**: API will always return current state for all fileds.
  All Subscriptionn types will be "cutted" and have "Subscription" postfix (i.e. `UserSubscription` instead of full `User`)

## Naming

Name your **Types** object like, or "noun".
Pattern: **[Type]**
`User`,

Name your **Subscription Types** object like, or "noun" + "Subscription" postfix. Subscription types should have different name because they will be "cutted" (don't have full Schema).
Pattern: **[Type]Subscription**
`UserSubscription`,

Name your Mutations "verb" first. Then the object, or "noun", if applicable. Add **s/es** for plural. Use camelCase.
Pattern: **[action][type]?s|es**
`createUser()`, `patchChatMessagge()`, ...

Name your Queries like object, or "noun", if applicable. Add **s/es** for plural. Use camelCase.
Pattern: **[type]?s|es**
`user`, `chatMessages`, ...

Name your Subscriptions like object, or "noun", if applicable. No plurals. Add `Subscription` in the end. Use camelCase.
Pattern: **[type]Subscription**
`userSubscription`, `chatMessageSubscription`, ...

## Inputs (Singular)

Use a single, required, unique, input object type as an argument for easier Mutations, Queries and Subscriptions execution on the client.

Mutations, Queries and Subscriptions should only ever have one input argument `input`.
`patchPost(input: { id: 4, post: { title: "..." } }) { ... }`
instead of
`patchPost(id: 4, newTitle: "...") { ... }`
Even if you need to use one argument like `uuid: UUID` wrap it inside `input`.
`user(input: { uuid: "..." })`

```graphql
interface Node {
    uuid: UUID!
}

input NodeInput implements Node {
    uuid: UUID!
}

input UserInput implements NodeInput {
    uuid: UUID!
}

type Query {
    user(input: UserInput!): UserPayload!
}
```

## Payloads (Singular)

Use a unique payload type for each Mutation/Query/Subscription and add the mutation/quiert/subscription’s output as a field to that payload type.
Just like when you design your input, nesting is a virtue for your GraphQL payload. Always create a custom object type for each of your Mutation/Query/Subscription and then add any output you want as a field of that custom object type.

```graphql
# Mutation Payload
type CreateUserPayload {
  user: User
  # errors: [Error!]
  # metadata: CreateUserMetadata
}
```

```graphql
# Query Payload
type UserPayload {
  user: User
  # errors: [Error!]
  # metadata: CustomMetadata ??
}
```

## Payload (on Delete)

By default deturn `uuid: UUID!`

````
type DeleteUserPayload {
    user {
        uuid: UUID!
        # Adittional fields could be added if needed
    }
}

## Identification

Always use `uuid: UUID!`.
```graphql
type Node {
    uuid: UUID!
}
````

## Collections

All collectios use Connection Type, event in nested objects.

```graphql
type User {
    name: String!
    posts(input: PostsConnectionInput): PostsConnection!
    ...
}
```

## Input (Connection)

```graphql
interface Nodes {
    uuids: [UUID!]!
}

input NodesInput implements Nodes {
    uuids: [UUID!]!
}

type ProductsInput {
	where: {
		title: String! # "where" fields realted to Product on top of the `where` object
		category: NodesInput,  # "where" fields realted to Product Sub Objects
		subcategory: NodesInput # "where" fields realted to Product Sub Objects
	}
    pagination: { # Could be changed to `relay: RelayInput!` for Cursor based pagination
        itemsPerPage: Int @default(10)
        pageNumber: Int @default(1)
    }
	sort: [ProductSortEnum!] @default(CREATED_DATETIME_DESC)
}

```

## Payload (Connection)

```graphql
interface ConnectionPayload {
    items: [Node!]!
    pageInfo: {
        currentPage: Int!
        totalPages: Int!
    }
	totalItems: Int!
}

type UserConnectionPayload implements ConnectionPayload {
    items: [User!]! # If can't implement like this than remove ConnectionPayload.items
    pageInfo: {
        currentPage: Int!
        totalPages: Int!
    }
	totalItems: Int!
}
```

## Patch by default

All mutations should use **patch** instead of **update** by default.
`patchUser()`, `patchChatMessage`

## Defaults

Max Page Size: 100 # If FE needs to get more than 100 items it needs to add pagiation
Default Page Size: 10
Default Sort: CREATED_DATETIME_DESC
Get All Items: Impossible with one request if more than 100

## Bulk updates

```graphql
patchUsers(input: [PatchUserInput!]!): [PatchUserPayload!]!
```

## Reordering

Reordering without `weight: Int` by default.
FE will send FULL list with new order, API will return list with same new order next time.
When user reorders he can't apply any filters or sorting, in other case it will be not default reordering
and we will define custom rules for it.!

## Unified Error Schema

```graphql
type Error {
	message: String! # Not user friendly error!
	code: String! # NOT_FOUND, ACCESS_DENIED, ...
    validation: [
        {
            message: String!
	        field: String! # Field on which vavlidation failed
        }
    ]
}
```

## Group Queries, Mutations, Subscriptions

Group queries/mutations/subscriptions by app domain/context.

```graphql
userQueries-UserQuieries, assetQueries, userMutations, assetMutations)
    assetsQuieries {
        assets(): AssetConectionPayload
        admins(): AssetsAdminConectionPayload
        users(): AssetsUserConnectionPayload
    }
     onboardingQuieries {
        onnboardings():  OnboardingConnectionPayload
        developers():  OnboardingDeveloperConnectionPayload
        mentors(): OnboardingMetorsConnectionPayload
    }
```
