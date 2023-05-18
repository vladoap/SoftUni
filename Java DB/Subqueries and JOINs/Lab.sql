# 1. Managers
SELECT e.`employee_id`, CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'full_name', d.`department_id`, d.`name` AS 'department_name'
FROM employees AS e
JOIN departments AS d
ON e.employee_id = d.manager_id
ORDER BY `employee_id`
LIMIT 5;

# 2. Towns and Addresses
SELECT t.town_id, `name`, `address_text`
FROM `towns` t
JOIN `addresses` a
ON t.town_id = a.town_id
WHERE t.town_id IN (9, 15, 32);

# 3. Employees Without Managers
SELECT employee_id, first_name, last_name, department_id, salary
FROM employees
WHERE manager_id IS NULL;

# 4. High Salary
