
INSERT INTO employee(id, uuid, first_name, last_name, role, birthday)
VALUES
(1, '48b560ab-7450-4088-b6bb-f57638ea0877', 'Jacob', 'Morris', 'ADMIN', '2022-03-23T19:37:14.480425Z'),
(2, '5bea1a4d-b457-47c9-bf8b-72f50182b707', 'Edmund', 'Hogan', 'EMPLOYEE', '2022-02-28T12:03:00.480425Z'),
(3, '493a5be9-01ba-47c6-95c1-29c230528520', 'John', 'Dow', 'EMPLOYEE', '2002-02-28T11:20:00Z');

INSERT INTO feed_item (id, uuid, created_at)
VALUES
(1, 'be0c9210-16d1-4ae8-918a-b3c951243c4c', '2022-05-30 15:00:23.296281+00'),
(2, '32e22341-040f-4b5f-9b1a-4dc2c59813f6', '2022-05-31 15:10:23.296281+00'),
(3, '58fedc11-c0f3-4ccb-81f8-12339fa6bc03', '2022-05-31 15:15:34.364033+00'),
(4, 'facff19e-f6a8-4377-842f-81114b8a243d', '2022-05-31 15:25:13.992505+00');

INSERT INTO post (id, text, author_id)
VALUES
(1, 'post 1', 1),
(2, 'post 2', 2),
(3, 'post 3', 1),
(4, 'post 4', 1);

INSERT INTO resource(id, uuid, outer_reference, thumbnail_id, mime_type)
VALUES
(1, 'b5673d40-ac1f-4092-8361-80bcdc182a07', 'ghjklalskdj', null, 'image/jpg'),
(2, '024ed048-68e9-47ca-99b8-3ee81dc70245', 'aasdfghgfsd', null, 'image/jpg'),
(3, 'e72e2eff-def2-479c-a827-bc5e59d694b4', 'resource_outer_reference_1', 3, 'image/jpg');

INSERT INTO post_to_resource(post_id, resource_id)
VALUES
(1, 1),
(1, 2),
(2, 3);


ALTER SEQUENCE employee_id_seq RESTART WITH 1000;
ALTER SEQUENCE feed_item_id_seq RESTART WITH 1000;
ALTER SEQUENCE resource_id_seq RESTART WITH 1000;
