plugins {
    id("java")
}

group = "me.gabl"
version = "IDEA-0.0.2"

repositories {
    mavenCentral()
}

dependencies {
    /* importing git/NatroxMC Common from local files */
    implementation(files("D:\\intelliJ\\natrox\\Common\\build\\libs\\common.jar"))
    implementation("org.jetbrains:annotations:20.1.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.8.2")
    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine:1.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}