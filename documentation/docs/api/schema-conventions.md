---
sidebar_position: 2
---

# GraphQL Schema Conventions

We need to follow conventions to make our Schema cosistent and easy to expand, maintain and work with.
Below conventios should be followed both from FE and BE side during API development and API design.

## Open questions

- FE should check how Apollo Client in Microfrontend batch Subscriptions. If each MF will use separate WS connection we nned to reduce amount of Subscriptions in Schema
- BE should check how to trigger Subscription for only requested from FE fields.
- BE should check how to handle "undefined" update in Subscriptions. **Preliminary solution**: API will always return current state for all fileds.
  All Subscriptionn types will be "cutted" and have "Subscription" postfix (i.e. `UserSubscription` instead of full `User`)
- How to delete field/s in Patch Mutation. **Preliminary solution**: add `fieldsToDelete: [Enum!]` to Patch Input.

## Comments

Use comments in **Not obvious** Schema fields to be as mach obvious as you can be! Create and update on API Design!

To make "persisted" comment which will be reflected in Schema type _three doublequotes_ above the field or type you want to comment.

```graphql
type User implements Node {
    id: UUID!
    """
    My cool descriptive comment about notObviousField
    """
    notObviousField: Secret
    ...
}
```

## Identification

Always use `id: UUID!` by default, instead of `id: ID!` or something else

```graphql
type User implements Node {
  id: UUID!
}
```

## Naming

### Types

Name your **Types** object like, or "noun". Use `PascalCase`.

All Types should by default implement `Node` interface

**Pattern**: {Type}

```graphql
type User implements Node {
    id: UUID!
    ...
}

type ChatMessage implements Node {
    id: UUID!
    ...
}
```

### Queries

Name your Queries like object, or "noun", if applicable. Add **s/es** for plural. Use `camelCase`.

**Pattern**: {type}?s|es

```graphql
type Query {
  user(input: UserInput!): UserPayload!
  chatMessage(input: ChatMessageInput!): ChatMessagePayload!
}
```

### Mutations

Name your Mutations "verb" first. Then the object, or "noun", if applicable. Add **s/es** for plural. Use `camelCase`.

**Pattern**: {action}{Type}?s|es

```graphql
type Mutation {
  createUser(input: CreateUserInput!): UserPayload!
  patchChatMessagge(input: PatchChatMessaggeInput!): ChatMessaggePayload!
}
```

### Subscriptions

