insert into location(id, uuid, country, flag_icon)
values
(1, 'b7f46256-e21d-483b-be29-8bf7617bc3c3', 'england', 'https://google.com/england_flag'),
(2, '9c6369ec-fd74-449f-8b68-83cae2df1aac', 'UAE', 'https://google.com/uae_flag');

insert into employee_position(id, uuid, name)
values
(1, 'cabfb51b-41c5-4f4d-afd5-146c796391ad', 'manager'),
(2, '79761728-8f51-475f-aa04-42385a0dfe35', 'principle');

INSERT INTO employee(id, uuid, first_name, last_name, role, birthday, manager_id, position_id, location_id)
VALUES
(1, '48b560ab-7450-4088-b6bb-f57638ea0877', 'Jacob', 'Morris', 'ADMIN', '2022-03-23T19:37:14.480425Z',2,1,1),
(2, '5bea1a4d-b457-47c9-bf8b-72f50182b707', 'Edmund', 'Hogan', 'EMPLOYEE', '2022-02-28T12:03:00.480425Z',1,1,1),
(3, '6bac1755-c88c-4462-ae14-527b54b03e0d', 'Geneva', 'Norman', 'EMPLOYEE', '2022-01-16T08:12:50.480425Z',1,1,1),
(4, 'b48db556-3bd0-479e-9f1a-231be9636887', 'Danielle', 'Kelly', 'ADMIN', '2022-02-08T18:34:45.480425Z',2,1,1),
(5, '78098941-0591-4b9c-998f-95a65e1ece55', 'Victoria', 'Luna', 'EMPLOYEE', '2022-03-15T22:11:15.480425Z',2,1,1);

INSERT INTO asset_type (id, uuid, title, icon)
VALUES
(1, '5478b586-e607-4448-ac05-3e5f2adbbc1b', 'Laptops', 'https://google.com/laptops'),
(2, 'b7c34a7d-eeb8-4491-b2c8-0e79d1367b6b', 'Monitors', 'https://google.com/monitors'),
(3, '773e44fe-ca9e-4e2b-872b-3a9dacb7ab57', 'Headphones', 'https://google.com/headphones');

INSERT INTO asset (id, uuid, title, serial_number, asset_type_id, employee_id, archived_date, purchased_date, location_id)
VALUES
(1, 'e838b736-721a-4cf8-80f3-ebcb6da01a36', 'Macbook Pro 13', 'Е673РВ', 1, 1, '2022-03-23T19:37:14.480425Z', '2022-03-23T19:37:14.480425Z', 1),
(2, '37464c92-80fa-4946-b41f-27928e6ec4d3', 'Dell Latitude 15', '343234323', 1, 3, null, '2022-02-23T12:32:45.480425Z', 1),
(3, '3c5201a9-cf34-4fd2-95d8-0d8b2e534463', 'Lenovo Laptop 14', 'EJS32SDGSD', 1, 4, null, '2022-01-15T14:17:26.480425Z', 2),
(4, '224c2d4d-05fb-449e-8dca-309fc390fbe5', 'Dell Latitude 13', 'SD832HGJTR', 1, 1, null, '2022-03-04T13:34:42.480425Z', 2),
(5, '759e9771-bdfa-4156-b5d8-71a5ecf22d2f', 'Dell Monitor 24', '56327382', 2, 3, '2022-01-12T19:37:14.480425Z', '2022-03-07T19:51:11.480425Z', 1),
(6, '0f3f7f9a-4a9c-45b5-b43d-59cad5a49725', 'KOSS Porta Pro', '737383389', 3, 4, null, '2022-02-13T17:34:28.480425Z', 1),
(7, 'edcfd952-4f1b-4f78-b4ac-f5705b703f33', 'Meze Neo', 'XJSK67HJ897', 3, 5, null, '2022-03-23T13:27:14.480425Z', 2),
(8, 'b1b6aaeb-252b-4324-9166-10c42635572d', 'DUNU Titan 1', '83837363', 3, 3, '2022-02-02T13:25:34.480425Z', '2022-01-27T08:12:24.480425Z', 1);


