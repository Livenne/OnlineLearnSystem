plugins {
    id("java-library")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    api("jakarta.servlet:jakarta.servlet-api:6.1.0")
    api("org.reflections:reflections:0.10.2")
    api("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    api("ch.qos.logback:logback-classic:1.5.18")
    api("com.mysql:mysql-connector-j:8.0.33")
    compileOnly("org.jetbrains:annotations:24.0.1")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    api("org.reflections:reflections:0.10.2")
    api("com.auth0:java-jwt:3.8.1")
}

tasks.test {
    useJUnitPlatform()
}