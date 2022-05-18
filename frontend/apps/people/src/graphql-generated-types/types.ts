export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
  /** UUID */
  UUID: any;
  /** A file part in a multipart request */
  Upload: any;
  /** ZonedDateTime */
  ZonedDateTime: any;
};

export type Announcement = Node & {
  __typename?: 'Announcement';
  body: Scalars['String'];
  createdAt: Scalars['ZonedDateTime'];
  uuid: Scalars['UUID'];
};

export type AnnouncementConnectionPayload = ConnectionPayload & {
  __typename?: 'AnnouncementConnectionPayload';
  items: Array<Announcement>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type AnnouncementInput = {
  uuid: Scalars['UUID'];
};

export type AnnouncementPayload = {
  __typename?: 'AnnouncementPayload';
  item: Announcement;
};

export type AnnouncementsInput = {
  from?: InputMaybe<Scalars['ZonedDateTime']>;
  pagination?: InputMaybe<PageInput>;
  to?: InputMaybe<Scalars['ZonedDateTime']>;
};

export type Asset = Node & {
  __typename?: 'Asset';
  archivedDate?: Maybe<Scalars['ZonedDateTime']>;
  employee?: Maybe<Employee>;
  nextActionDate?: Maybe<Scalars['ZonedDateTime']>;
  purchasedDate?: Maybe<Scalars['ZonedDateTime']>;
  serial?: Maybe<Scalars['String']>;
  title?: Maybe<Scalars['String']>;
  type?: Maybe<AssetType>;
  uuid: Scalars['UUID'];
};

export type AssetConnectionPayload = ConnectionPayload & {
  __typename?: 'AssetConnectionPayload';
  items: Array<Asset>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type AssetInput = {
  uuid: Scalars['UUID'];
};

export type AssetPayload = {
  __typename?: 'AssetPayload';
  item?: Maybe<Asset>;
};

export type AssetType = Node & {
  __typename?: 'AssetType';
  icon?: Maybe<Scalars['String']>;
  title?: Maybe<Scalars['String']>;
  uuid: Scalars['UUID'];
};

export type AssetTypeConnection = ConnectionPayload & {
  __typename?: 'AssetTypeConnection';
  items: Array<AssetType>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type AssetTypeInput = {
  uuid: Scalars['UUID'];
};

export type AssetTypePayload = {
  __typename?: 'AssetTypePayload';
  item?: Maybe<AssetType>;
};

export type AssetWhereInput = {
  archived?: InputMaybe<Scalars['Boolean']>;
  employee?: InputMaybe<NodeInput>;
  /** asset.archivedDate is not null - TRUE */
  type?: InputMaybe<NodeInput>;
};

export type AssetsInput = {
  page?: InputMaybe<PageInput>;
  where?: InputMaybe<AssetWhereInput>;
};

export type ConnectionPayload = {
  items: Array<Node>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type CreateAnnouncementInput = {
  body: Scalars['String'];
};

export type CreateAssetInput = {
  archivedDate?: InputMaybe<Scalars['ZonedDateTime']>;
  employee?: InputMaybe<Scalars['UUID']>;
  nextActionDate?: InputMaybe<Scalars['ZonedDateTime']>;
  purchasedDate?: InputMaybe<Scalars['ZonedDateTime']>;
  serial?: InputMaybe<Scalars['String']>;
  title?: InputMaybe<Scalars['String']>;
  type?: InputMaybe<Scalars['UUID']>;
};

export type CreateAssetTypeInput = {
  title: Scalars['String'];
};

export type CreateEmployeeInput = {
  birthday: Scalars['ZonedDateTime'];
  firstName: Scalars['String'];
  lastName: Scalars['String'];
  location?: InputMaybe<Scalars['UUID']>;
  position?: InputMaybe<Scalars['UUID']>;
  reportingManager?: InputMaybe<Scalars['UUID']>;
  role: EmployeeRole;
};

export type CreateEmployeePositionInput = {
  name: Scalars['String'];
};

export type CreateLocationInput = {
  name: Scalars['String'];
};

export type CreateObjectiveInput = {
  comments?: InputMaybe<Scalars['String']>;
  description?: InputMaybe<Scalars['String']>;
  dueToDate: Scalars['ZonedDateTime'];
  employee: Scalars['UUID'];
  name: Scalars['String'];
  status?: InputMaybe<ObjectiveStatus>;
};

export type CreatePolicyInput = {
  file: ResourceInput;
  status: PolicyStatus;
  title: Scalars['String'];
};

export type CreateVacancyInput = {
  /** default NEW */
  assignTo?: InputMaybe<Scalars['UUID']>;
  description: Scalars['String'];
  position: Scalars['UUID'];
  priority: VacancyPriority;
  status: VacancyStatus;
};

export type DeleteAnnouncementInput = {
  uuid: Scalars['UUID'];
};

export type DeleteAnnouncementPayload = {
  __typename?: 'DeleteAnnouncementPayload';
  Announcement: Node;
};

export type DeleteEmployeePositionInput = {
  uuid: Scalars['UUID'];
};

export type DeleteEmployeePositionPayload = {
  __typename?: 'DeleteEmployeePositionPayload';
  employeePosition: Node;
};

export type DeleteObjectiveInput = {
  uuid: Scalars['UUID'];
};

export type DeleteObjectivePayload = {
  __typename?: 'DeleteObjectivePayload';
  objective: Node;
};

export type DeletePoliciesInput = {
  policies: NodesInput;
};

export type DeletePoliciesPayload = {
  __typename?: 'DeletePoliciesPayload';
  policies: DeletedNodes;
};

export type DeletedNodes = Nodes & {
  __typename?: 'DeletedNodes';
  uuids: Array<Scalars['UUID']>;
};

export type Employee = Node & {
  __typename?: 'Employee';
  birthday?: Maybe<Scalars['ZonedDateTime']>;
  firstName?: Maybe<Scalars['String']>;
  lastName?: Maybe<Scalars['String']>;
  location?: Maybe<Location>;
  position?: Maybe<EmployeePosition>;
  reportingManager?: Maybe<Employee>;
  role?: Maybe<EmployeeRole>;
  uuid: Scalars['UUID'];
};

export type EmployeeConnectionPayload = ConnectionPayload & {
  __typename?: 'EmployeeConnectionPayload';
  items: Array<Employee>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type EmployeeObjectivesWhereInput = {
  employee: NodeInput;
  status?: InputMaybe<Array<ObjectiveStatus>>;
};

export type EmployeePayload = {
  __typename?: 'EmployeePayload';
  item?: Maybe<Employee>;
};

export type EmployeePosition = Node & {
  __typename?: 'EmployeePosition';
  name?: Maybe<Scalars['String']>;
  uuid: Scalars['UUID'];
};

export type EmployeePositionPayload = {
  __typename?: 'EmployeePositionPayload';
  item?: Maybe<EmployeePosition>;
};

export enum EmployeeRole {
  Admin = 'ADMIN',
  Employee = 'EMPLOYEE'
}

export type EmployeeWhereInput = {
  location?: InputMaybe<NodeInput>;
};

export type EmployeesInput = {
  pagination?: InputMaybe<PageInput>;
  where?: InputMaybe<EmployeeWhereInput>;
};

export type LeaveRequest = Node & {
  __typename?: 'LeaveRequest';
  comment?: Maybe<Scalars['String']>;
  employee: Employee;
  requestDate?: Maybe<Scalars['ZonedDateTime']>;
  status?: Maybe<Scalars['String']>;
  uuid: Scalars['UUID'];
};

export type Location = Node & {
  __typename?: 'Location';
  name: Scalars['String'];
  uuid: Scalars['UUID'];
};

export type LocationConnectionPayload = ConnectionPayload & {
  __typename?: 'LocationConnectionPayload';
  items: Array<Location>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type LocationInput = {
  uuid: Scalars['UUID'];
};

export type LocationPayload = {
  __typename?: 'LocationPayload';
  location: Location;
};

export type Mutation = {
  __typename?: 'Mutation';
  createAnnouncement: AnnouncementPayload;
  createAsset: AssetPayload;
  createAssetType: AssetTypePayload;
  createEmployee: EmployeePayload;
  createEmployeePosition: EmployeePositionPayload;
  createLocation: LocationPayload;
  createObjective: ObjectivePayload;
  createPolicy: PolicyPayload;
  createVacancy: VacancyPayload;
  deleteAnnouncement: DeleteAnnouncementPayload;
  deleteEmployeePosition: DeleteEmployeePositionPayload;
  deleteObjective: DeleteObjectivePayload;
  deletePolicies: DeletePoliciesPayload;
  updateAnnouncement: AnnouncementPayload;
  updateAsset: AssetPayload;
  updateEmployeePosition: EmployeePositionPayload;
  updateLocation: LocationPayload;
  updateObjective: ObjectivePayload;
  updateVacancy: VacancyPayload;
  uploadResource?: Maybe<ResourcePayload>;
};


export type MutationCreateAnnouncementArgs = {
  input: CreateAnnouncementInput;
};


export type MutationCreateAssetArgs = {
  input: CreateAssetInput;
};


export type MutationCreateAssetTypeArgs = {
  input: CreateAssetTypeInput;
};


export type MutationCreateEmployeeArgs = {
  input: CreateEmployeeInput;
};


export type MutationCreateEmployeePositionArgs = {
  input: CreateEmployeePositionInput;
};


export type MutationCreateLocationArgs = {
  input: CreateLocationInput;
};


export type MutationCreateObjectiveArgs = {
  input: CreateObjectiveInput;
};


export type MutationCreatePolicyArgs = {
  input: CreatePolicyInput;
};


export type MutationCreateVacancyArgs = {
  input: CreateVacancyInput;
};


export type MutationDeleteAnnouncementArgs = {
  input: DeleteAnnouncementInput;
};


export type MutationDeleteEmployeePositionArgs = {
  input: DeleteEmployeePositionInput;
};


export type MutationDeleteObjectiveArgs = {
  input: DeleteObjectiveInput;
};


export type MutationDeletePoliciesArgs = {
  input: DeletePoliciesInput;
};


export type MutationUpdateAnnouncementArgs = {
  input: UpdateAnnouncementInput;
};


export type MutationUpdateAssetArgs = {
  input: UpdateAssetInput;
};


export type MutationUpdateEmployeePositionArgs = {
  input: UpdateEmployeePositionInput;
};


export type MutationUpdateLocationArgs = {
  input: UpdateLocationInput;
};


export type MutationUpdateObjectiveArgs = {
  input: UpdateObjectiveInput;
};


export type MutationUpdateVacancyArgs = {
  input: UpdateVacancyInput;
};


export type MutationUploadResourceArgs = {
  resource: Scalars['Upload'];
  thumbnail?: InputMaybe<Scalars['Upload']>;
};

export type Node = {
  uuid: Scalars['UUID'];
};

export type NodeInput = {
  uuid: Scalars['UUID'];
};

export type Nodes = {
  uuids: Array<Scalars['UUID']>;
};

export type NodesInput = {
  uuids: Array<Scalars['UUID']>;
};

export type Objective = Node & {
  __typename?: 'Objective';
  comments?: Maybe<Scalars['String']>;
  description?: Maybe<Scalars['String']>;
  dueToDate?: Maybe<Scalars['ZonedDateTime']>;
  employee: Employee;
  name: Scalars['String'];
  status?: Maybe<ObjectiveStatus>;
  uuid: Scalars['UUID'];
};

export type ObjectiveConnectionPayload = ConnectionPayload & {
  __typename?: 'ObjectiveConnectionPayload';
  items: Array<Objective>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type ObjectiveInput = {
  uuid: Scalars['UUID'];
};

export type ObjectivePayload = {
  __typename?: 'ObjectivePayload';
  item: Objective;
};

export enum ObjectiveStatus {
  Canceled = 'CANCELED',
  Done = 'DONE',
  InProgress = 'IN_PROGRESS',
  New = 'NEW',
  Postponed = 'POSTPONED'
}

export type ObjectivesInput = {
  pagination?: InputMaybe<PageInput>;
  where?: InputMaybe<EmployeeObjectivesWhereInput>;
};

export type PageInfo = {
  __typename?: 'PageInfo';
  currentPage: Scalars['Int'];
  totalPages: Scalars['Int'];
};

export type PageInput = {
  itemsPerPage?: InputMaybe<Scalars['Int']>;
  pageNumber?: InputMaybe<Scalars['Int']>;
};

export type PoliciesInput = {
  pagination?: InputMaybe<PageInput>;
  sort?: InputMaybe<Array<PolicySort>>;
  where?: InputMaybe<PoliciesWhereInput>;
};

export type PoliciesWhereInput = {
  /** API will filter by "title". */
  query?: InputMaybe<Scalars['String']>;
};

export type Policy = Node & {
  __typename?: 'Policy';
  file: Resource;
  owner: Employee;
  publicationDate?: Maybe<Scalars['ZonedDateTime']>;
  status: PolicyStatus;
  title: Scalars['String'];
  uuid: Scalars['UUID'];
};

export type PolicyConnectionPayload = ConnectionPayload & {
  __typename?: 'PolicyConnectionPayload';
  items: Array<Policy>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type PolicyPayload = {
  __typename?: 'PolicyPayload';
  policy?: Maybe<Policy>;
};

export enum PolicySort {
  PublicationDateAsc = 'publicationDate_ASC',
  PublicationDateDesc = 'publicationDate_DESC'
}

export enum PolicyStatus {
  Published = 'PUBLISHED',
  Unpublished = 'UNPUBLISHED'
}

export type Query = {
  __typename?: 'Query';
  announcement: AnnouncementPayload;
  announcements: AnnouncementConnectionPayload;
  asset: AssetPayload;
  assetType: AssetTypePayload;
  assetTypes: AssetTypeConnection;
  assets: AssetConnectionPayload;
  employees: EmployeeConnectionPayload;
  location: LocationPayload;
  locations: LocationConnectionPayload;
  objective: ObjectivePayload;
  objectives: ObjectiveConnectionPayload;
  policies: PolicyConnectionPayload;
  resource: ResourcePayload;
  vacancies: VacancyConnectionPayload;
  vacancy: VacancyPayload;
};


export type QueryAnnouncementArgs = {
  input: AnnouncementInput;
};


export type QueryAnnouncementsArgs = {
  input: AnnouncementsInput;
};


export type QueryAssetArgs = {
  input: AssetInput;
};


export type QueryAssetTypeArgs = {
  input: AssetTypeInput;
};


export type QueryAssetsArgs = {
  input?: InputMaybe<AssetsInput>;
};


export type QueryEmployeesArgs = {
  input?: InputMaybe<EmployeesInput>;
};


export type QueryLocationArgs = {
  input: LocationInput;
};


export type QueryObjectiveArgs = {
  input: ObjectiveInput;
};


export type QueryObjectivesArgs = {
  input: ObjectivesInput;
};


export type QueryPoliciesArgs = {
  input: PoliciesInput;
};


export type QueryResourceArgs = {
  input: ResourceInput;
};


export type QueryVacanciesArgs = {
  input: VacanciesInput;
};


export type QueryVacancyArgs = {
  input: VacancyInput;
};

export type Resource = {
  __typename?: 'Resource';
  thumbnail?: Maybe<Scalars['String']>;
  url: Scalars['String'];
  uuid: Scalars['UUID'];
};

export type ResourceInput = {
  uuid: Scalars['UUID'];
};

export type ResourcePayload = {
  __typename?: 'ResourcePayload';
  resource?: Maybe<Resource>;
};

export type UpdateAnnouncementInput = {
  body: Scalars['String'];
  uuid: Scalars['UUID'];
};

export type UpdateAssetInput = {
  archivedDate?: InputMaybe<Scalars['ZonedDateTime']>;
  employee?: InputMaybe<Scalars['UUID']>;
  nextActionDate?: InputMaybe<Scalars['ZonedDateTime']>;
  purchasedDate?: InputMaybe<Scalars['ZonedDateTime']>;
  serial?: InputMaybe<Scalars['String']>;
  title?: InputMaybe<Scalars['String']>;
  type?: InputMaybe<Scalars['UUID']>;
  uuid: Scalars['UUID'];
};

export type UpdateEmployeePositionInput = {
  name: Scalars['String'];
  uuid: Scalars['UUID'];
};

export type UpdateLocationInput = {
  name: Scalars['String'];
  uuid: Scalars['UUID'];
};

export type UpdateObjectiveInput = {
  comments?: InputMaybe<Scalars['String']>;
  description?: InputMaybe<Scalars['String']>;
  dueToDate: Scalars['ZonedDateTime'];
  employee: Scalars['UUID'];
  name: Scalars['String'];
  status?: InputMaybe<ObjectiveStatus>;
  uuid: Scalars['UUID'];
};

export type UpdateVacancyInput = {
  assignTo?: InputMaybe<Scalars['UUID']>;
  description: Scalars['String'];
  position: Scalars['UUID'];
  priority: VacancyPriority;
  status: VacancyStatus;
  uuid: Scalars['UUID'];
};

export type VacanciesInput = {
  pagination?: InputMaybe<PageInput>;
  where?: InputMaybe<VacancyWhereInput>;
};

export type Vacancy = Node & {
  __typename?: 'Vacancy';
  /** default NEW */
  assignTo?: Maybe<Employee>;
  description: Scalars['String'];
  position: EmployeePosition;
  priority: VacancyPriority;
  status: VacancyStatus;
  uuid: Scalars['UUID'];
};

export type VacancyConnectionPayload = ConnectionPayload & {
  __typename?: 'VacancyConnectionPayload';
  items: Array<Vacancy>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type VacancyInput = {
  uuid: Scalars['UUID'];
};

export type VacancyPayload = {
  __typename?: 'VacancyPayload';
  item: Vacancy;
};

export enum VacancyPriority {
  High = 'HIGH',
  Low = 'LOW',
  Normal = 'NORMAL'
}

export enum VacancyStatus {
  Closed = 'CLOSED',
  InProgress = 'IN_PROGRESS',
  New = 'NEW',
  Postponed = 'POSTPONED',
  Rejected = 'REJECTED'
}

export type VacancyWhereInput = {
  assignTo?: InputMaybe<NodeInput>;
  position: Array<NodeInput>;
  status: Array<VacancyStatus>;
};
