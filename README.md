
# Guide Me - Android App

A mobile application designed to help travelers discover authentic local experiences and connect with local guides.

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
