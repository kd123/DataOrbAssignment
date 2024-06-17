CREATE SCHEMA IF NOT EXISTS employees;

SET SCHEMA employees;

CREATE TABLE IF NOT EXISTS Employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employeeReferenceNumber VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    designation VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL
);

