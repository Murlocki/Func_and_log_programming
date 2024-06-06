import org.jetbrains.kotlin.gradle.dsl.JvmTarget
plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "2.0.0"
    application
    id("org.owasp.dependencycheck") version "8.0.0"
    id("io.ktor.plugin") version "2.3.11"
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

application {
    mainClass.set("org.example.MainKt")
}
tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.example.MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
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