import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

tasks {
    withType<ShadowJar> {
        manifest {
            attributes(
                "Main-Class" to "com.livenne.OnlineLearnSystem"
            )
        }
    }
}

group = "com.livenne"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
    mavenLocal()
}


dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.13.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("io.github.livenne:webframework:1.0.0")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    implementation("redis.clients:jedis:3.9.0")
}

tasks.test {
    useJUnitPlatform()
}