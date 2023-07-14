CREATE DATABASE IF NOT EXISTS member;
USE member;

DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS Gender;

CREATE TABLE Gender (
    genderId INT PRIMARY KEY AUTO_INCREMENT,
    genderName VARCHAR(255) NOT NULL
);

INSERT INTO Gender (genderName)
VALUES 
('Male'), 
('Female'), 
('Others'), 
('Prefer_not_to_tell');


CREATE TABLE Member (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(255) NOT NULL,
    middleName VARCHAR(255),
    lastName VARCHAR(255) NOT NULL,
    preferredName VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    cellPhone VARCHAR(20) NOT NULL,
    genderId INT,
    birthday DATE,
    memberSince DATETIME NOT NULL default NOW(),
    remainingCredit BIGINT NOT NULL,
    activeFlag BOOLEAN NOT NULL default TRUE,
    FOREIGN KEY (userId) REFERENCES authentication.User(userId) ON DELETE CASCADE,
    FOREIGN KEY (genderId) REFERENCES Gender(genderId)
);

-- Insert Member data
INSERT INTO Member 
(firstName, middleName, lastName, preferredName, email, cellPhone, genderId, birthday, memberSince, remainingCredit, activeFlag)
VALUES
('John', 'James', 'Doe', 'Johnny', 'john.doe@email.com', '1234567890', 1, '1980-01-01', NOW(), 100, TRUE),
('Jane', 'Marie', 'Smith', 'Janie', 'jane.smith@email.com', '2345678901', 2, '1990-02-02', NOW(), 200, TRUE),
('Alex', NULL, 'Johnson', NULL, 'alex.johnson@email.com', '3456789012', 3, NULL, NOW(), 300, TRUE),
('Taylor', NULL, 'Brown', 'Tay', 'taylor.brown@email.com', '4567890123', 4, '2000-03-03', NOW(), 400, FALSE);