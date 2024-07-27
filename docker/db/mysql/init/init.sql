CREATE USER IF NOT EXISTS 'master'@'%' IDENTIFIED BY 'master';
GRANT ALL PRIVILEGES ON *.* TO 'master'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS reststop;

USE reststop;

CREATE TABLE menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    food_seq VARCHAR(255),
    reststop_id INT,
    name VARCHAR(255) NOT NULL,
    price INT,
    description TEXT,
    is_recommended BOOLEAN,
    is_premium BOOLEAN,
    is_best_food BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY (reststop_id, name)
);

CREATE TABLE brand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    rest_stop_code VARCHAR(255) NOT NULL,
    rest_stop_name VARCHAR(255) NOT NULL,
    route_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    thumbnail_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
