CREATE SCHEMA online_store;
USE online_store;

CREATE TABLE brands(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE categories(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE reviews(
id INT PRIMARY KEY AUTO_INCREMENT,
content TEXT,
rating DECIMAL(10,2) NOT NULL,
picture_url VARCHAR(80) NOT NULL,
published_at DATETIME NOT NULL
);

CREATE TABLE products(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(40) NOT NULL,
price DECIMAL(19,2) NOT NULL,
quantity_in_stock INT,
description TEXT,
brand_id INT NOT NULL,
category_id INT NOT NULL,
review_id INT,
CONSTRAINT fk_products_brands
FOREIGN KEY (brand_id)
REFERENCES brands(id),
CONSTRAINT fk_products_categories
FOREIGN KEY (category_id)
REFERENCES categories(id),
CONSTRAINT fk_products_reviews
FOREIGN KEY (review_id)
REFERENCES reviews(id)
);


CREATE TABLE customers(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
phone VARCHAR(30) NOT NULL UNIQUE,
address VARCHAR(60) NOT NULL,
discount_card BIT(1)
);

CREATE TABLE orders(
id INT PRIMARY KEY AUTO_INCREMENT,
order_datetime DATETIME NOT NULL,
customer_id INT NOT NULL,
CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id)
REFERENCES customers(id)
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


INSERT INTO reviews(content, picture_url, published_at, rating)
SELECT 
LEFT(p.description, 15),
reverse(p.name),
'2010-10-10',
p.price / 8 
FROM products as p
WHERE p.id >= 5;

UPDATE products p
SET p.quantity_in_stock = p.quantity_in_stock - 5
WHERE p.quantity_in_stock BETWEEN 60 AND 70;

DELETE FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);


SELECT 
id,
name
FROM categories
ORDER BY name DESC;

SELECT 
id,
brand_id,
name,
quantity_in_stock
FROM 
products as p
WHERE price > 1000 and quantity_in_stock < 30
ORDER BY quantity_in_stock, id;

SELECT * FROM reviews
WHERE content LIKE 'My%' AND char_length(content) > 61
ORDER BY rating DESC;

SELECT 
CONCAT(c.first_name, ' ', c.last_name) as full_name,
c.address,
o.order_datetime
FROM customers as c
RIGHT JOIN orders as o ON o.customer_id = c.id
WHERE YEAR(o.order_datetime) <= 2018
ORDER BY full_name DESC;


SELECT
COUNT(p.id) as items_count,
c.name,
SUM(p.quantity_in_stock) as total_quantity
FROM categories as c
JOIN products as p ON c.id = p.category_id
GROUP BY c.id
ORDER BY items_count DESC, total_quantity
LIMIT 5;


DELIMITER $$
CREATE FUNCTION udf_customer_products_count(name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN(SELECT COUNT(p.id)
FROM customers c
JOIN orders o ON c.id = o.customer_id
JOIN orders_products op ON o.id = op.order_id
JOIN products p ON op.product_id = p.id
WHERE c.first_name = name);


END$$

DELIMITER $$
CREATE PROCEDURE udp_reduce_price(category_name VARCHAR(50))
BEGIN
DECLARE category_by_id INT;
SET category_by_id := (SELECT id FROM categories WHERE name = category_name);

UPDATE products p
SET p.price = p.price * 0.7
WHERE (SELECT rating FROM reviews AS r WHERE r.id = p.review_id) < 4 AND p.category_id = category_by_id;

END$$