Name your Subscriptions object like, or "noun" + "Subscription" postfix. Subscription types should have different name because they will be "cutted" (don't have full Schema).

**Pattern**: {Type}Subscription

```graphql
type UserSubscripton implements Node {
    id: UUID!
    ...
}

type ChatMessageSubscription implements Node {
    id: UUID!
    ...
}
```

### Connections

Name your Connections object like, or "noun" + "ConnectionPayload" postfix. Use singular type name

**Pattern**: {type}ConnectionPayload

```graphql
type Query {
  users(input: UsersInput!): UserConnectionPayload!
  chatMessages(input: ChatMessagesInput!): ChatMessageConnectionPayload!
}
```

### Enums

Name your Enums object like, use singular type name. Use `PascalCase`

**Pattern**: {Type}

```graphql
type ChatMessage implements Node {
    id: UUID!
    type: ChatMessageType!
    status: ChatMessageStatus!
    ...
}

enum ChatMessageType {
    ...
}

enum ChatMessageStatus {
    ...
}

enum UserSort {
    ...
}
```

## Interfaces

### Node

All types should implement Node interface by default.

```graphql
interface Node {
  id: UUID!
}

type User implements Node {
    id: UUID!
    ...
}

type ChatMessage implements Node {
    id: UUID!
    ...
}
```

### Nodes

Nodes interface is the same as Node but plural. Use when working with list of entities.

```graphql
interface Nodes {
  ids: [UUID!]!
}

input NodesInput {
  ids: [UUID!]!
}

input ListInput {
  add: NodesInput
  remove: NodesInput
}
```

### ConnectionPayload

ConnectionPayload interface should be used for all ConnectionPayload types.

```graphql
interface ConnectionPayload {
  items: [Node!]!
  pageInfo: PageInfo!
  totalItems: Int!
}

type PageInfo {
  currentPage: Int!
  totalPages: Int!
}

type UserConnectionPayload implements ConnectionPayload {
  items: [User!]! # If can't implement like this than remove ConnectionPayload.items
  pageInfo: PageInfo!
  totalItems: Int!
}
```

## Inputs

### Singular

Use a single, required, unique, input object type as an argument for easier Mutations, Queries and Subscriptions execution on the client.

Mutations, Queries and Subscriptions should only ever have one input argument `input`.
Even if you need to use one argument like `id: UUID!` wrap it inside `input`.

```graphql
# Bad 👎
type Mutation {
    createUser(id: UUID!, email: String!): UserPayload!
    patchUser(id: UUID!, email: String!): UserPayload!
    deleteUser(id: UUID!): UserPayload!: UserPayload!
}

# Good 👍
type Mutation {
    createUser(input: CreateUserInput!): UserPayload!
    patchUser(input: PatchUserInput!): UserPayload!
    deleteUser(input: DeleteUserInput!): DeleteUserPayload!
}

input CreateUserInput {
    ...
}

input PatchUserInput {
    id: UUID!
    ...
}

input DeleteUserInput {
    id: UUID!
    ...
}
```

### Type NodeInput

NodeInput is a helper general type to use for getting entities only by id

```graphql
input NodeInput {
  id: UUID!
}
```

### Type NodesInput

NodesInput is a helper general type to use for getting entities only by ids

```graphql
input NodesInput {
  ids: [UUID!]!
}
```

### Multiple (Connection)

Connection inputs should follow below structure

```graphql
type Query {
  products(input: ProductsInput!): ProductConnectionPayload!
}

input ProductsInput {
  where: ProductsWhereInput
  pagination: PageInput # Could be changed to "relay: RelayInput" for Cursor based pagination
  sort: [ProductSort!] = createdAt_DESC
}

input ProductsWhereInput {
  title: String! # "where" fields realted to Product on top of the `where` object
  category: NodesInput # "where" fields realted to Product Sub Objects
  subcategory: NodesInput # "where" fields realted to Product Sub Objects
}

input PageInput {
  itemsPerPage: Int = 10 @constraint(max: 100)
  pageNumber: Int = 1
}

enum ProductSort {
  createdAt_DESC
  createdAt_ASC
}
```

### Search by multiple fields

To search by multiple fields with **OR** condition use `query: String` field.

Add _persisted_ comment about how this field will filter entities.

:::warning
**For Performance reasons** use it only in needed places.
Don't use in palce where filter by one field required, create specific filter field instead!
:::

```graphql
input UserWhereInput {
  """
  Filters by 'firstName', 'lastName' and 'email', with 'OR' condition!
  """
  query: String

  # Below fileds should be created and used in cases where FE should filter by only one specific field at once.
  # In case of mutliple fileds with alues it will be AND condition
  firstName: String
  lastName: String
  email: String
}
```

## Payloads

### Singular

Use a unique payload type for each Delete Mutation and Subscription, for Query, Create, Patch and Update one general Payload could be used.

Just like when you design your input, nesting is a virtue for your GraphQL payload. Always create a custom object type and then add any output you want as a field of that custom object type.

```graphql
type Query {
  user(input: UserInput!): UserPayload!
  users(input: UsersInput!): UserConnectionPayload!
}

type Mutation {
  createUser(input: CreateUserInput!): UserPayload!
  patchUser(input: PatchUserInput!): UserPayload!
  updateUser(input: UpdateUserInput!): UserPayload!
  deleteUser(input: DeleteUserInput!): DeleteUserPayload!
}

type UserPayload {
  user: User
  # This approach gives us possibility to extend without Breaking Changes
  # errors: [Error!]
  # metadata: UserMetadata
}

type DeleteUserPayload {
  # By default return Node
  user: Node
}
```

### Multiple (Connection)

```graphql
interface ConnectionPayload {
  items: [Node!]!
  pageInfo: PageInfo!
  totalItems: Int!
}

type PageInfo {
  currentPage: Int!
  totalPages: Int!
}

type UserConnectionPayload implements ConnectionPayload {
  items: [User!]! # If can't implement like this than remove ConnectionPayload.items
  pageInfo: PageInfo!
  totalItems: Int!
}
```

## Collections

For all collections with Input and collections with more than 100 items use Connection Type, even in nested objects.

```graphql
type User {
    name: String!
    posts(input: PostsConnectionInput): PostsConnectionPayload!
    ...
}
```

## Update lists

If you need to update several entities in big list use `ListInput`

```graphql
input ListInput {
  add: NodesInput
  remove: NodesInput
}
```

## Patch by default

All mutations should use **patch** instead of **update** by default.
:::note
You can use one of them or both if needed
:::
:::warning
Use Flat Strucutre in Patch and Update Inputs! If you need to update nested object use nested object patch/update input.
:::

```graphql
type Mutatuion {
  patchUser(input: PatchUserInput!): UserPayload!
  updateUser(input: UpdateUserInput!): UserPayload!
}
```

## Max page size

**100** is maximum possible value for `itemsPerPage` field.
If FE needs to get more than 100 items it needs to add pagiation
:::info
It's impossible with one request to get more than 100 items!
:::

## Defaults

On API DEsign for ConnectionPayload write default sort vavlue, by default DESC to show latest created entities.

Default Sort: `createdAt_DESC`

## Bulk operations

### Update

For bulk update use below approach:

```graphql
patchUsers(input: PatchUsersInput!): UsersPayload!

input PatchUsersInput {
    users: [PatchUserInput!]!
}

type UsersPayload {
    users: [UserPayload!]!
}
```

### Delete

For bulk delete use below approach:

```graphql
deleteUsers(input: DeleteUsersInput!): DeleteUsersPayload!

input DeleteUsersInput {
    users: NodesInput!
}

type DeleteUsersPayload {
    users: Nodes
}
```

## Reordering

Reordering should be done without `weight: Int` by default.

FE will send FULL list with new order, API will return list with same new order next time.

When user reorders he can't apply any filters or sorting, or if users needs to reorder big lists it will be not default reordering
and we will define custom rules for it.!

In Schema write 2 new Enum items to SortEnum: [`weight_DESC`, `weight_ASC`]

## Unified Error Schema

```graphql
type Error {
  message: String! # Not user friendly error! For developers purpose only!
  code: ErrorCode!
  validation: [ValidationError!]
}

enum ErrorCode {
  NOT_FOUND
  ACCESS_DENIED
  # Add new items here ...
}

type ValidationError {
  message: String!
  field: String! # Field on which vavlidation failed
}
```

## Group Queries, Mutations, Subscriptions

Group queries/mutations/subscriptions by app domain/context.

```graphql
type Query {
    assetsQuieries {
        assets(): AssetConectionPayload
        asset(): AssetPayload
        assetTypes(): AssetTypesConnectionPayload
    }
}

type Mutation {
    assetsMutations {
        createAsset(): AssetPayload
        deleteAsset(): DeleteAssetPayload
        patchAsset(): AssetPayload
        createAssetType(): AssetTypePayload
    }
}
```
