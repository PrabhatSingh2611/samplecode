INSERT INTO asset_type(id, uuid, title, icon) 
VALUES (1, '5478b586-e607-4448-ac05-3e5f2adbbc1b', 'asset type 1', 'https://google.com');

INSERT INTO employee(id, uuid, first_name, last_name, role, birthday)
VALUES (1, '48b560ab-7450-4088-b6bb-f57638ea0877', 'First name', 'Last name', 'ADMIN', '2022-03-23T19:37:14.480425Z');

INSERT INTO asset (id, uuid, title, serial_number, asset_type_id, employee_id, archived_date, purchased_date)
VALUES (1, 'e838b736-721a-4cf8-80f3-ebcb6da01a36', 'asset title 1', 'serial number 1', 1, 1, '2022-03-23T19:37:14.480425Z', '2022-03-23T19:37:14.480425Z');