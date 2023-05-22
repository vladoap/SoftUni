# 01. Employee Address
SELECT employee_id, job_title, address_id, address_text
FROM employees
JOIN addresses
USING (address_id)
ORDER BY address_id
LIMIT 5;

# 02. Addresses with Towns
SELECT first_name, last_name, name AS 'town', address_text
FROM employees e
JOIN addresses a ON e.address_id = a.address_id
JOIN towns t ON a.town_id = t.town_id
ORDER BY first_name, last_name
LIMIT 5;

# 03. Sales Employee
SELECT e.employee_id, e.first_name, e.last_name, d.name AS 'department_name'
FROM employees e
JOIN departments d ON e.department_id = d.department_id
WHERE d.name = 'Sales'
ORDER BY employee_id DESC;

# 04. Employee Departments
SELECT e.employee_id, e.first_name, salary, d.name AS 'department_name'
FROM employees e
JOIN departments d ON e.department_id = d.department_id
WHERE salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;

# 05. Employees Without Project
SELECT e.employee_id, first_name
FROM employees e
LEFT JOIN employees_projects ep 
ON e.employee_id = ep.employee_id
WHERE ep.project_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;

# 06. Employees Hired After
SELECT e.first_name, e.last_name, e.hire_date, d.name AS 'dept_name'
FROM employees e
JOIN departments d
ON e.department_id = d.department_id
WHERE d.name IN ('Sales', 'Finance') AND DATE(e.hire_date) > '1999-01-01'
ORDER BY e.hire_date;

# 07. Employees with Project
SELECT e.employee_id, e.first_name, p.name AS 'project_name'
FROM employees e
JOIN employees_projects ep ON e.employee_id = ep.employee_id
JOIN projects p ON ep.project_id = p.project_id
WHERE DATE(p.start_date) > '2002-08-13' AND p.end_date IS NULL
ORDER BY e.first_name, p.name
LIMIT 5;

# 08. Employee 24
SELECT e.employee_id, e.first_name, IF(YEAR(p.start_date) >= 2005, NULL, p.name) AS 'project_name'
FROM employees e
JOIN employees_projects ep ON e.employee_id = ep.employee_id
JOIN projects p ON ep.project_id = p.project_id
WHERE e.employee_id = 24
ORDER BY p.name;


# 09. Employee Manager
SELECT e1.employee_id, e1.first_name, e1.manager_id, e2.first_name AS 'manager_name'
FROM employees e1
JOIN employees e2 
ON e1.manager_id = e2.employee_id
WHERE e1.manager_id IN (3, 7)
ORDER BY e1.first_name;

# 10. Employee Summary
SELECT e.employee_id, CONCAT(e.first_name, ' ', e.last_name) AS 'employee_name', CONCAT(m.first_name, ' ', m.last_name) AS 'manager_name', d.name AS 'department_name'
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id
JOIN departments d
ON e.department_id = d.department_id
ORDER BY e.employee_id
LIMIT 5;

# 11. Min Average Salary
SELECT AVG(salary) AS 'min_average_salary' 
FROM employees
GROUP BY department_id
ORDER BY min_average_salary
LIMIT 1;



