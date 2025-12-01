plugins {
    id("java-library")
    id("war")
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.13.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(project(":framework-core"))
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    api("org.apache.httpcomponents:httpcore:4.4.10")
    api("org.apache.httpcomponents:httpclient:4.5.6")
}

tasks.test {
    useJUnitPlatform()
}