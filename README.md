# TranslateTest 🌐

A modern Android application for offline Hindi to Tamil text translation powered by Google ML Kit.

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org)

## ✨ Features

- **On-Device Translation** - Neural machine translation without constant internet connectivity
- **Dynamic Model Management** - Automatic download and caching of language models (~30MB)
- **Real-time Progress** - Visual feedback during model download with animated indicators
- **Modern UI** - Edge-to-edge display support with Material Design
- **Lifecycle-Aware** - Proper resource management with automatic cleanup

## 📱 Screenshots

<!-- Add your screenshots here -->
<!-- ![Home Screen](screenshots/home.png) -->
<!-- ![Translation Result](screenshots/result.png) -->

## 🔧 Prerequisites

- Android Studio Hedgehog or later
- Android SDK 23+ (Android 6.0 Marshmallow)
- Internet connection for initial model download

## 🚀 Getting Started

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/TranslateTest.git
   cd TranslateTest
   ```

2. **Open in Android Studio**
   - File → Open → Select the project directory

3. **Build and Run**
   - Connect an Android device or start an emulator
   - Click **Run** ▶️ or press `Shift + F10`

### First Launch

On first launch, the app will automatically download the Hindi-Tamil translation model. This requires an internet connection and may take a moment depending on your network speed.

## 💻 Usage

1. Enter Hindi text in the input field
2. Tap the **Translate** button
3. View the Tamil translation in the output area

## 🛠️ Tech Stack

| Component | Technology |
|-----------|------------|
| Language | Kotlin |
| UI Framework | Android Views (XML) |
| ML Translation | [Google ML Kit](https://developers.google.com/ml-kit/language/translation) |
| Async Operations | Kotlin Coroutines |
| Architecture | Lifecycle-aware components |

## 📁 Project Structure

```
app/
├── src/main/
│   ├── java/com/terrystudios/translatetest/
│   │   └── MainActivity.kt          # Main translation logic
│   └── res/
│       └── layout/
│           └── activity_main.xml    # UI layout
└── build.gradle.kts                  # App dependencies
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the **GNU General Public License v3.0** - see the [LICENSE](LICENSE) file for details.

```
TranslateTest - Hindi to Tamil Translation App
Copyright (C) 2024 Terry Studios

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
```

## 🙏 Acknowledgments

- [Google ML Kit](https://developers.google.com/ml-kit) for the translation API
- [Android Developers](https://developer.android.com) for documentation and tools

---

<p align="center">Made with ❤️ by Terry Studios</p>
