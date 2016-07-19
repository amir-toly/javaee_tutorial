INSERT INTO sdzee.t_customer(last_name, first_name, address, phone_number, email, picture_name) VALUES ('First', 'One', '1st Avenue', '0101010101', NULL, NULL);
INSERT INTO sdzee.t_customer(last_name, first_name, address, phone_number, email, picture_name) VALUES ('Second', NULL, '2nd Avenue', '0202020202', 'two.second@mail.com', NULL);
INSERT INTO sdzee.t_customer(last_name, first_name, address, phone_number, email, picture_name) VALUES ('Third', NULL, '3rd Avenue', '0303030303', NULL, 'delete.png');

INSERT INTO sdzee.t_order(customer_id, order_date, amount, payment_method, payment_status, shipping_mode, delivery_status) VALUES ((SELECT id FROM sdzee.t_customer WHERE last_name = 'First'), NOW(), 42.0, 'Credit Card', 'Confirmed', 'UPS', NULL);
INSERT INTO sdzee.t_order(customer_id, order_date, amount, payment_method, payment_status, shipping_mode, delivery_status) VALUES ((SELECT id FROM sdzee.t_customer WHERE last_name = 'Second'), NOW(), 1234.56, 'Check', NULL, 'FedEx', 'Delivered');
