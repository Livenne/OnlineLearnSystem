plugins {
    id("java-library")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    api("jakarta.servlet:jakarta.servlet-api:6.1.0")
    api("net.bytebuddy:byte-buddy:1.14.0")
    api("org.reflections:reflections:0.10.2")
    api("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    api("com.auth0:java-jwt:3.8.1")
    api("org.hibernate.orm:hibernate-core:6.4.0.Final")
    api("com.mysql:mysql-connector-j:8.2.0")
    api("jakarta.persistence:jakarta.persistence-api:3.1.0")
}

tasks.test {
    useJUnitPlatform()
}