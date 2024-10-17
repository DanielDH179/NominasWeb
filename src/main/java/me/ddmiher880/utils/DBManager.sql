SET PASSWORD FOR 'root'@'localhost' = PASSWORD('123456');
FLUSH PRIVILEGES;

-- $ mysql -u root -p
-- $ mysql --skip-ssl

DROP DATABASE IF EXISTS DWES_NominasWeb;
CREATE DATABASE DWES_NominasWeb;
USE DWES_NominasWeb;

SET NAMES 'utf8mb4';
SET CHARACTER SET 'utf8mb4';
SET character_set_connection = 'utf8mb4';
SET character_set_client = 'utf8mb4';
SET character_set_results = 'utf8mb4';
-- SHOW variables LIKE 'character_%';

CREATE TABLE IF NOT EXISTS auth_user(
  username NVARCHAR(50) PRIMARY KEY,
  password NVARCHAR(100) NOT NULL,
  alias NVARCHAR(100)
);

CREATE TABLE employee(
  name NVARCHAR(100) NOT NULL,
  dni CHAR(9) PRIMARY KEY,
  sex CHAR(1),
  category INT(2),
  years INT(5),
  signed_up_date DATETIME,
  modified_date DATETIME,
  CONSTRAINT CHK_sex CHECK (sex IN ('M', 'F')),
  CONSTRAINT CHK_category CHECK (category BETWEEN 1 AND 10),
  CONSTRAINT CHK_years CHECK (years >= 0)
);

CREATE TABLE payroll(
  dni CHAR(9) PRIMARY KEY,
  salary INT(10),
  CONSTRAINT FK_dni FOREIGN KEY (dni) REFERENCES employee(dni) ON DELETE CASCADE
);

DELIMITER $$

CREATE TRIGGER insert_employee
BEFORE INSERT ON employee
FOR EACH ROW BEGIN
  DECLARE total INT;
  SELECT COUNT(*) INTO total FROM employee WHERE dni = NEW.dni;
  IF total > 0 THEN
    set @error = concat('ERROR: ', NEW.dni, ' ya está registrado');
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = @error;
  END IF;
  IF NEW.name = '' OR TRIM(NEW.name) = '' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'ERROR: El nombre no puede ser nulo';
  END IF;
END $$

CREATE TRIGGER update_employee
BEFORE UPDATE ON employee
FOR EACH ROW BEGIN
  IF NEW.name = '' OR TRIM(NEW.name) = '' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'ERROR: El nombre no puede ser nulo';
  END IF;
END $$

DELIMITER ;

INSERT INTO auth_user VALUES
  ('admin', 'admin', 'ddmiher880');

INSERT INTO employee (name, dni, sex, category, years, signed_up_date) VALUES
  ('Juan Pérez', '12345678Z', 'M', 3, 5, '2019-06-01 08:30:00'),
  ('María López', '87654321X', 'F', 5, 8, '2015-03-15 09:00:00'),
  ('Carlos García', '11223344B', 'M', 4, 3, '2020-01-20 10:30:00'),
  ('Ana Sánchez', '99887766P', 'F', 2, 10, '2014-12-11 11:45:00'),
  ('Pedro Fernández', '55667788Z', 'M', 7, 15, '2001-07-30 07:50:00'),
  ('Laura Martínez', '12341234D', 'F', 2, 4, '2020-02-01 09:15:00'),
  ('Sara Ruiz', '44556677L', 'F', 1, 1, '2021-01-10 11:00:00'),
  ('Elena Torres', '11111111H', 'F', 3, 6, '2017-08-25 14:45:00'),
  ('Miguel Ángel', '98765432M', 'M', 6, 12, '2016-04-20 15:30:00'),
  ('Carmen Jiménez', '22223333C', 'F', 1, 2, '2022-07-15 10:00:00'),
  ('Luis Hernández', '33334444S', 'M', 8, 1, '2023-01-05 16:15:00'),
  ('Beatriz Silva', '44445555X', 'F', 5, 9, '2018-12-30 08:00:00'),
  ('Roberto Ruiz', '55556666M', 'M', 2, 4, '2021-09-11 13:20:00'),
  ('Patricia Gómez', '66667777T', 'F', 3, 6, '2019-05-17 12:25:00'),
  ('Fernando Díaz', '77778888H', 'M', 7, 11, '2020-03-28 11:50:00'),
  ('Vanessa Morales', '88889999J', 'F', 4, 7, '2017-06-22 09:45:00'),
  ('Javier Ortega', '33333333P', 'M', 9, 0, '2024-02-14 14:30:00');

INSERT INTO payroll VALUES
  ('12345678Z', 115000),
  ('87654321X', 170000),
  ('11223344B', 125000),
  ('99887766P', 120000),
  ('55667788Z', 245000),
  ('12341234D', 90000),
  ('44556677L', 55555),
  ('11111111H', 120000),
  ('98765432M', 210000),
  ('22223333C', 60000),
  ('33334444S', 195000),
  ('44445555X', 175000),
  ('55556666M', 90000),
  ('66667777T', 120000),
  ('77778888H', 225000),
  ('88889999J', 145000),
  ('33333333P', 210000);
