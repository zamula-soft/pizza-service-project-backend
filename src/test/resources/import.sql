-- import.sql
-- TEST ENVIRONMENT
-- CAFE
--INSERT INTO CAFE (ID, TITLE, EMAIL) VALUES (1, 'Test cafe', 'test@amil.com');


-- PIZZA
INSERT INTO PIZZA(ID, CAFE_ID, NAME, SIZE, PRICE) VALUES (1, 1, 'Test pizza 1', 'XL', 10.90);
INSERT INTO PIZZA(ID, CAFE_ID, NAME, SIZE, PRICE) VALUES (2, 1, 'Test pizza 2', 'L', 15.90);
