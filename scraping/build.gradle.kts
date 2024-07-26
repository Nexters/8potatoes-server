plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("io.asyncer:r2dbc-mysql:1.1.0")
    runtimeOnly("mysql:mysql-connector-java:8.0.28")
    runtimeOnly("io.r2dbc:r2dbc-pool")
    runtimeOnly("io.r2dbc:r2dbc-spi")
}
