INSERT INTO employee(id, uuid, first_name, last_name, role, birthday)
VALUES
(1, '48b560ab-7450-4088-b6bb-f57638ea0877', 'Jacob', 'Morris', 'ADMIN', '2022-03-23T19:37:14.480425Z');

INSERT INTO leave_type(id, uuid, name, days, end_of_year_action) 
VALUES (1, 'b8931b95-ed2b-4f66-a211-776007dc56c1', 'SICK_LEAVE', 10, 'CARRY_OVER' );

INSERT INTO leave_request (id, uuid, employee_id, name, request_date, status, start_date, end_date, leave_type_id, number_of_days, comment)
VALUES
(1, '493a5be9-01ba-47c6-95c1-29c230528527', '1', 'Leave request 1', now(), 'NEW', now(), now() + INTERVAL '1 DAYS', 1, 1, 'this is comment 1'),
(2, 'd7a9e9c8-fa56-47ce-b036-678e6f7f2399', '1', 'Leave request 2', now(), 'APPROVED', now() - INTERVAL '1 DAYS', now(), 1, 1, 'this is comment 2'),
(3, 'c823dbfb-b1c6-4c77-b22e-fc06f2737b2b', '1', 'Leave request 3', now(), 'DECLINED', now() + INTERVAL '1 DAYS', now() + INTERVAL '2 DAYS', 1, 1, 'this is comment 3'),
(4, '3c0e4949-d259-4e1d-86a5-e6025308346f', '1', 'Leave request 4', now(), 'NEW', now() + INTERVAL '2 DAYS', now() + INTERVAL '3 DAYS', 1, 1, 'this is comment 4');

