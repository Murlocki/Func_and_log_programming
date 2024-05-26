import org.jetbrains.kotlin.gradle.dsl.JvmTarget
plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "2.0.0"
    application
    id("org.owasp.dependencycheck") version "8.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("io.ktor:ktor-server-netty:2.2.1")
    implementation("io.ktor:ktor-server-core:2.2.1")
    implementation("io.ktor:ktor-server-content-negotiation:2.2.1")
    implementation("io.ktor:ktor-serialization-gson:2.2.1")
    implementation("org.apache.poi:poi-ooxml:5.2.3")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks.named("check").configure {
    dependsOn("dependencyCheckAnalyze")
}