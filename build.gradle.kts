plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ben.manes.versions)
    alias(libs.plugins.test.logger)
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

    testImplementation(libs.elephantchess.xiangqi.core.test.utils)
    testImplementation(libs.elephantchess.seven.kingdoms.core.test.utils)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(kotlin("test"))
    testRuntimeOnly(libs.logback.classic)
}

tasks.test {
    useJUnitPlatform()
    minHeapSize = "512M"
    maxHeapSize = "512M"
    testLogging {
        showStandardStreams = true
    }
}

kotlin {
    jvmToolchain(21)
}
