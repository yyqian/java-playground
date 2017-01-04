IF EXISTS(SELECT * from sys.databases WHERE name='TestData')
BEGIN
  DROP DATABASE TestData;
END

CREATE DATABASE TestData;

USE TestData;

CREATE TABLE Products (ProductID int PRIMARY KEY NOT NULL, ProductName varchar(25) NOT NULL, Price money NULL, ProductDescription text NULL);

INSERT Products (ProductID, ProductName, Price, ProductDescription) VALUES (1, 'Clamp', 12.48, 'Workbench clamp');

INSERT Products (ProductName, ProductID, Price, ProductDescription) VALUES ('Screwdriver', 50, 3.17, 'Flat head');
