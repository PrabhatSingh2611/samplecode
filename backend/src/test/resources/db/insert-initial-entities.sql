ALTER SEQUENCE location_id_seq RESTART WITH 1000;
ALTER SEQUENCE location_country_id_seq RESTART WITH 1000;
ALTER SEQUENCE asset_id_seq RESTART WITH 1000;

INSERT INTO location_country (id, uuid, name, icon_name)
VALUES
    (1, '3f7b6d39-ffbc-4e9a-8e15-075aff4c8f17', 'Portugal', 'portugal_flag'),
    (2, '0164033e-2381-4d80-aa30-363532352e7d', 'Spain', 'spain_flag'),
    (3, 'b86cf217-9049-4a15-bef6-4d6849176da4', 'Ukraine', 'ukraine_flag');

INSERT INTO location(id, uuid, name, country_id)
VALUES
(1, 'cabfb51b-41c5-4f4d-afd5-146c796391af', 'Lisbon', 1),
(2, '79761728-8f51-475f-aa04-42385a0dfe36', 'Madrid', 2),
(3, 'c6faa656-6da1-450e-b176-6093e22298f6', 'Kyiv', 3);

insert into employee_position(id, uuid, name)
values
(1, 'cabfb51b-41c5-4f4d-afd5-146c796391ad', 'manager'),
(2, '79761728-8f51-475f-aa04-42385a0dfe35', 'principle');

INSERT INTO employee(id, uuid, first_name, last_name, email, role, birthday, manager_id, position_id, location_id)
VALUES
(1, '48b560ab-7450-4088-b6bb-f57638ea0877', 'Jacob', 'Morris','jacob@gmail.com', 'ADMIN', '2022-03-23T19:37:14.480425Z',2,1,1),
(2, '5bea1a4d-b457-47c9-bf8b-72f50182b707', 'Edmund', 'Hogan','edmund@gmail.com', 'EMPLOYEE', '2022-02-28T12:03:00.480425Z',1,1,1),
(3, '6bac1755-c88c-4462-ae14-527b54b03e0d', 'Geneva', 'Norman','geneva@gmail.com', 'EMPLOYEE', '2022-01-16T08:12:50.480425Z',1,1,1),
(4, 'b48db556-3bd0-479e-9f1a-231be9636887', 'Danielle', 'Kelly','danielle@gmail.com', 'ADMIN', '2022-02-08T18:34:45.480425Z',2,1,1),
(5, '78098941-0591-4b9c-998f-95a65e1ece55', 'Victoria', 'Luna','victoria@gmail.com', 'EMPLOYEE', '2022-03-15T22:11:15.480425Z',2,1,1),
(6, '493a5be9-01ba-47c6-95c1-29c230528520', 'Tom', 'Johns','tom.johns@gmail.com', 'ADMIN', '2022-05-26T17:26:17.186989Z', 1, 1, 2);

ALTER SEQUENCE employee_id_seq RESTART WITH 1000;

INSERT INTO asset_type_category (id, uuid, name)
VALUES
    (1, 'f2b267a2-3336-4cdf-97cd-295207054497', 'Devices'),
    (2, '30502d59-30b3-42e1-9f6b-11670c7b2083', 'Accessories'),
    (3, '5ae9cce4-b25f-447f-8e6f-a534c9a07934', 'Licenses');

INSERT INTO asset_type (id, uuid, name, icon_name, created_at, asset_type_category_id)
VALUES
(1, '5478b586-e607-4448-ac05-3e5f2adbbc1b', 'Laptops', 'laptops_icon','2021-11-21T18:14:25.480425Z',1),
(2, 'b7c34a7d-eeb8-4491-b2c8-0e79d1367b6b', 'Monitors', 'monitors_icon','2022-02-14T21:37:05.480425Z',1),
(3, '773e44fe-ca9e-4e2b-872b-3a9dacb7ab57', 'Headphones', 'headphones_icon','2022-05-11T13:18:14.480425Z',2),
(4, '9bec3da6-13d5-408a-8ecf-b0fae84f4ba3', 'OS', 'os_icon','2022-05-11T13:18:14.480425Z',3),
(5, 'e67a9709-0ae7-4da8-aa7c-6731cb52921d', 'Application', 'app_icon','2022-05-11T13:18:14.480425Z',3);

ALTER SEQUENCE asset_type_id_seq RESTART WITH 1000;
ALTER SEQUENCE asset_type_category_id_seq RESTART WITH 1000;


