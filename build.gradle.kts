plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ben.manes.versions)
    application
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation(libs.elephantchess.xiangqi.core)
    implementation(libs.elephantchess.engine.api)
    implementation(libs.elephantchess.seven.kingdoms.core)

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
