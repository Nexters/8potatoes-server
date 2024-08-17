CREATE
USER IF NOT EXISTS 'master'@'%' IDENTIFIED BY 'master';
GRANT ALL PRIVILEGES ON *.* TO
'master'@'%';
FLUSH
PRIVILEGES;

CREATE
DATABASE IF NOT EXISTS reststop;

USE
reststop;

CREATE TABLE menu
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    food_seq       VARCHAR(255),
    reststop_code  VARCHAR(255) COMMENT '휴게소 매핑 key(service_area_code2)',
    name           VARCHAR(255) NOT NULL,
    price          INT,
    description    TEXT,
    is_recommended BOOLEAN,
    is_premium     BOOLEAN,
    is_best_food   BOOLEAN,
    category       VARCHAR(255) COMMENT '카테고리 : 스낵/한식/양식/중식/아시안',
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY (reststop_code, name)
);

CREATE TABLE brand
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_code    VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    reststop_code VARCHAR(255) NOT NULL,
    reststop_name VARCHAR(255) NOT NULL,
    route_name    VARCHAR(255) NOT NULL,
    address       VARCHAR(255) NOT NULL,
    thumbnail_url VARCHAR(255),
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE reststop
(
    id                       BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Primary key',
    standard_code            VARCHAR(255) NOT NULL COMMENT '표준 휴게소/주유소 공통 코드',
    name                     VARCHAR(255) NOT NULL COMMENT '휴게소명',
    road_route_no            VARCHAR(255) COMMENT '노선코드',
    road_route_name          VARCHAR(255) COMMENT '노선명',
    road_route_direction     VARCHAR(255) COMMENT '상행/하행',
    latitude                 DOUBLE COMMENT 'Y좌표값',
    longitude                DOUBLE COMMENT 'X좌표값',
    phone_number             VARCHAR(255) COMMENT '전화번호',
    reference_date           VARCHAR(255) COMMENT '데이터기준일자',
    representative_food_name VARCHAR(255) COMMENT '대표음식명',
    reststop_type            VARCHAR(255) COMMENT '휴게소유형 : 일반/화물차/간이',
    service_area_code        VARCHAR(255) COMMENT '서비스 구역 코드',
    address                  VARCHAR(255) COMMENT '주소',
    small_car_space          INT COMMENT '소형차 주차대수',
    large_car_space          INT COMMENT '대형차 주차대수',
    disabled_person_space    INT COMMENT '장애인 주차대수',
    total_space              INT COMMENT '총 주차대수',
    gasoline_price           VARCHAR(255) COMMENT '휘발유 가격',
    diesel_price             VARCHAR(255) COMMENT '경유 가격',
    lpg_price                VARCHAR(255) COMMENT 'LPG 가격',
    has_electric_charger     BOOLEAN COMMENT '전기차 충전소 여부',
    has_hydrogen_charger     BOOLEAN COMMENT '수소차 충전소 여부',
    restaurant_open_time     VARCHAR(255) COMMENT '식당 영업시간',
    naver_rating             FLOAT COMMENT '네이버 평점',
    created_at               TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    modified_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    UNIQUE KEY (standard_code)
) COMMENT='휴게소 정보';

CREATE TABLE convenient_facility
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    standard_code VARCHAR(255) NOT NULL COMMENT '표준 휴게소/주유소 공통 코드',
    name          VARCHAR(255) NOT NULL,
    code          VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    start_time     VARCHAR(255) NOT NULL,
    end_time       VARCHAR(255) NOT NULL,
    address       VARCHAR(255) NOT NULL,
    route_code     VARCHAR(255),
    route_name     VARCHAR(255),
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
