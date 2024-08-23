dependencies {
    implementation(project(":core"))
    implementation(project(":client"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.364")
}

tasks.getByName<Jar>("jar"){
    enabled=false
}