INSERT INTO vacancy (id, uuid, position_id, description, status, employee_id, priority, created_at, updated_at)
VALUES
(1, '493a5be9-01ba-47c6-95c1-29c230528525', '1', 'Vacancy 1', 'NEW', 1, 'LOW', '2022-02-02T13:25:34.480425Z', '2022-02-02T13:25:34.480425Z'),
(2, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2398', '2', 'Vacancy 2', 'IN_PROGRESS', 2, 'NORMAL',  '2022-02-02T13:25:34.480425Z', '2022-02-02T13:25:34.480425Z');

INSERT INTO objective (id, uuid, employee_id, name, description, comments, due_to_date, status)
VALUES
(1, 'e94f4d6a-a6cf-4786-a0b8-f096290057e3', 1, 'Objective 1', 'Description of Objective 1', 'Comment of Objective 1', '2022-02-02T13:25:34.480425Z', 'NEW'),
(2, '395e206f-fb3e-4364-92bf-0f5778e739a4', 2, 'Objective 2', 'Description of Objective 2', 'Comment of Objective 2', '2022-02-02T13:25:34.480425Z', 'DONE'),
(3, '2fa56def-ce16-493c-8f8c-173bf3ee8309', 1, 'Objective 3', 'Description of Objective 3', 'Comment of Objective 3', '2022-02-02T13:25:34.480425Z', 'DONE');

insert into announcement (id, uuid, body, created_at)
            values (1, 'f0ebfc41-acfb-4049-9aef-ea8ab8057c88', 'this is sample 1 announcement body', '2022-02-02T13:25:34.480425Z'),
                   (2, 'd3e573f6-9b06-46ab-b3ff-ebd7caefb890', 'this is sample 2 announcement body', '2021-02-02T13:25:34.480425Z');

INSERT INTO candidate(id, uuid, vacancy_id, first_name, last_name, linkedin, attachment_uuid, status)
VALUES
(1, '29e98fa1-778f-4922-980b-89e287c4e180' ,1,'Robert', 'Wilson','RobertW', 'b5673d40-ac1f-4092-8361-80bcdc182a07', 'NEW'),
(2, 'c5c381eb-5b4c-4d01-980c-31b842367e16' ,1,'William', 'Henry','WilliamH', '024ed048-68e9-47ca-99b8-3ee81dc70245', 'OFFER_SENT');

ALTER SEQUENCE candidate_id_seq RESTART WITH 1000;

INSERT INTO resource(id, uuid, outer_reference, thumbnail_id, mime_type)
VALUES
(1, 'b5673d40-ac1f-4092-8361-80bcdc182a07', 'ghjklalskdj', null, 'application/pdf'),
(2, '024ed048-68e9-47ca-99b8-3ee81dc70245', 'aasdfghgfsd', null, 'image/jpg'),
(3, 'e72e2eff-def2-479c-a827-bc5e59d694b4', 'resource_outer_reference_1', 2, 'application/txt');

INSERT INTO candidate_to_resources(candidate_id, resource_id)
VALUES
(1, 1),
(2,2);

ALTER SEQUENCE resource_id_seq RESTART WITH 1000;

insert into policy (id, uuid, title, resource_id, publication_date, status, employee_id)
values
(1001, '239e8741-f04d-406a-bf7d-e2feaf8f5619', 'policy_title_1', 1, '2022-02-02T13:25:34.480425Z', 'PUBLISHED', 2),
(1002, 'c9cfa69a-b21d-42e9-a75f-78993d6cb509', 'policy_title_2', 1, '2022-02-02T13:25:34.480425Z', 'PUBLISHED', 1);

insert into survey (id, uuid, title_i18n, description_i18n, created_at, updated_at)
            values (1, 'f0ebfc41-acfb-4049-9aef-ea8ab8057c89', '{"en": "Survey 1"}','{"en": "this is sample 1 description"}', '2022-02-02T13:25:34.480425Z','2022-02-02T13:25:34.480425Z'),
                   (2, 'd3e573f6-9b06-46ab-b3ff-ebd7caefb891', '{"en": "Survey 2"}','{"en": "this is sample 2 description"}', '2021-02-02T13:25:34.480425Z','2021-02-02T13:25:34.480425Z');

insert into question (id, uuid, body_i18n, type,survey_id)
            values (1, 'f0ebfc41-acfb-4049-9aef-ea8ab8057c92', '{"en": "this is sample 1 question body"}','SINGLE',1),
                   (2, 'd3e573f6-9b06-46ab-b3ff-ebd7caefb894', '{"en": "this is sample 2 question body"}','SINGLE',2);

insert into option (id, uuid, text_i18n, question_id)
            values (1, 'f0ebfc41-acfb-4049-9aef-ea8ab8057c95', '{"en": "this is sample 1 option text"}',1),
                   (2, 'd3e573f6-9b06-46ab-b3ff-ebd7caefb896', '{"en": "this is sample 2 option text"}',2);

ALTER SEQUENCE survey_id_seq RESTART WITH 1000;
ALTER SEQUENCE question_id_seq RESTART WITH 1000;
ALTER SEQUENCE option_id_seq RESTART WITH 1000;

INSERT INTO leave_type(id, uuid, name, days, end_of_year_action)
VALUES
(1, 'b8931b95-ed2b-4f66-a211-776007dc56c1', 'SICK_LEAVE', 10, 'CARRY_OVER' ),
(2, 'd94911c5-fe2b-4b32-86de-a68feb39a08b', 'REGULAR', 5, 'CARRY_OVER' );


INSERT INTO leave_request (id, uuid, employee_id, request_date, status, start_date, end_date, leave_type_id, comment)
 VALUES
(1, '493a5be9-01ba-47c6-95c1-29c230528527', 1,'2022-02-02T13:25:34.480425Z', 'NEW', '2022-02-02T13:25:34.480425Z', '2022-02-02T13:25:34.480425Z', 1, 'this is comment'),
(2, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2399', 2,'2022-02-02T13:25:34.480425Z', 'APPROVED', '2022-02-02T13:25:34.480425Z', '2022-02-02T13:25:34.480425Z', 1, 'this is comment 2');


ALTER SEQUENCE leave_request_id_seq RESTART WITH 3;