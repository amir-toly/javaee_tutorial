CREATE DATABASE sdzee DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'java'@'localhost' IDENTIFIED BY 'SdZ_eE';
GRANT ALL ON sdzee.* TO 'java'@'localhost' IDENTIFIED BY 'SdZ_eE';

CREATE TABLE sdzee.t_customer (
	id INT(11) NOT NULL AUTO_INCREMENT,
	last_name VARCHAR(20) NOT NULL,
	first_name VARCHAR(20),
	address VARCHAR(100) NOT NULL,
	phone_number VARCHAR(10) NOT NULL,
	email VARCHAR(60),
	picture_name VARCHAR(20),
	PRIMARY KEY (id),
	UNIQUE (email)
) ENGINE = INNODB;

CREATE TABLE sdzee.t_order (
	id INT(11) NOT NULL AUTO_INCREMENT,
	customer_id INT(11) NOT NULL,
	order_date DATETIME NOT NULL,
	amount DOUBLE NOT NULL,
	payment_method VARCHAR(20) NOT NULL,
	payment_status VARCHAR(20),
	shipping_mode VARCHAR(20) NOT NULL,
	delivery_status VARCHAR(20),
	PRIMARY KEY (id),
	CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES sdzee.t_customer(id)
) ENGINE = INNODB;
