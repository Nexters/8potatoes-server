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
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Primary key',
    standardCode VARCHAR(255) NOT NULL COMMENT '표준 휴게소/주유소 공통 코드',
    name VARCHAR(255) NOT NULL COMMENT '휴게소명',
    code VARCHAR(255) NOT NULL COMMENT '휴게소 코드',
    routeName VARCHAR(255) NOT NULL COMMENT '노선명',
    routeNo VARCHAR(255) NOT NULL COMMENT '노선코드',
    longitude FLOAT NOT NULL COMMENT 'X좌표값',
    latitude FLOAT NOT NULL COMMENT 'Y좌표값',
    serviceAreaCode VARCHAR(255) NOT NULL COMMENT '서비스 구역 코드',
    smallCarSpace INT NOT NULL COMMENT '소형차 주차대수',
    largeCarSpace INT NOT NULL COMMENT '대형차 주차대수',
    disabledPersonSpace INT NOT NULL COMMENT '장애인 주차대수',
    routeDirection VARCHAR(255) NOT NULL COMMENT '상행/하행',
    gasolinePrice VARCHAR(255) DEFAULT NULL COMMENT '휘발유 가격',
    dieselPrice VARCHAR(255) DEFAULT NULL COMMENT '경유 가격',
    lpgPrice VARCHAR(255) DEFAULT NULL COMMENT 'LPG 가격',
    address VARCHAR(255) NOT NULL COMMENT '주소',
    phoneNumber VARCHAR(255) NOT NULL COMMENT '전화번호',
    hasElectricCharger BOOLEAN NOT NULL DEFAULT FALSE COMMENT '전기차 충전소 여부',
    hasHydrogenCharger BOOLEAN NOT NULL DEFAULT FALSE COMMENT '수소차 충전소 여부',
    restaurantOpenTime VARCHAR(255) DEFAULT NULL COMMENT '식당 영업시간',
    naverRating FLOAT DEFAULT NULL COMMENT '네이버 평점',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='휴게소 정보';
