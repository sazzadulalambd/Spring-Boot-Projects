/∗ Employee table data (created in Chapter 3) ∗/
INSERT INTO employee (employee_id, employee_name, age)
VALUES(1, 'Bob', 30);
/∗ User master data (ADMIN authority) ∗/
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage,
role)
VALUES('system@xxx.co.jp', 'password', 'system', '1990-01-01', 30, false,
'ROLE_ADMIN');