# radiomaster-t8l-android-configurator
Radiomaster T8L android configurator via usb
# RadioMaster T8L Android Configurator 🛠️📱

An open-source Android application designed to configure the **RadioMaster T8L** transmitter directly via a USB-C OTG cable without relying on WebSerial or Chrome browser limitations.

Brought to you by **Rotorismo**.

## 🚀 Features (Planned)
- **Direct USB-Serial Communication:** Bypasses WebSerial restrictions on Android (such as on Google Pixel devices).
- **Wi-Fi Toggle:** Easily trigger `Open WiFi` and `Close WiFi` modes on the internal ExpressLRS module.
- **Switch Configuration:** Map hardware switches (SA, SB, SC, SD, SE) to 2-POS, 3-POS, or Click modes.
- **Audio & Trims:** Adjust volume, S1 mid-tone, and reset trim values.
- **100% Offline:** Fully functional on the field without an active internet connection.

## 🛠️ Architecture & Tech Stack
- **Language:** Kotlin
- **UI:** Jetpack Compose
- **USB Driver:** [usb-serial-for-android](https://github.com/mik3y/usb-serial-for-android)
- **Target OS:** Android 8.0+ (API 26+)

## 🤝 Contributing
Contributions, issues, and feature requests are welcome! Feel free to check the issues page or submit a Pull Request.

## 📄 License
[MIT](LICENSE)
