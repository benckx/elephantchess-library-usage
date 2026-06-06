# About

Sample project to check we can use libraries from https://github.com/benckx/elephantchess

The libraries are published via [JitPack](https://jitpack.io/#benckx/elephantchess), so you need to:

1. add the JitPack repository
2. declare the dependencies you need

You can either use a released tag (e.g. `2.0.0`) or build the latest commit of a branch with the
`-SNAPSHOT` suffix (e.g. `master-SNAPSHOT`).

[![Build](https://github.com/benckx/elephantchess-library-usage/actions/workflows/build.yml/badge.svg)](https://github.com/benckx/elephantchess-library-usage/actions/workflows/build.yml) [![](https://www.jitpack.io/v/benckx/elephantchess.svg)](https://www.jitpack.io/#benckx/elephantchess)

## Available artifacts

All artifacts share the group `com.github.benckx.elephantchess`:

| Artifact                         | Description                           | Scope |
|----------------------------------|---------------------------------------|-------|
| `xiangqi-core`                   | Xiangqi (Chinese chess) board & rules | main  |
| `xiangqi-core-test-utils`        | Test helpers (sample Xiangqi games)   | test  |
| `engine-api`                     | Engine API                            | main  |
| `seven-kingdoms-core`            | Seven Kingdoms variant board & rules  | main  |
| `seven-kingdoms-core-test-utils` | Test helpers (sample 7K games)        | test  |

## Kotlin Gradle (`build.gradle.kts`)

```kotlin
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.benckx.elephantchess:xiangqi-core:2.0.0")
    implementation("com.github.benckx.elephantchess:engine-api:2.0.0")
    implementation("com.github.benckx.elephantchess:seven-kingdoms-core:2.0.0")

    testImplementation("com.github.benckx.elephantchess:xiangqi-core-test-utils:2.0.0")
    testImplementation("com.github.benckx.elephantchess:seven-kingdoms-core-test-utils:2.0.0")
}
```

### With a version catalog (`gradle/libs.versions.toml`)

This is the approach used by this project:

```toml
[versions]
elephantchess = "2.0.0"

[libraries]
elephantchess-xiangqi-core = { module = "com.github.benckx.elephantchess:xiangqi-core", version.ref = "elephantchess" }
elephantchess-xiangqi-core-test-utils = { module = "com.github.benckx.elephantchess:xiangqi-core-test-utils", version.ref = "elephantchess" }
elephantchess-engine-api = { module = "com.github.benckx.elephantchess:engine-api", version.ref = "elephantchess" }
elephantchess-seven-kingdoms-core = { module = "com.github.benckx.elephantchess:seven-kingdoms-core", version.ref = "elephantchess" }
elephantchess-seven-kingdoms-core-test-utils = { module = "com.github.benckx.elephantchess:seven-kingdoms-core-test-utils", version.ref = "elephantchess" }
```

```kotlin
dependencies {
    implementation(libs.elephantchess.xiangqi.core)
    implementation(libs.elephantchess.engine.api)
    implementation(libs.elephantchess.seven.kingdoms.core)

    testImplementation(libs.elephantchess.xiangqi.core.test.utils)
    testImplementation(libs.elephantchess.seven.kingdoms.core.test.utils)
}
```

## Groovy Gradle (`build.gradle`)

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.benckx.elephantchess:xiangqi-core:2.0.0'
    implementation 'com.github.benckx.elephantchess:engine-api:2.0.0'
    implementation 'com.github.benckx.elephantchess:seven-kingdoms-core:2.0.0'

    testImplementation 'com.github.benckx.elephantchess:xiangqi-core-test-utils:2.0.0'
    testImplementation 'com.github.benckx.elephantchess:seven-kingdoms-core-test-utils:2.0.0'
}
```

> **Note:** `-SNAPSHOT` versions are mutable. If JitPack has already cached an older build, force a refresh with
> `./gradlew build --refresh-dependencies`.
