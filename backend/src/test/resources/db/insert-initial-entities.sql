insert into location(id, uuid, name)
values
(1, 'b7f46256-e21d-483b-be29-8bf7617bc3c3', 'london'),
(2, '9c6369ec-fd74-449f-8b68-83cae2df1aac', 'dubai');

INSERT INTO employee(id, uuid, first_name, last_name, role, birthday)
VALUES
(1, '48b560ab-7450-4088-b6bb-f57638ea0877', 'Jacob', 'Morris', 'ADMIN', '2022-03-23T19:37:14.480425Z'),
(2, '5bea1a4d-b457-47c9-bf8b-72f50182b707', 'Edmund', 'Hogan', 'EMPLOYEE', '2022-02-28T12:03:00.480425Z'),
(3, '6bac1755-c88c-4462-ae14-527b54b03e0d', 'Geneva', 'Norman', 'EMPLOYEE', '2022-01-16T08:12:50.480425Z'),
(4, 'b48db556-3bd0-479e-9f1a-231be9636887', 'Danielle', 'Kelly', 'ADMIN', '2022-02-08T18:34:45.480425Z'),
(5, '78098941-0591-4b9c-998f-95a65e1ece55', 'Victoria', 'Luna', 'EMPLOYEE', '2022-03-15T22:11:15.480425Z');

INSERT INTO asset_type (id, uuid, title, icon)
VALUES
(1, '5478b586-e607-4448-ac05-3e5f2adbbc1b', 'Laptops', 'https://google.com/laptops'),
(2, 'b7c34a7d-eeb8-4491-b2c8-0e79d1367b6b', 'Monitors', 'https://google.com/monitors'),
(3, '773e44fe-ca9e-4e2b-872b-3a9dacb7ab57', 'Headphones', 'https://google.com/headphones');

INSERT INTO asset (id, uuid, title, serial_number, asset_type_id, employee_id, archived_date, purchased_date)
VALUES
(1, 'e838b736-721a-4cf8-80f3-ebcb6da01a36', 'Macbook Pro 13', 'Е673РВ', 1, 1, '2022-03-23T19:37:14.480425Z', '2022-03-23T19:37:14.480425Z'),
(2, '37464c92-80fa-4946-b41f-27928e6ec4d3', 'Dell Latitude 15', '343234323', 1, 3, null, '2022-02-23T12:32:45.480425Z'),
(3, '3c5201a9-cf34-4fd2-95d8-0d8b2e534463', 'Lenovo Laptop 14', 'EJS32SDGSD', 1, 4, null, '2022-01-15T14:17:26.480425Z'),
(4, '224c2d4d-05fb-449e-8dca-309fc390fbe5', 'Dell Latitude 13', 'SD832HGJTR', 1, 1, null, '2022-03-04T13:34:42.480425Z'),
(5, '759e9771-bdfa-4156-b5d8-71a5ecf22d2f', 'Dell Monitor 24', '56327382', 2, 3, '2022-01-12T19:37:14.480425Z', '2022-03-07T19:51:11.480425Z'),
(6, '0f3f7f9a-4a9c-45b5-b43d-59cad5a49725', 'KOSS Porta Pro', '737383389', 3, 4, null, '2022-02-13T17:34:28.480425Z'),
(7, 'edcfd952-4f1b-4f78-b4ac-f5705b703f33', 'Meze Neo', 'XJSK67HJ897', 3, 5, null, '2022-03-23T13:27:14.480425Z'),
(8, 'b1b6aaeb-252b-4324-9166-10c42635572d', 'DUNU Titan 1', '83837363', 3, 3, '2022-02-02T13:25:34.480425Z', '2022-01-27T08:12:24.480425Z');

