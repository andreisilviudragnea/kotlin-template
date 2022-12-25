import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.4")
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("org.apache.kafka:kafka-clients:3.3.1")
    testImplementation("org.apache.logging.log4j:log4j-core:2.19.0")
    testImplementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.19.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}
