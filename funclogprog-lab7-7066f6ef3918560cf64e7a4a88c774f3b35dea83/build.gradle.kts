import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "1.8.0"
    application
    id("org.owasp.dependencycheck") version "8.0.0"
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("io.ktor:ktor-server-netty:2.2.1")
    implementation("io.ktor:ktor-server-core:2.2.1")
    implementation("io.ktor:ktor-server-content-negotiation:2.2.1")
    implementation("io.ktor:ktor-serialization-gson:2.2.1")
    implementation("org.apache.poi:poi-ooxml:5.2.3")
}

application {
    mainClass.set("MainKt")
}

// Task for running dependency check
tasks.named("check").configure {
    dependsOn("dependencyCheckAnalyze")
}
