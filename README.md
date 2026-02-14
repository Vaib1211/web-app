# Bhada Chitthi

An Android application for managing business profiles.

## Features

- **First-time Onboarding**: Step-by-step profile creation when the app is launched for the first time
- **User Profile Fields**:
  - Mobile Number (Mandatory, 10-digit numeric)
  - Name (Editable maximum 1 time after creation)
  - Business Name (Editable maximum 1 time after creation)
- **Profile Management**: Edit name and business name with edit restrictions
- **Data Persistence**: Profile data is stored locally using SharedPreferences

## Project Structure

```
app/
├── src/main/
│   ├── java/com/bhadachitthi/
│   │   ├── MainActivity.kt - Main screen showing profile
│   │   ├── OnboardingActivity.kt - Step-by-step onboarding flow
│   │   ├── UserProfile.kt - Data model for user profile
│   │   └── ProfileManager.kt - SharedPreferences helper for profile management
│   ├── res/
│   │   ├── layout/ - UI layouts
│   │   └── values/ - Strings, colors, themes
│   └── AndroidManifest.xml
└── build.gradle
```

## Building the App

This is an Android application built with Kotlin. To build:

1. Open the project in Android Studio
2. Sync Gradle files
3. Build and run on an emulator or physical device

## Requirements

- Android SDK 21+
- Kotlin 1.8.0
- Gradle 7.4.0