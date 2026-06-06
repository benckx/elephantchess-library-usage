plugins {
    kotlin("jvm") version "2.2.20"
    application
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.benckx.elephantchess:xiangqi-core:master-SNAPSHOT")
    implementation("com.github.benckx.elephantchess:engine-api:master-SNAPSHOT")

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
