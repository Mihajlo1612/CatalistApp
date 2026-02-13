# CatalistApp

[![Android](https://img.shields.io/badge/Android-26%2B-3DDC84?logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9%2B-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack%20Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)

CatalistApp is an Android application built with Kotlin and Jetpack Compose. The current app label is "TestApp" and the package name is `rs.raf.edu`.

## Overview

- UI: Jetpack Compose + Material 3
- DI: Hilt
- Networking: Retrofit + OkHttp
- Serialization: Kotlinx Serialization
- Image loading: Coil
- Min SDK: 26
- Target/Compile SDK: 35

## Features

- Modern Compose UI
- Dependency injection with Hilt
- Network layer with Retrofit and OkHttp

## Project Structure

TestApp/
app/
src/
main/
test/
androidTest/
build.gradle.kts
settings.gradle.kts
gradle/


## Requirements

- Android Studio (latest stable)
- JDK 17
- Android SDK 35

## Getting Started

1) Clone the repo and open the root folder in Android Studio.
2) Let Gradle sync finish.
3) Run the `app` configuration on a device or emulator.

## Build and Run (Windows PowerShell)

```powershell
# Debug APK
.\gradlew assembleDebug

# Install debug APK on a connected device
.\gradlew installDebug

# Unit tests
.\gradlew test

# Instrumented tests
.\gradlew connectedAndroidTest

Configuration
local.properties should contain your Android SDK path and must not be committed.
Update applicationId, versionCode, and versionName in build.gradle.kts if needed.
Architecture Notes
Compose UI layer in app/src/main
DI modules under app/src/main
Data/networking in app/src/main
Roadmap
Add feature list here
Add screenshots in a docs/ folder
Contributing
See CONTRIBUTING.md.

Security
See SECURITY.md.

Changelog
See CHANGELOG.md.

License
No license specified yet. Add one in LICENSE if you intend to open-source this project.
