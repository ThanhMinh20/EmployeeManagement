

USE EmployeeManagement;
GO

CREATE TABLE Employees (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    salary FLOAT NOT NULL,
    departmentId INT NOT NULL,
    status NVARCHAR(20) NOT NULL,
    hireDate DATE NOT NULL
);
GO

-- Sample data
INSERT INTO Employees (name, salary, departmentId, status, hireDate)
VALUES 
(N'Nguyen Van A', 1200, 1, 'ACTIVE', '2023-01-10'),
(N'Tran Thi B', 1000, 2, 'PENDING', '2024-02-15'),
(N'Le Van C', 900, 1, 'TERMINATED', '2022-08-20');
GO
