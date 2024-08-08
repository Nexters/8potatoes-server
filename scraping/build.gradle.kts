dependencies {
    implementation(project(":core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Selenium dependencies
    implementation("org.seleniumhq.selenium:selenium-java")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver")
    implementation("org.seleniumhq.selenium:selenium-support")
    // Apache POI dependencies
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    // Jsoup dependency
    implementation("org.jsoup:jsoup:1.15.3")
}

tasks.findByName("bootJar")?.apply {
    enabled = false
}

tasks.findByName("jar")?.apply {
    enabled = true
}