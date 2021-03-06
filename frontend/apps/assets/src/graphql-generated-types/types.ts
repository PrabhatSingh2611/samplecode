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
  /** An RFC-3339 compliant DateTime Scalar */
  DateTime: any;
  /** A universally unique identifier compliant UUID Scalar */
  UUID: any;
  /** A file part in a multipart request */
  Upload: any;
  /** A Url scalar */
  Url: any;
};

export type Announcement = Node & {
  __typename?: 'Announcement';
  body: Scalars['String'];
  createdAt: Scalars['DateTime'];
  id: Scalars['UUID'];
};

export type AnnouncementConnectionPayload = ConnectionPayload & {
  __typename?: 'AnnouncementConnectionPayload';
  items: Array<Announcement>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type AnnouncementInput = {
  id: Scalars['UUID'];
};

export type AnnouncementPayload = {
  __typename?: 'AnnouncementPayload';
  item: Announcement;
};

export type AnnouncementsInput = {
  from?: InputMaybe<Scalars['DateTime']>;
  pagination?: InputMaybe<PageInput>;
  to?: InputMaybe<Scalars['DateTime']>;
};

export type ArchiveAssetInput = {
  asset: NodeInput;
};

export type ArchiveAssetPayload = {
  __typename?: 'ArchiveAssetPayload';
  asset?: Maybe<Node>;
};

export type Asset = Node & {
  __typename?: 'Asset';
  actionOnName?: Maybe<Scalars['String']>;
  archivedDate?: Maybe<Scalars['DateTime']>;
  assignee?: Maybe<Employee>;
  comment?: Maybe<Scalars['String']>;
  id: Scalars['UUID'];
  location?: Maybe<Location>;
  nextActionDate?: Maybe<Scalars['DateTime']>;
  serialNumber: Scalars['String'];
  tagNumber?: Maybe<Scalars['String']>;
  title?: Maybe<Scalars['String']>;
  type: AssetType;
  waybillDate: Scalars['DateTime'];
};

export type AssetConnectionPayload = ConnectionPayload & {
  __typename?: 'AssetConnectionPayload';
  items: Array<Asset>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type AssetInput = {
  id: Scalars['UUID'];
};

export type AssetPayload = {
  __typename?: 'AssetPayload';
  asset?: Maybe<Asset>;
};

export enum AssetSortEnum {
  WaybillDateAsc = 'waybillDate_ASC',
  WaybillDateDesc = 'waybillDate_DESC'
}

export type AssetType = Node & {
  __typename?: 'AssetType';
  category?: Maybe<AssetTypeCategory>;
  createdAt: Scalars['DateTime'];
  iconName: Scalars['String'];
  id: Scalars['UUID'];
  name: Scalars['String'];
};

export type AssetTypeCategoriesInput = {
  pagination?: InputMaybe<PageInput>;
  sort?: InputMaybe<Array<AssetTypeSort>>;
  where?: InputMaybe<AssetTypeCategoriesWhereInput>;
};

export type AssetTypeCategoriesWhereInput = {
  name?: InputMaybe<Scalars['String']>;
};

export type AssetTypeCategory = Node & {
  __typename?: 'AssetTypeCategory';
  id: Scalars['UUID'];
  name: Scalars['String'];
  types: Array<AssetType>;
};

export type AssetTypeCategoryConnectionPayload = ConnectionPayload & {
  __typename?: 'AssetTypeCategoryConnectionPayload';
  items: Array<AssetTypeCategory>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type AssetTypeCategoryPayload = {
  __typename?: 'AssetTypeCategoryPayload';
  assetTypeCategory?: Maybe<AssetTypeCategory>;
};

export type AssetTypeConnectionPayload = ConnectionPayload & {
  __typename?: 'AssetTypeConnectionPayload';
  items: Array<AssetType>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type AssetTypeInput = {
  id: Scalars['UUID'];
};

export type AssetTypePayload = {
  __typename?: 'AssetTypePayload';
  assetType: AssetType;
};

export enum AssetTypeSort {
  CreatedAtAsc = 'createdAt_ASC',
  CreatedAtDesc = 'createdAt_DESC'
}

export type AssetTypesInput = {
  pagination?: InputMaybe<PageInput>;
  sort?: InputMaybe<Array<AssetTypeSort>>;
  where?: InputMaybe<AssetTypesWhereInput>;
};

export type AssetTypesWhereInput = {
  category?: InputMaybe<NodeInput>;
  name?: InputMaybe<Scalars['String']>;
};

export type AssetWhereInput = {
  archived?: InputMaybe<Scalars['Boolean']>;
  assignee?: InputMaybe<NodesInput>;
  location?: InputMaybe<NodesInput>;
  /** Filters by title OR serialNumber OR tagNumber */
  query?: InputMaybe<Scalars['String']>;
  type?: InputMaybe<NodesInput>;
};

export type AssetsInput = {
  pagination?: InputMaybe<PageInput>;
  sort?: InputMaybe<Array<AssetSortEnum>>;
  where?: InputMaybe<AssetWhereInput>;
};

export type Candidate = Node & {
  __typename?: 'Candidate';
  attachments: Array<Resource>;
  firstName: Scalars['String'];
  id: Scalars['UUID'];
  lastName: Scalars['String'];
  linkedIn: Scalars['String'];
  status: CandidateStatus;
  vacancy: Vacancy;
};

export type CandidateConnectionPayload = ConnectionPayload & {
  __typename?: 'CandidateConnectionPayload';
  items: Array<Candidate>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type CandidateInput = {
  id: Scalars['UUID'];
};

export type CandidatePayLoad = {
  __typename?: 'CandidatePayLoad';
  candidate?: Maybe<Candidate>;
};

export enum CandidateStatus {
  New = 'NEW',
  OfferSent = 'OFFER_SENT',
  Pass = 'PASS',
  Rejected = 'REJECTED'
}

export type CandidatesInput = {
  pagination?: InputMaybe<PageInput>;
  where?: InputMaybe<VacancyCandidatesWhereInput>;
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
  actionOnName?: InputMaybe<Scalars['String']>;
  archivedDate?: InputMaybe<Scalars['DateTime']>;
  assignee?: InputMaybe<NodeInput>;
  comment?: InputMaybe<Scalars['String']>;
  location?: InputMaybe<NodeInput>;
  nextActionDate?: InputMaybe<Scalars['DateTime']>;
  serialNumber: Scalars['String'];
  tagNumber?: InputMaybe<Scalars['String']>;
  title: Scalars['String'];
  type: NodeInput;
  waybillDate: Scalars['DateTime'];
};

export type CreateAssetTypeCategoryInput = {
  name: Scalars['String'];
};

export type CreateAssetTypeInput = {
  category?: InputMaybe<NodeInput>;
  iconName: Scalars['String'];
  name: Scalars['String'];
};

export type CreateCandidateInput = {
  attachments: NodesInput;
  firstName: Scalars['String'];
  lastName: Scalars['String'];
  linkedIn: Scalars['String'];
  status: CandidateStatus;
  vacancy: Scalars['UUID'];
};

export type CreateEmployeeInput = {
  birthday: Scalars['DateTime'];
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

export type CreateLeaveRequestInput = {
  comment?: InputMaybe<Scalars['String']>;
  period: StrictDatePeriod;
  status: LeaveRequestStatus;
  type: NodeInput;
};

export type CreateLocationCountryInput = {
  iconName: Scalars['String'];
  name: Scalars['String'];
};

export type CreateLocationInput = {
  country: NodeInput;
  name: Scalars['String'];
};

export type CreateObjectiveInput = {
  comments?: InputMaybe<Scalars['String']>;
  description?: InputMaybe<Scalars['String']>;
  dueToDate: Scalars['DateTime'];
  employee: Scalars['UUID'];
  name: Scalars['String'];
  status?: InputMaybe<ObjectiveStatus>;
};

export type CreatePolicyInput = {
  file: ResourceInput;
  status: PolicyStatus;
  title: Scalars['String'];
};

export type CreateQuestionInput = {
  body: Scalars['String'];
  options?: InputMaybe<Array<InputMaybe<CreateQuestionOptionInput>>>;
  type: QuestionType;
};

export type CreateQuestionOptionInput = {
  text: Scalars['String'];
};

export type CreateSurveyInput = {
  description: Scalars['String'];
  questions: Array<InputMaybe<CreateQuestionInput>>;
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
  id: Scalars['UUID'];
};

export type DeleteAnnouncementPayload = {
  __typename?: 'DeleteAnnouncementPayload';
  Announcement: Node;
};

export type DeleteEmployeePositionInput = {
  id: Scalars['UUID'];
};

export type DeleteEmployeePositionPayload = {
  __typename?: 'DeleteEmployeePositionPayload';
  employeePosition: Node;
};

export type DeleteLeaveRequestInput = {
  id: Scalars['UUID'];
};

export type DeleteLeaveRequestPayload = {
  __typename?: 'DeleteLeaveRequestPayload';
  leaveRequest: Node;
};

export type DeleteObjectiveInput = {
  id: Scalars['UUID'];
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

export type DeleteSurveyInput = {
  id: Scalars['UUID'];
};

export type DeleteSurveyPayload = {
  __typename?: 'DeleteSurveyPayload';
  Survey: Node;
};

export type DeletedNodes = Nodes & {
  __typename?: 'DeletedNodes';
  ids: Array<Scalars['UUID']>;
};

export type Employee = Node & {
  __typename?: 'Employee';
  avatar?: Maybe<Scalars['String']>;
  birthday?: Maybe<Scalars['DateTime']>;
  email?: Maybe<Scalars['String']>;
  firstName?: Maybe<Scalars['String']>;
  id: Scalars['UUID'];
  lastName?: Maybe<Scalars['String']>;
  location?: Maybe<Location>;
  position?: Maybe<EmployeePosition>;
  reportingManager?: Maybe<Employee>;
  role?: Maybe<EmployeeRole>;
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
  id: Scalars['UUID'];
  name?: Maybe<Scalars['String']>;
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
  /** Filters by 'firstName', 'lastName' and 'email', with 'OR' condition! */
  query?: InputMaybe<Scalars['String']>;
};

export type EmployeesInput = {
  pagination?: InputMaybe<PageInput>;
  sort?: Array<EmployeesSortEnum>;
  where?: InputMaybe<EmployeeWhereInput>;
};

export enum EmployeesSortEnum {
  FirstNameAsc = 'firstName_ASC',
  FirstNameDesc = 'firstName_DESC'
}

export type LeaveRequest = Node & {
  __typename?: 'LeaveRequest';
  comment?: Maybe<Scalars['String']>;
  employee: Employee;
  endDate: Scalars['DateTime'];
  id: Scalars['UUID'];
  numberOfDays: Scalars['Int'];
  requestDate: Scalars['DateTime'];
  startDate: Scalars['DateTime'];
  status: LeaveRequestStatus;
  type: LeaveType;
};

export type LeaveRequestConnectionPayload = ConnectionPayload & {
  __typename?: 'LeaveRequestConnectionPayload';
  items: Array<LeaveRequest>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type LeaveRequestInput = {
  id: Scalars['UUID'];
};

export type LeaveRequestPayload = {
  __typename?: 'LeaveRequestPayload';
  leaveRequest?: Maybe<LeaveRequest>;
};

export enum LeaveRequestStatus {
  Approved = 'APPROVED',
  Declined = 'DECLINED',
  New = 'NEW'
}

export type LeaveRequestWhereInput = {
  approver?: InputMaybe<NodeInput>;
  employee?: InputMaybe<NodeInput>;
  endDate?: InputMaybe<Scalars['DateTime']>;
  startDate?: InputMaybe<Scalars['DateTime']>;
};

export type LeaveRequestsInput = {
  pagination?: InputMaybe<PageInput>;
  sort?: Array<LeaveRequestsSortEnum>;
  where?: InputMaybe<LeaveRequestWhereInput>;
};

export enum LeaveRequestsSortEnum {
  PendingAsc = 'pending_ASC',
  PendingDesc = 'pending_DESC',
  StartDateAsc = 'startDate_ASC',
  StartDateDesc = 'startDate_DESC'
}

export type LeaveType = Node & {
  __typename?: 'LeaveType';
  days: Scalars['Int'];
  endOfYearAction: LeaveTypeEndOfYearAction;
  id: Scalars['UUID'];
  name: Scalars['String'];
};

export type LeaveTypeConnectionPayload = ConnectionPayload & {
  __typename?: 'LeaveTypeConnectionPayload';
  items: Array<LeaveType>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export enum LeaveTypeEndOfYearAction {
  Expire = 'EXPIRE',
  Move = 'MOVE'
}

export type LeaveTypesInput = {
  /** where: LeaveTypesWhereInput! */
  pagination?: InputMaybe<PageInput>;
  sort?: InputMaybe<Array<LeaveTypesSortEnum>>;
};

export enum LeaveTypesSortEnum {
  NameAsc = 'name_ASC',
  NameDesc = 'name_DESC'
}

export type Location = Node & {
  __typename?: 'Location';
  /** this can be City, but also can be smth like ???Main office???, ???Warehouse' or whatever */
  country: LocationCountry;
  id: Scalars['UUID'];
  name?: Maybe<Scalars['String']>;
};

export type LocationConnectionPayload = ConnectionPayload & {
  __typename?: 'LocationConnectionPayload';
  items: Array<Location>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type LocationCountriesInput = {
  pagination?: InputMaybe<PageInput>;
  sort?: InputMaybe<Array<LocationCountrySort>>;
  where?: InputMaybe<LocationCountriesWhereInput>;
};

export type LocationCountriesWhereInput = {
  name?: InputMaybe<Scalars['String']>;
};

export type LocationCountry = Node & {
  __typename?: 'LocationCountry';
  iconName: Scalars['String'];
  id: Scalars['UUID'];
  locations: Array<Location>;
  name: Scalars['String'];
};

export type LocationCountryConnectionPayload = ConnectionPayload & {
  __typename?: 'LocationCountryConnectionPayload';
  items: Array<LocationCountry>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type LocationCountryPayload = {
  __typename?: 'LocationCountryPayload';
  locationCountry?: Maybe<LocationCountry>;
};

export enum LocationCountrySort {
  NameAsc = 'name_ASC',
  NameDesc = 'name_DESC'
}

export type LocationInput = {
  id: Scalars['UUID'];
};

export type LocationPayload = {
  __typename?: 'LocationPayload';
  location: Location;
};

export enum LocationSort {
  NameAsc = 'name_ASC',
  NameDesc = 'name_DESC'
}

export type LocationsInput = {
  pagination?: InputMaybe<PageInput>;
  sort?: InputMaybe<Array<LocationSort>>;
  where?: InputMaybe<LocationsWhereInput>;
};

export type LocationsWhereInput = {
  country?: InputMaybe<NodeInput>;
  name?: InputMaybe<Scalars['String']>;
};

export type Mutation = {
  __typename?: 'Mutation';
  archiveAsset: ArchiveAssetPayload;
  createAnnouncement: AnnouncementPayload;
  createAsset: AssetPayload;
  createAssetType: AssetTypePayload;
  createAssetTypeCategory: AssetTypeCategoryPayload;
  createCandidate: CandidatePayLoad;
  createEmployee: EmployeePayload;
  createEmployeePosition: EmployeePositionPayload;
  createLeaveRequest: LeaveRequestPayload;
  createLocation: LocationPayload;
  createLocationCountry: LocationCountryPayload;
  createObjective: ObjectivePayload;
  createPolicy: PolicyPayload;
  createSurvey: SurveyPayload;
  createVacancy: VacancyPayload;
  deleteAnnouncement: DeleteAnnouncementPayload;
  deleteEmployeePosition: DeleteEmployeePositionPayload;
  deleteLeaveRequest: DeleteLeaveRequestPayload;
  deleteObjective: DeleteObjectivePayload;
  deletePolicies: DeletePoliciesPayload;
  deleteSurvey: DeleteSurveyPayload;
  patchAssetTypeCategory: AssetTypeCategoryPayload;
  patchCandidate: CandidatePayLoad;
  patchLeaveRequest: LeaveRequestPayload;
  patchLocationCountry: LocationCountryPayload;
  patchSurvey: SurveyPayload;
  updateAnnouncement: AnnouncementPayload;
  updateAsset: AssetPayload;
  updateAssetType: AssetTypePayload;
  updateEmployee: EmployeePayload;
  updateEmployeePosition: EmployeePositionPayload;
  updateLocation: LocationPayload;
  updateObjective: ObjectivePayload;
  updateVacancy: VacancyPayload;
  uploadResource?: Maybe<ResourcePayload>;
};


export type MutationArchiveAssetArgs = {
  input: ArchiveAssetInput;
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


export type MutationCreateAssetTypeCategoryArgs = {
  input: CreateAssetTypeCategoryInput;
};


export type MutationCreateCandidateArgs = {
  input: CreateCandidateInput;
};


export type MutationCreateEmployeeArgs = {
  input: CreateEmployeeInput;
};


export type MutationCreateEmployeePositionArgs = {
  input: CreateEmployeePositionInput;
};


export type MutationCreateLeaveRequestArgs = {
  input: CreateLeaveRequestInput;
};


export type MutationCreateLocationArgs = {
  input: CreateLocationInput;
};


export type MutationCreateLocationCountryArgs = {
  input: CreateLocationCountryInput;
};


export type MutationCreateObjectiveArgs = {
  input: CreateObjectiveInput;
};


export type MutationCreatePolicyArgs = {
  input: CreatePolicyInput;
};


export type MutationCreateSurveyArgs = {
  input: CreateSurveyInput;
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


export type MutationDeleteLeaveRequestArgs = {
  input: DeleteLeaveRequestInput;
};


export type MutationDeleteObjectiveArgs = {
  input: DeleteObjectiveInput;
};


export type MutationDeletePoliciesArgs = {
  input: DeletePoliciesInput;
};


export type MutationDeleteSurveyArgs = {
  input: DeleteSurveyInput;
};


export type MutationPatchAssetTypeCategoryArgs = {
  input: PatchAssetTypeCategoryInput;
};


export type MutationPatchCandidateArgs = {
  input: PatchCandidateInput;
};


export type MutationPatchLeaveRequestArgs = {
  input: PatchLeaveRequestInput;
};


export type MutationPatchLocationCountryArgs = {
  input: PatchLocationCountryInput;
};


export type MutationPatchSurveyArgs = {
  input: PatchSurveyInput;
};


export type MutationUpdateAnnouncementArgs = {
  input: UpdateAnnouncementInput;
};


export type MutationUpdateAssetArgs = {
  input: UpdateAssetInput;
};


export type MutationUpdateAssetTypeArgs = {
  input: UpdateAssetTypeInput;
};


export type MutationUpdateEmployeeArgs = {
  input: UpdateEmployeeInput;
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
  id: Scalars['UUID'];
};

export type NodeInput = {
  id: Scalars['UUID'];
};

export type Nodes = {
  ids: Array<Scalars['UUID']>;
};

export type NodesInput = {
  ids: Array<Scalars['UUID']>;
};

export type Objective = Node & {
  __typename?: 'Objective';
  comments?: Maybe<Scalars['String']>;
  description?: Maybe<Scalars['String']>;
  dueToDate?: Maybe<Scalars['DateTime']>;
  employee: Employee;
  id: Scalars['UUID'];
  name: Scalars['String'];
  status?: Maybe<ObjectiveStatus>;
};

export type ObjectiveConnectionPayload = ConnectionPayload & {
  __typename?: 'ObjectiveConnectionPayload';
  items: Array<Objective>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type ObjectiveInput = {
  id: Scalars['UUID'];
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

export type OptionConnectionPayload = ConnectionPayload & {
  __typename?: 'OptionConnectionPayload';
  items: Array<QuestionOption>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
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

export type PatchAssetTypeCategoryInput = {
  id: Scalars['UUID'];
  name?: InputMaybe<Scalars['String']>;
};

export type PatchCandidateInput = {
  attachments?: InputMaybe<NodesInput>;
  firstName?: InputMaybe<Scalars['String']>;
  id: Scalars['UUID'];
  lastName?: InputMaybe<Scalars['String']>;
  linkedIn?: InputMaybe<Scalars['String']>;
  status?: InputMaybe<CandidateStatus>;
  vacancy?: InputMaybe<Scalars['UUID']>;
};

export type PatchLeaveRequestInput = {
  comment?: InputMaybe<Scalars['String']>;
  employee?: InputMaybe<NodeInput>;
  id: Scalars['UUID'];
  period?: InputMaybe<StrictDatePeriod>;
  status?: InputMaybe<LeaveRequestStatus>;
  type?: InputMaybe<NodeInput>;
};

export type PatchLocationCountryInput = {
  iconName?: InputMaybe<Scalars['String']>;
  id: Scalars['UUID'];
  name?: InputMaybe<Scalars['String']>;
};

export type PatchSurveyInput = {
  description?: InputMaybe<Scalars['String']>;
  id: Scalars['UUID'];
  questions?: InputMaybe<Array<InputMaybe<CreateQuestionInput>>>;
  title?: InputMaybe<Scalars['String']>;
};

export type Playbook = Node & {
  __typename?: 'Playbook';
  createdAt: Scalars['DateTime'];
  id: Scalars['UUID'];
  image: Resource;
  steps: Array<PlaybookStep>;
  title: Scalars['String'];
  updatedAt: Scalars['DateTime'];
};

export type PlaybookConnectionPayload = ConnectionPayload & {
  __typename?: 'PlaybookConnectionPayload';
  items: Array<Playbook>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type PlaybookInput = {
  id: Scalars['UUID'];
};

export type PlaybookPayload = {
  __typename?: 'PlaybookPayload';
  playbook?: Maybe<Playbook>;
};

export type PlaybookResource = Node & {
  __typename?: 'PlaybookResource';
  id: Scalars['UUID'];
  resource: Resource;
};

export enum PlaybookSort {
  CreatedAtAsc = 'createdAt_ASC',
  CreatedAtDesc = 'createdAt_DESC'
}

export type PlaybookStep = Node & {
  __typename?: 'PlaybookStep';
  id: Scalars['UUID'];
  resource?: Maybe<PlaybookResource>;
  taskList?: Maybe<PlaybookTaskList>;
  title: Scalars['String'];
  type: PlaybookStepType;
  video?: Maybe<PlaybookVideo>;
};

export enum PlaybookStepType {
  Resource = 'RESOURCE',
  TaskList = 'TASK_LIST',
  Video = 'VIDEO'
}

export type PlaybookTask = Node & {
  __typename?: 'PlaybookTask';
  id: Scalars['UUID'];
  title: Scalars['String'];
};

export type PlaybookTaskList = Node & {
  __typename?: 'PlaybookTaskList';
  id: Scalars['UUID'];
  tasks: Array<PlaybookTask>;
};

export type PlaybookVideo = Node & {
  __typename?: 'PlaybookVideo';
  description: Scalars['String'];
  id: Scalars['UUID'];
  url: Scalars['Url'];
};

export type PlaybooksInput = {
  pagination?: InputMaybe<PageInput>;
  sort?: InputMaybe<Array<PlaybookSort>>;
  where?: InputMaybe<PlaybooksWhereInput>;
};

export type PlaybooksWhereInput = {
  title: Scalars['String'];
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
  id: Scalars['UUID'];
  owner: Employee;
  publicationDate?: Maybe<Scalars['DateTime']>;
  status: PolicyStatus;
  title: Scalars['String'];
};

export type PolicyConnectionPayload = ConnectionPayload & {
  __typename?: 'PolicyConnectionPayload';
  items: Array<Policy>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type PolicyInput = {
  id: Scalars['UUID'];
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
  assetTypeCategories: AssetTypeCategoryConnectionPayload;
  assetTypes: AssetTypeConnectionPayload;
  assets: AssetConnectionPayload;
  candidate: CandidatePayLoad;
  candidates: CandidateConnectionPayload;
  employees: EmployeeConnectionPayload;
  leaveRequest: LeaveRequestPayload;
  leaveRequests: LeaveRequestConnectionPayload;
  leaveTypes: LeaveTypeConnectionPayload;
  location: LocationPayload;
  locationCountries: LocationCountryConnectionPayload;
  locations: LocationConnectionPayload;
  me: EmployeePayload;
  objective: ObjectivePayload;
  objectives: ObjectiveConnectionPayload;
  playbook: PlaybookPayload;
  playbooks: PlaybookConnectionPayload;
  policies: PolicyConnectionPayload;
  policy: PolicyPayload;
  resource: ResourcePayload;
  survey: SurveyPayload;
  surveys: SurveyConnectionPayload;
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


export type QueryAssetTypeCategoriesArgs = {
  input: AssetTypeCategoriesInput;
};


export type QueryAssetTypesArgs = {
  input: AssetTypesInput;
};


export type QueryAssetsArgs = {
  input: AssetsInput;
};


export type QueryCandidateArgs = {
  input: CandidateInput;
};


export type QueryCandidatesArgs = {
  input: CandidatesInput;
};


export type QueryEmployeesArgs = {
  input?: InputMaybe<EmployeesInput>;
};


export type QueryLeaveRequestArgs = {
  input: LeaveRequestInput;
};


export type QueryLeaveRequestsArgs = {
  input: LeaveRequestsInput;
};


export type QueryLeaveTypesArgs = {
  input: LeaveTypesInput;
};


export type QueryLocationArgs = {
  input: LocationInput;
};


export type QueryLocationCountriesArgs = {
  input: LocationCountriesInput;
};


export type QueryLocationsArgs = {
  input: LocationsInput;
};


export type QueryObjectiveArgs = {
  input: ObjectiveInput;
};


export type QueryObjectivesArgs = {
  input: ObjectivesInput;
};


export type QueryPlaybookArgs = {
  input: PlaybookInput;
};


export type QueryPlaybooksArgs = {
  input: PlaybooksInput;
};


export type QueryPoliciesArgs = {
  input: PoliciesInput;
};


export type QueryPolicyArgs = {
  input: PolicyInput;
};


export type QueryResourceArgs = {
  input: ResourceInput;
};


export type QuerySurveyArgs = {
  input: SurveyInput;
};


export type QuerySurveysArgs = {
  input: SurveysInput;
};


export type QueryVacanciesArgs = {
  input: VacanciesInput;
};


export type QueryVacancyArgs = {
  input: VacancyInput;
};

export type Question = Node & {
  __typename?: 'Question';
  body: Scalars['String'];
  id: Scalars['UUID'];
  options?: Maybe<OptionConnectionPayload>;
  type: QuestionType;
};

export type QuestionConnectionPayload = ConnectionPayload & {
  __typename?: 'QuestionConnectionPayload';
  items: Array<Question>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type QuestionOption = Node & {
  __typename?: 'QuestionOption';
  id: Scalars['UUID'];
  text: Scalars['String'];
};

export enum QuestionType {
  Multiple = 'MULTIPLE',
  Order = 'ORDER',
  Single = 'SINGLE',
  Text = 'TEXT'
}

export type Resource = {
  __typename?: 'Resource';
  id: Scalars['UUID'];
  mimeType: Scalars['String'];
  thumbnail?: Maybe<Scalars['String']>;
  url: Scalars['String'];
};

export type ResourceInput = {
  id: Scalars['UUID'];
};

export type ResourcePayload = {
  __typename?: 'ResourcePayload';
  resource?: Maybe<Resource>;
};

export type StrictDatePeriod = {
  endDate: Scalars['DateTime'];
  startDate: Scalars['DateTime'];
};

export type Survey = Node & {
  __typename?: 'Survey';
  description: Scalars['String'];
  id: Scalars['UUID'];
  questions?: Maybe<QuestionConnectionPayload>;
  title: Scalars['String'];
};

export type SurveyConnectionPayload = ConnectionPayload & {
  __typename?: 'SurveyConnectionPayload';
  items: Array<Survey>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type SurveyInput = {
  id: Scalars['UUID'];
};

export type SurveyPayload = {
  __typename?: 'SurveyPayload';
  survey: Survey;
};

export type SurveyWhereInput = {
  title?: InputMaybe<Scalars['String']>;
};

export type SurveysInput = {
  pagination?: InputMaybe<PageInput>;
  where?: InputMaybe<SurveyWhereInput>;
};

export type UpdateAnnouncementInput = {
  body: Scalars['String'];
  id: Scalars['UUID'];
};

export type UpdateAssetInput = {
  actionOnName?: InputMaybe<Scalars['String']>;
  archivedDate?: InputMaybe<Scalars['DateTime']>;
  assignee?: InputMaybe<NodeInput>;
  comment?: InputMaybe<Scalars['String']>;
  id: Scalars['UUID'];
  location?: InputMaybe<NodeInput>;
  nextActionDate?: InputMaybe<Scalars['DateTime']>;
  serialNumber: Scalars['String'];
  tagNumber?: InputMaybe<Scalars['String']>;
  title: Scalars['String'];
  type: NodeInput;
  waybillDate: Scalars['DateTime'];
};

export type UpdateAssetTypeInput = {
  category?: InputMaybe<NodeInput>;
  iconName: Scalars['String'];
  id: Scalars['UUID'];
  name: Scalars['String'];
};

export type UpdateEmployeeInput = {
  birthday: Scalars['DateTime'];
  firstName: Scalars['String'];
  id: Scalars['UUID'];
  lastName: Scalars['String'];
  location?: InputMaybe<Scalars['UUID']>;
  position?: InputMaybe<Scalars['UUID']>;
  reportingManager?: InputMaybe<Scalars['UUID']>;
  role: EmployeeRole;
};

export type UpdateEmployeePositionInput = {
  id: Scalars['UUID'];
  name: Scalars['String'];
};

export type UpdateLocationInput = {
  country: NodeInput;
  id: Scalars['UUID'];
  name: Scalars['String'];
};

export type UpdateObjectiveInput = {
  comments?: InputMaybe<Scalars['String']>;
  description?: InputMaybe<Scalars['String']>;
  dueToDate: Scalars['DateTime'];
  employee: Scalars['UUID'];
  id: Scalars['UUID'];
  name: Scalars['String'];
  status?: InputMaybe<ObjectiveStatus>;
};

export type UpdateVacancyInput = {
  assignTo?: InputMaybe<Scalars['UUID']>;
  description: Scalars['String'];
  id: Scalars['UUID'];
  position: Scalars['UUID'];
  priority: VacancyPriority;
  status: VacancyStatus;
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
  id: Scalars['UUID'];
  position: EmployeePosition;
  priority: VacancyPriority;
  status: VacancyStatus;
};

export type VacancyCandidatesWhereInput = {
  status?: InputMaybe<Array<CandidateStatus>>;
  vacancy: NodeInput;
};

export type VacancyConnectionPayload = ConnectionPayload & {
  __typename?: 'VacancyConnectionPayload';
  items: Array<Vacancy>;
  pageInfo: PageInfo;
  totalItems: Scalars['Int'];
};

export type VacancyInput = {
  id: Scalars['UUID'];
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
