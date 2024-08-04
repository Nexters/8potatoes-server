CREATE USER IF NOT EXISTS 'master'@'%' IDENTIFIED BY 'master';
GRANT ALL PRIVILEGES ON *.* TO 'master'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS reststop;

USE reststop;

CREATE TABLE menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    food_seq VARCHAR(255),
    reststop_id VARCHAR(255) COMMENT '휴게소 매핑 key(service_area_code2)',
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
    reststop_code VARCHAR(255) NOT NULL,
    reststop_name VARCHAR(255) NOT NULL,
    route_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    thumbnail_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE reststop (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reststop_code VARCHAR(255),
    name VARCHAR(255),
    road_kind VARCHAR(255),
    road_route_no VARCHAR(255),
    road_route_name VARCHAR(255),
    road_route_direction VARCHAR(255),
    destination_direction VARCHAR(255),
    reststop_type VARCHAR(255),
    latitude FLOAT,
    longitude FLOAT,
    open_time VARCHAR(255),
    close_time VARCHAR(255),
    phone_number VARCHAR(255),
    has_gas_station BOOLEAN,
    has_lpg_charging_station BOOLEAN,
    has_electric_charging_station BOOLEAN,
    has_pharmacy BOOLEAN,
    has_restaurant BOOLEAN,
    has_nursing_room BOOLEAN,
    has_toilet BOOLEAN,
    route_code VARCHAR(255),
    gasoline_price VARCHAR(255),
    diesel_price VARCHAR(255),
    lpg_price VARCHAR(255),
    gas_station_code VARCHAR(255) COMMENT '주유소 코드(service_area_code2)',
    naver_rating FLOAT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY (name, destination_direction)
);
