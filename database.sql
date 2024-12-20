CREATE DATABASE IF NOT EXISTS laboratoire;
USE laboratoire;

CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE members (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE members_roles (
    member_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (member_id, role_id),
    FOREIGN KEY (member_id) REFERENCES members(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    start_date DATE,
    end_date DATE
);

CREATE TABLE publication (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    date_pub DATE NOT NULL,
    publication VARCHAR(255) NOT NULL,
    project_id BIGINT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(id)
);

CREATE TABLE resources (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    availability VARCHAR(255) NOT NULL
);

INSERT INTO roles (id, name) VALUES
(1, 'ADMIN'),
(2, 'STAFF'),
(3, 'CLIENT');

INSERT INTO members (id, first_name, last_name, email, password) VALUES
(1, 'John', 'Doe', 'john.doe@example.com', 'password1'),
(2, 'Jane', 'Smith', 'jane.smith@example.com', 'password2'),
(3, 'Alice', 'Johnson', 'alice.johnson@example.com', 'password3'),
(4, 'Bob', 'Brown', 'bob.brown@example.com', 'password4'),
(5, 'Charlie', 'Davis', 'charlie.davis@example.com', 'password5'),
(6, 'Emily', 'Clark', 'emily.clark@example.com', 'password6'),
(7, 'Daniel', 'Evans', 'daniel.evans@example.com', 'password7'),
(8, 'Sophia', 'Harris', 'sophia.harris@example.com', 'password8'),
(9, 'James', 'Wilson', 'james.wilson@example.com', 'password9'),
(10, 'Oliver', 'Martinez', 'oliver.martinez@example.com', 'password10');

INSERT INTO members_roles (member_id, role_id) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 3),
(5, 3),
(6, 2),
(7, 1),
(8, 3),
(9, 2),
(10, 3);

INSERT INTO project (id, title, description, start_date, end_date) VALUES
(1, 'Research Project 1', 'Description of Project 1', '2024-01-01', '2024-06-30'),
(2, 'Research Project 2', 'Description of Project 2', '2024-02-01', '2024-07-31'),
(3, 'Research Project 3', 'Description of Project 3', '2024-03-01', '2024-08-31'),
(4, 'Research Project 4', 'Description of Project 4', '2024-04-01', '2024-09-30'),
(5, 'Research Project 5', 'Description of Project 5', '2024-05-01', '2024-10-31'),
(6, 'Research Project 6', 'Description of Project 6', '2024-06-01', '2024-11-30'),
(7, 'Research Project 7', 'Description of Project 7', '2024-07-01', '2024-12-31'),
(8, 'Research Project 8', 'Description of Project 8', '2024-08-01', '2025-01-31'),
(9, 'Research Project 9', 'Description of Project 9', '2024-09-01', '2025-02-28'),
(10, 'Research Project 10', 'Description of Project 10', '2024-10-01', '2025-03-31');

INSERT INTO publication (id, title, author, date_pub, publication, project_id) VALUES
(1, 'Publication 1', 'Author 1', '2024-01-15', 'Journal 1', 1),
(2, 'Publication 2', 'Author 2', '2024-02-15', 'Journal 2', 1),
(3, 'Publication 3', 'Author 3', '2024-03-15', 'Journal 3', 2),
(4, 'Publication 4', 'Author 4', '2024-04-15', 'Journal 4', 2),
(5, 'Publication 5', 'Author 5', '2024-05-15', 'Journal 5', 3),
(6, 'Publication 6', 'Author 6', '2024-06-15', 'Journal 6', 3),
(7, 'Publication 7', 'Author 7', '2024-07-15', 'Journal 7', 4),
(8, 'Publication 8', 'Author 8', '2024-08-15', 'Journal 8', 4),
(9, 'Publication 9', 'Author 9', '2024-09-15', 'Journal 9', 5),
(10, 'Publication 10', 'Author 10', '2024-10-15', 'Journal 10', 5);

INSERT INTO resources (id, name, description, availability) VALUES
(1, 'Resource 1', 'Description for Resource 1', 'Available'),
(2, 'Resource 2', 'Description for Resource 2', 'Not Available'),
(3, 'Resource 3', 'Description for Resource 3', 'Available'),
(4, 'Resource 4', 'Description for Resource 4', 'Not Available'),
(5, 'Resource 5', 'Description for Resource 5', 'Available'),
(6, 'Resource 6', 'Description for Resource 6', 'Not Available'),
(7, 'Resource 7', 'Description for Resource 7', 'Available'),
(8, 'Resource 8', 'Description for Resource 8', 'Not Available'),
(9, 'Resource 9', 'Description for Resource 9', 'Available'),
(10, 'Resource 10', 'Description for Resource 10', 'Not Available');
