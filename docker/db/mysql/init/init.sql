CREATE USER IF NOT EXISTS 'master'@'%' IDENTIFIED BY 'master';
GRANT ALL PRIVILEGES ON *.* TO 'master'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS reststop;

USE reststop;

CREATE TABLE menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    food_seq VARCHAR(255),
    price INT,
    description TEXT,
    is_recommended BOOLEAN,
    is_premium BOOLEAN,
    is_best_food BOOLEAN,
    reststop_id INT
);