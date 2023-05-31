# 01. Employees with Salary Above 35000
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
SELECT first_name, last_name 
FROM employees
WHERE salary > 35000
ORDER BY first_name, last_name, employee_id;
END$$

# 02. Employees with Salary Above Number
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(target_salary DECIMAL(19,4))
BEGIN
SELECT first_name, last_name 
FROM employees
WHERE salary >= target_salary
ORDER BY first_name, last_name, employee_id;
END$$

# 03. Town Names Starting With
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(prefix VARCHAR(50))
BEGIN
SELECT name AS 'town_name'
FROM towns
WHERE towns.name LIKE CONCAT(prefix, '%')
ORDER BY town_name;
END$$

# 04. Employees from Town
DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
DECLARE town_by_id INT;
SET town_by_id = (SELECT town_id FROM towns WHERE name = town_name);
SELECT first_name, last_name 
FROM employees AS e
WHERE e.address_id IN (SELECT address_id FROM addresses WHERE town_id = town_by_id)
ORDER BY first_name, last_name, employee_id;
END$$
		
# 05. Salary Level Function
DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary DECIMAL(19,4))
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
DECLARE salary_level VARCHAR(50);

IF salary < 30000 THEN SET salary_level = 'Low';
ELSEIF salary <= 50000 THEN SET salary_level = 'Average';
ELSE SET salary_level = 'High';
END IF;
RETURN salary_level;
END$$

# 06. Employees by Salary Level
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(50))
BEGIN
SELECT first_name, last_name 
FROM employees
WHERE ufn_get_salary_level(salary) = salary_level
ORDER BY first_name DESC, last_name DESC;
END$$


# 07. Define Function
DELIMITER $$
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN word REGEXP CONCAT('^[', set_of_letters, ']+$');
END$$

# 08. Find Full Name
DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
SELECT CONCAT(first_name, ' ', last_name) AS 'full_name'
FROM account_holders
ORDER BY full_name, id;
END$$

# 9. People with Balance Higher Than
DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(num DECIMAL(19,4))
BEGIN
SELECT first_name, last_name 
FROM account_holders AS ah
JOIN accounts AS a
ON a.account_holder_id = ah.id 
GROUP BY first_name, last_name
HAVING SUM(a.balance) > `num`;
END$$

# 10. Future Value Function
DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(sum DECIMAL(19,4), interest_rate DOUBLE, years_count INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN
DECLARE calc_sum DECIMAL(19,4);
SET calc_sum = sum * (pow((1 + interest_rate), years_count));
RETURN calc_sum;
END$$

# 11. Calculating Interest
DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(id INT, interest_rate DECIMAL(19,4))
BEGIN
SELECT a.id AS 'account_id',
ah.first_name,
ah.last_name,
a.balance AS 'current_balance',
ufn_calculate_future_value(a.balance, interest_rate, 5) AS 'balance_in_5_years'
FROM accounts AS a
JOIN account_holders AS ah
ON a.account_holder_id = ah.id
WHERE a.id = id;
END$$

# 12. Deposit Money
DELIMITER $$
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19,4))
BEGIN
START TRANSACTION;
IF (money_amount <= 0) THEN
ROLLBACK;
ELSE
UPDATE accounts AS a
SET balance = balance + money_amount
WHERE a.id = account_id;
COMMIT;
END IF;
END$$

# 13. Withdraw Money
DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19,4))
BEGIN
DECLARE current_balance DECIMAL(19,4);
START TRANSACTION;
SET current_balance = (SELECT balance FROM accounts WHERE id = account_id);
IF (money_amount <= 0)  THEN ROLLBACK;
ELSEIF (current_balance - money_amount) < 0 THEN ROLLBACK;

ELSE
UPDATE accounts AS a
SET balance = balance - money_amount
WHERE a.id = account_id;
COMMIT;
END IF;
END$$


# 14. Money Transfer
DELIMITER $$
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19,4))
BEGIN
DECLARE current_balance DECIMAL(19,4);
START TRANSACTION;
SET current_balance = (SELECT balance FROM accounts WHERE id = from_account_id);
IF (SELECT COUNT(*) FROM accounts WHERE id = from_account_id) <> 1 THEN ROLLBACK;
ELSEIF (SELECT COUNT(*) FROM accounts WHERE id = to_account_id) <> 1 THEN ROLLBACK;
ELSEIF amount <= 0  THEN ROLLBACK;
ELSEIF current_balance - amount < 0 THEN ROLLBACK;
ELSEIF from_account_id = to_account_id THEN ROLLBACK;
ELSE
call usp_withdraw_money(from_account_id, amount);
call usp_deposit_money(to_account_id, amount);
END IF;
END$$

# 15. Log Accounts Trigger
CREATE TABLE `logs`(
log_id INT PRIMARY KEY AUTO_INCREMENT,
account_id INT NOT NULL,
old_sum DECIMAL(19,4),
new_sum DECIMAL(19,4)
);

DELIMITER $$
CREATE TRIGGER tr_updated_balance
AFTER UPDATE
ON accounts 
FOR EACH ROW
BEGIN
INSERT INTO logs(account_id, old_sum, new_sum)
VALUES (OLD.id, OLD.balance, NEW.balance);
END$$

# 16. Emails Trigger
CREATE TABLE notification_emails(
id INT PRIMARY KEY AUTO_INCREMENT,
recipient INT NOT NULL,
 subject TEXT, 
 body TEXT);

DELIMITER $$
CREATE TRIGGER tr_notification_email
AFTER INSERT
ON logs
FOR EACH ROW
BEGIN
INSERT INTO notification_emails(recipient, subject, body)
VALUES (
NEW.account_id,
 CONCAT('Balance change for account: ', NEW.account_id),
 CONCAT('On ', DATE_FORMAT(NOW(), '%b %e %Y'), ' at ', DATE_FORMAT(NOW(), '%l:%i:%s %p'), ' your balance was changed from ', NEW.old_sum, ' to ', NEW.new_sum, '.')
 );
END$$
