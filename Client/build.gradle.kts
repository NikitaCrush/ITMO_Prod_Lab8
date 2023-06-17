plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.5.10"
    id ("org.openjfx.javafxplugin") version "0.0.8"
    application
}

group = "org.NikitaCrush"
version = "2.0"

dependencies {
    implementation(project(":common"))
    testImplementation(kotlin("test"))
    implementation("org.openjfx:javafx-base:17-ea+11")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation ("no.tornado:tornadofx:1.7.20")
    implementation("io.insert-koin:koin-core:3.3.3")
    implementation("io.insert-koin:koin-core:3.3.3")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("org.postgresql:postgresql:42.2.27")
    implementation ("org.openjfx:javafx-controls:16")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

javafx {
    version = "11.0.2"
    modules("javafx.graphics", "javafx.controls")
}