INSERT INTO asset (id, uuid, title, serial_number, asset_type_id, employee_id, archived_date, purchased_date, location_id)
VALUES
(1, 'e838b736-721a-4cf8-80f3-ebcb6da01a36', 'Macbook Pro 13', '??673????', 1, 1, '2022-03-23T19:37:14.480425Z', '2022-03-23T19:37:14.480425Z', 1),
(2, '37464c92-80fa-4946-b41f-27928e6ec4d3', 'Dell Latitude 15', '343234323', 1, 3, null, '2022-02-23T12:32:45.480425Z', 1),
(3, '3c5201a9-cf34-4fd2-95d8-0d8b2e534463', 'Lenovo Laptop 14', 'EJS32SDGSD', 1, 4, null, '2022-01-15T14:17:26.480425Z', 2),
(4, '224c2d4d-05fb-449e-8dca-309fc390fbe5', 'Dell Latitude 13', 'SD832HGJTR', 1, 1, null, '2022-03-04T13:34:42.480425Z', 2),
(5, '759e9771-bdfa-4156-b5d8-71a5ecf22d2f', 'Dell Monitor 24', '56327382', 2, 3, '2022-01-12T19:37:14.480425Z', '2022-03-07T19:51:11.480425Z', 1),
(6, '0f3f7f9a-4a9c-45b5-b43d-59cad5a49725', 'KOSS Porta Pro', '737383389', 3, 4, null, '2022-02-13T17:34:28.480425Z', 1),
(7, 'edcfd952-4f1b-4f78-b4ac-f5705b703f33', 'Meze Neo', 'XJSK67HJ897', 3, 5, null, '2022-03-23T13:27:14.480425Z', 2),
(8, 'b1b6aaeb-252b-4324-9166-10c42635572d', 'DUNU Titan 1', '83837363', 3, 3, '2022-02-02T13:25:34.480425Z', '2022-01-27T08:12:24.480425Z', 1),
(9, '40c73e5b-a93c-43ac-b575-5add9ea03df7', 'Panasonic', '238320203HJN', 3, null, null, '2022-01-17T09:15:24.480425Z', 1);


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
(1, '29e98fa1-778f-4922-980b-89e287c4e180', 1, 'Robert', 'Wilson', 'RobertW', 'b5673d40-ac1f-4092-8361-80bcdc182a07', 'NEW'),
(2, 'c5c381eb-5b4c-4d01-980c-31b842367e16', 1, 'William', 'Henry', 'WilliamH', '024ed048-68e9-47ca-99b8-3ee81dc70245', 'OFFER_SENT'),
(3, '78edf7f1-a660-4ce1-978f-8d394cdfa068', 1, 'Joe', 'Hang', 'JHang', 'a9aa8317-c5a9-421f-914e-26d0b473cd98', 'REJECTED'),
(4, 'ea93f158-d5ed-452d-ad75-08a683c5c834', 2, 'Duke', 'Milne', 'DMilne', 'ea42a183-c8f5-43d2-bae9-968346481bc7', 'PASS'),
(5, 'fd0afd22-e70a-4b11-8314-d86023b083aa', 2, 'Ellise', 'Tillman', 'ETillman', 'd12476da-0e83-4393-9075-1aff91640790', 'OFFER_SENT');

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

INSERT INTO leave_type(id, uuid, name, days, end_of_year_action)
VALUES
(1, 'b8931b95-ed2b-4f66-a211-776007dc56c1', 'SICK_LEAVE', 10, 'CARRY_OVER' ),
(2, 'd94911c5-fe2b-4b32-86de-a68feb39a08b', 'REGULAR', 5, 'CARRY_OVER' );


INSERT INTO leave_request (id, uuid, employee_id, request_date, status, start_date, end_date, leave_type_id, comment)
 VALUES
(1, '493a5be9-01ba-47c6-95c1-29c230528527', 1,'2022-02-02T13:25:34.480425Z', 'NEW', '2022-02-02T13:25:34.480425Z', '2022-02-02T13:25:34.480425Z', 1, 'this is comment'),
(2, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2399', 2,'2022-02-02T13:25:34.480425Z', 'APPROVED', '2022-02-02T13:25:34.480425Z', '2022-02-02T13:25:34.480425Z', 1, 'this is comment 2');


insert into playbook (id, uuid, name, description, status, image_id, created_at, updated_at)
            values (1, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2400', 'Playbook 1', 'Playbook description 1', 'DRAFT', 1, '2022-02-02T13:25:34.480425Z','2022-02-02T13:25:34.480425Z'),
                   (2, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2401', 'Playbook 2', 'Playbook description 2', 'PUBLISHED', 1, '2021-02-02T13:25:34.480425Z','2021-02-02T13:25:34.480425Z');

insert into playbook_section (id, uuid, name, sort, playbook_id)
values (1, 'd0228476-aa22-4f2a-95ab-ee7bd0178330', 'Section 1 Playbook 2', 1, 2),
       (2, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2405', 'Section 1 Playbook 1', 1, 1),
       (3, '91b240f7-087f-4d78-9907-66a1e4f14342', 'Section 2 Playbook 1', 2, 1),
       (4, 'e88c0f10-a84b-4bfa-a8d4-bfe924874224', 'Section 3 Playbook 1', 3, 1),
       (5, '2d71d9b3-874d-416c-a874-9275886d0a19', 'Section 4 Playbook 1', 4, 1);

insert into playbook_section_topic (id, uuid, name, body, sort, playbook_section_id)
            values (1, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2406', 'Playbook section topic 1', 'This is body test 1', 1, 1),
                   (2, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2407', 'Playbook section topic 2', 'This is body test 2', 2, 2),
                   (3, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2408', 'Playbook section topic 3', 'This is body test 3', 3, 2),
                   (4, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2409', 'Playbook section topic 4', 'This is body test 4', 4, 1),
                   (5, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2410', 'Playbook section topic 5', 'This is body test 5', 5, 1);


ALTER SEQUENCE resource_id_seq RESTART WITH 1000;
ALTER SEQUENCE survey_id_seq RESTART WITH 1000;
ALTER SEQUENCE question_id_seq RESTART WITH 1000;
ALTER SEQUENCE option_id_seq RESTART WITH 1000;
ALTER SEQUENCE leave_request_id_seq RESTART WITH 1000;
ALTER SEQUENCE playbook_id_seq RESTART WITH 1000;
ALTER SEQUENCE playbook_section_id_seq RESTART WITH 1000;
ALTER SEQUENCE playbook_section_topic_id_seq RESTART WITH 1000;
