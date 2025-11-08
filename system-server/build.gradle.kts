plugins {
    id("java-library")
    id("war")
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.13.2")
    implementation(project(":framework-core"))
}

tasks.test {
    useJUnitPlatform()
}