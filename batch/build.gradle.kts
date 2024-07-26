dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("io.asyncer:r2dbc-mysql:1.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    runtimeOnly("mysql:mysql-connector-java:8.0.28")
    runtimeOnly("io.r2dbc:r2dbc-pool")
    runtimeOnly("io.r2dbc:r2dbc-spi")
    runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.80.Final:osx-aarch_64")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    implementation(kotlin("stdlib"))

    // Selenium dependencies
    implementation("org.seleniumhq.selenium:selenium-java")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver")
    implementation("org.seleniumhq.selenium:selenium-support")

    // Apache POI dependencies
    implementation("org.apache.poi:poi-ooxml:5.2.3")

    // Jsoup dependency
    implementation("org.jsoup:jsoup:1.15.3")
}
