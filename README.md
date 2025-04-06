# Guide Me - Android Travel Experience App
![Guide Me Logo](https://raw.githubusercontent.com/youssefMouslih/guideme/main/logo.png)

## 📱 Overview

**Guide Me** is a mobile application designed to help travelers discover authentic local experiences and connect with certified local guides. The app provides a seamless platform for exploring unique cultural experiences, local cuisine, and hidden gems that typical tourists might miss.

With Guide Me, travelers can:
- Browse and book authentic local experiences  
- Connect with certified local guides who share their passion and knowledge  
- Discover hidden gems and off-the-beaten-path attractions  
- Experience local culture, cuisine, and traditions firsthand  
- Create memorable travel experiences beyond standard tourist attractions
  
## ✨ Features

### Core Features
- **Onboarding Experience**: Interactive introduction to the app’s features and benefits  
- **User Authentication**: Secure login and registration system with profile management  
- **Experience Discovery**: Browse, search, and filter local experiences by category, location, price, and availability  
- **Interactive Maps**: View experience locations, get directions, and explore nearby attractions  
- **Media Gallery**: High-quality photos and videos showcasing experiences  
- **Profile Management**: User profiles with media uploads, preferences, and booking history  
- **Reviews System**: Rate and review experiences to help other travelers  
- **Credit Card Scanner**: Secure payment integration for booking experiences  

### User Journey
1. **Discover**: Browse through curated local experiences  
2. **Book**: Select dates, times, and number of participants  
3. **Experience**: Meet local guides and enjoy authentic experiences  
4. **Share**: Rate, review, and share experiences with others  

## 🛠️ Development Requirements

### Android Studio
- Android Studio Arctic Fox (2020.3.1) or newer  
- Gradle version 7.2.2  
- Build tools version 33.0.1  

### Android Requirements
- Minimum SDK: API 21 (Android 5.0)  
- Target SDK: API 34 (Android 14)  
- Compile SDK: 34  

### Required Permissions
```
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

## 📂 Project Structure
```
app/
├── src/main/
    ├── java/com/ysf/mslh/guideme/
    │   ├── activities/          # Activity classes
    │   ├── fragments/           # UI fragments
    │   ├── fragmentsExperience/ # Experience-related fragments
    │   ├── adapters/            # RecyclerView adapters
    │   ├── models/              # Data models
    │   ├── utils/               # Utility classes
    │   ├── costumUi/            # Custom UI components
    │   └── hiddenFragments/     # Additional UI fragments
    └── res/
        ├── layout/              # XML layouts
        ├── drawable/            # Images and icons
        └── values/              # App resources
```


## 📚 Dependencies

The Guide Me app uses a variety of libraries to implement its features:

- **UI Components**  
- **Image & Media Handling**  
- **Camera & Vision**  
- **Maps & Location**  
- **Networking**  
- **Payment Processing**  
- **Testing**

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox (2020.3.1) or newer  
- JDK 11 or higher  
- An Android device or emulator running Android 5.0 (API 21) or higher  

### Installation
1. Clone the repository  
2. Open the project in Android Studio  
3. Sync Gradle files  
    - Click on “Sync Project with Gradle Files” in Android Studio  
4. Build the project  
5. Run the app on an emulator or physical device  
    - Select “Run ‘app’” from the Run menu in Android Studio  
    - Choose your target device  

## 🧰 Technologies Used
- **Android SDK**: Core Android development framework  
- **Google Maps Integration**: For location-based features and directions  
- **Credit Card Scanner API**: For secure payment processing  
- **RecyclerView**: For efficient list displays  
- **ViewPager**: For smooth sliding views and onboarding  
- **Bottom Sheet Dialogs**: For interactive UI components  
- **CameraX**: For camera functionality and image capture  
- **ML Kit & Tesseract OCR**: For text recognition and scanning  
- **ExoPlayer**: For video playback of experiences  
- **Glide**: For efficient image loading and caching  
- **OkHttp**: For network requests and API communication  

## 🤝 Contributing

Contributions are welcome! If you’d like to contribute to Guide Me, please follow these steps:

1. Fork the repository  
2. Create a feature branch (`git checkout -b feature/amazing-feature`)  
3. Commit your changes (`git commit -m 'Add some amazing feature'`)  
4. Push to the branch (`git push origin feature/amazing-feature`)  
5. Open a Pull Request  

Please make sure to update tests as appropriate and adhere to the existing coding style.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Contact

**Youssef Mouslih** - [@youssefMouslih](https://github.com/youssefMouslih)  
Project Link: [https://github.com/youssefMouslih/guideme](https://github.com/youssefMouslih/guideme)
