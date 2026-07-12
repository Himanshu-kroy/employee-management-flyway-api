-- ==========================================
-- SAMPLE SQL QUERIES - EMPLOYEE MANAGEMENT
-- ==========================================

-- 1. Select all employees
SELECT * FROM employees;

-- 2. Find employees by department
-- Example: Retrieve all employees working in 'Engineering'
SELECT * FROM employees WHERE department = 'Engineering';

-- 3. Find employees with salary greater than a value
-- Example: Retrieve all employees earning more than 80,000.00
SELECT * FROM employees WHERE salary > 80000.00;

-- 4. Update employee salary
-- Example: Increase salary for employee with ID 5
UPDATE employees SET salary = 92000.00 WHERE id = 5;

-- 5. Delete employee
-- Example: Remove employee with ID 1
DELETE FROM employees WHERE id = 1;
