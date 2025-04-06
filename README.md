# Guide Me - Android Travel Experience App

Guide Me is a mobile application designed to help travelers discover authentic local experiences and connect with certified local guides. The app provides a seamless platform for exploring unique cultural experiences, local cuisine, and hidden gems.

## Development Requirements

### Android Studio
- Android Studio Arctic Fox (2020.3.1) or newer
- Gradle version 7.2.2
- Build tools version 33.0.1

### Android Requirements
- Minimum SDK: API 21 (Android 5.0)
- Target SDK: API 34 (Android 14)
- Compile SDK: 34

### Required Permissions
```xml
<!-- Location Permissions -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>

<!-- Camera Permissions -->
<uses-permission android:name="android.permission.CAMERA"/>
<uses-feature android:name="android.hardware.camera" android:required="false"/>
<uses-feature android:name="android.hardware.camera.autofocus" android:required="false"/>

<!-- GPS Feature -->
<uses-feature android:name="android.hardware.location.gps"/>
```

## Features

- **Onboarding Experience**: Interactive introduction to the app's features
- **User Authentication**: Login and registration system
- **Experience Discovery**: Browse and search for local experiences
- **Interactive Maps**: View experience locations and get directions
- **Media Gallery**: Photos and videos of experiences
- **Profile Management**: User profiles with media uploads
- **Reviews System**: Rate and review experiences
- **Credit Card Scanner**: Secure payment integration

## Project Structure

```
app/
├── src/main/
    ├── java/com/ysf/mslh/guideme/
    │   ├── fragments/           # UI fragments
    │   ├── fragmentsExperience/ # Experience-related fragments
    │   └── adapters/           # RecyclerView adapters
    └── res/
        ├── layout/             # XML layouts
        ├── drawable/           # Images and icons
        └── values/            # App resources
```

## Development Setup

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or physical device

## Technologies Used

- Android SDK
- Google Maps Integration
- Credit Card Scanner API
- RecyclerView for Lists
- ViewPager for Sliding Views
- Bottom Sheet Dialogs