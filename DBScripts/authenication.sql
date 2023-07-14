CREATE DATABASE IF NOT EXISTS authentication;
USE authentication; 


DROP TABLE IF EXISTS RegistrationToken;
DROP TABLE IF EXISTS UserRole;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS User;

CREATE TABLE User (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255),
    createAt DATETIME NOT NULL default NOW(),
    lastModificationAt DATETIME NOT NULL default NOW(),
    activeFlag BOOLEAN NOT NULL default true,
    provider ENUM('local', 'google','facebook','github')  NOT NULL default 'local',
    providerId VARCHAR(255),
    imageUrl VARCHAR(255)
);

CREATE TABLE Role (
    roleId INT PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(50) NOT NULL,
    roleDescription VARCHAR(255) NOT NULL,
    createAt DATETIME NOT NULL,
    lastModificationAt DATETIME NOT NULL
);

CREATE TABLE UserRole (
    userRoleId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    roleId INT NOT NULL,
    activeFlag BOOLEAN NOT NULL,
    createAt DATETIME NOT NULL,
    lastModificationAt DATETIME NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (roleId) REFERENCES Role(roleId)
);

/*CREATE TABLE ReferralCode (
    referralCodeId INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) NOT NULL,
    email VARCHAR(50) NOT NULL,
    expirationDate DATETIME NOT NULL,
    createBy INT NOT NULL,
    consumed BOOLEAN NOT NULL default false,
	FOREIGN KEY (createBy) REFERENCES User(userId)
);*/


-- Insert user record
INSERT INTO User (userName, email, password)
VALUES 
('admin@example.com', 'admin@example.com', '$2a$10$BJf6vvQKxTXaSzvBMivzb.cfuZTYhdqfcvf/gGE5goE7d/yrGKQB6'),
('user@example.com', 'user@example.com', '$2a$10$BJf6vvQKxTXaSzvBMivzb.cfuZTYhdqfcvf/gGE5goE7d/yrGKQB6');

-- Insert admin role record
INSERT INTO Role (roleName, roleDescription, createAt, lastModificationAt)
VALUES 
('admin', 'Administrator role', NOW(), NOW()),
('user', 'Normal user role', NOW(), NOW());

/*INSERT INTO RegistrationToken (token, email, expirationDate, createBy)
VALUES ('1681065484215-c164e267-8a39-4267-b8ae-177b83077632', 'newUser@example.com', DATE_ADD(NOW(), INTERVAL 3 HOUR), 1),
('1681065484215-c164e267-8a39-4267-b8ae-177b83077631', 'testAddUser@example.com', DATE_ADD(NOW(), INTERVAL 356 DAY), 1);*/

-- Insert user record
INSERT INTO User (username, email, password)
VALUES 
('user3@example.com', 'user3@example.com', '$2a$10$BJf6vvQKxTXaSzvBMivzb.cfuZTYhdqfcvf/gGE5goE7d/yrGKQB6'),
('user4@example.com', 'user4@example.com', '$2a$10$BJf6vvQKxTXaSzvBMivzb.cfuZTYhdqfcvf/gGE5goE7d/yrGKQB6'),
('user5@example.com', 'user5@example.com', '$2a$10$BJf6vvQKxTXaSzvBMivzb.cfuZTYhdqfcvf/gGE5goE7d/yrGKQB6');

INSERT INTO UserRole (userId, roleId, activeFlag, createAt, lastModificationAt)
VALUES
(1, 1, 1, NOW(), NOW()),
(2, 2, 1, NOW(), NOW()), 
(3, 2, 1, NOW(), NOW()),
(4, 2, 1, NOW(), NOW()),
(5, 2, 1, NOW(), NOW());
