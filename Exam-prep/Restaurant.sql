CREATE TABLE products(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) NOT NULL UNIQUE,
type VARCHAR(30) NOT NULL,
price DECIMAL(10,2) NOT NULL
);

CREATE TABLE clients(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
birthdate DATE NOT NULL,
card VARCHAR(50),
review TEXT
);

CREATE TABLE tables(
id INT PRIMARY KEY AUTO_INCREMENT,
floor INT NOT NULL,
reserved TINYINT(1),
capacity INT NOT NULL
);

CREATE TABLE waiters(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
phone VARCHAR(50),
salary DECIMAL(10,2)
);

CREATE TABLE orders(
id INT PRIMARY KEY AUTO_INCREMENT,
table_id INT NOT NULL,
waiter_id INT NOT NULL,
order_time TIME NOT NULL,
payed_status TINYINT(1),
CONSTRAINT fk_orders_tables
FOREIGN KEY (table_id)
REFERENCES tables(id),
CONSTRAINT fk_orders_waiters
FOREIGN KEY (waiter_id)
REFERENCES waiters(id)
);


CREATE TABLE orders_clients(
order_id INT,
client_id INT,
CONSTRAINT fk_order_clients_orders
FOREIGN KEY (order_id)
REFERENCES orders(id),
CONSTRAINT fk_order_clients_clients
FOREIGN KEY (client_id)
REFERENCES clients(id)
);

CREATE TABLE orders_products(
order_id INT,
product_id INT,
CONSTRAINT fk_orders_products_orders
FOREIGN KEY (order_id)
REFERENCES orders(id),
CONSTRAINT fk_orders_products_products
FOREIGN KEY (product_id)
REFERENCES products(id)
);


INSERT INTO products(name, type, price)
SELECT 
CONCAT(w.last_name, ' ', 'specialty'),
'Cocktail',
ceiling(w.salary * 0.01)
FROM waiters as w
WHERE w.id > 6;

UPDATE orders 
SET table_id = table_id - 1
WHERE id BETWEEN 12 AND 23;

DELETE FROM waiters
WHERE id NOT IN (SELECT waiter_id FROM orders);


SELECT * FROM clients
ORDER BY birthdate DESC, id DESC;

SELECT
first_name,
last_name,
birthdate,
review
FROM clients
WHERE card IS NULL AND YEAR(birthdate) BETWEEN 1978 AND 1993
ORDER BY last_name DESC, id
LIMIT 5;


SELECT 
CONCAT(last_name, first_name, char_length(first_name), 'Restaurant') AS username,
REVERSE(substring(email, 2, 12)) AS password
FROM waiters
WHERE salary IS NOT NULL
ORDER BY password DESC;


SELECT
p.id,
p.name,
COUNT(p.id) as count
FROM products as p
JOIN orders_products as op ON op.product_id = p.id
JOIN orders as o ON o.id = op.order_id
GROUP BY p.id
HAVING COUNT(*) >= 5
ORDER BY count DESC, p.name;


SELECT 
t.id as table_id,
t.capacity,
COUNT(c.id) as count_clients,
(CASE
WHEN capacity > COUNT(c.id) THEN 'Free seats'
WHEN capacity = COUNT(c.id) THEN 'Full'
ELSE 'Extra seats'
END) as availability
FROM tables as t
RIGHT JOIN orders as o ON t.id = o.table_id
RIGHT JOIN orders_clients as oc ON o.id = oc.order_id
RIGHT JOIN clients as c ON oc.client_id = c.id
WHERE t.floor = 1
GROUP BY t.id
ORDER BY t.id DESC;

DELIMITER $$
CREATE FUNCTION udf_client_bill(full_name VARCHAR(50))
RETURNS DECIMAL(19,2)
DETERMINISTIC
BEGIN
DECLARE total_sum DECIMAL(19,2);
SET total_sum := (SELECT 
SUM(p.price)
FROM
clients as c
JOIN orders_clients as oc ON c.id = oc.client_id
JOIN orders as o ON oc.order_id = o.id
JOIN orders_products as op ON o.id = op.order_id
JOIN products as p ON op.product_id = p.id
WHERE concat(c.first_name, ' ', c.last_name) = full_name
);
RETURN total_sum;
END$$

DELIMITER $$
CREATE PROCEDURE udp_happy_hour(type VARCHAR(50))
BEGIN

UPDATE products as p
SET p.price = p.price * 0.8
WHERE p.price >= 10 AND p.type = type;

END$$




