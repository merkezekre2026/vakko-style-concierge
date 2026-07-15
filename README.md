# Vakko Style Concierge

> A premium Android styling-assistant concept that turns occasions, wardrobe context, and concierge services into a focused mobile experience.

[![Android](https://img.shields.io/badge/Android-API%2026%2B-3DDC84?logo=android&logoColor=white)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/compose)
[![Status](https://img.shields.io/badge/status-concept%20prototype-C7A96B)](#project-status)

Vakko Style Concierge explores how a luxury fashion brand could bring personal styling, wardrobe intelligence, special occasions, and high-touch services together in one elegant Android experience.

The project is built as a native Jetpack Compose prototype and uses sample data, making it easy to explore the product vision without configuring a backend or API keys.

## Highlights

- **Occasion-based styling** — discover curated looks tailored to the event and context.
- **Vakko Moments** — follow a dedicated journey for celebrations and special occasions.
- **Virtual wardrobe** — connect pieces you already own with relevant new-season products.
- **Stylist appointments** — access the entry point for a personal styling consultation.
- **AR and premium services** — explore future-facing try-on and concierge touchpoints.
- **Accessible navigation** — move through the experience with a clear four-tab mobile structure.

## Product vision

The prototype brings together three parts of a premium styling journey:

1. **Inspiration** — help customers decide what to wear for a specific moment.
2. **Personalization** — use wardrobe context to make recommendations more relevant.
3. **Service** — connect digital discovery with stylists, appointments, and premium experiences.

## Tech stack

| Area | Technology |
| --- | --- |
| Language | Kotlin 2.0.21 |
| UI | Jetpack Compose with Material 3 |
| Platform | Native Android |
| Build system | Gradle with Kotlin DSL |
| Compile / target SDK | Android 35 |
| Minimum SDK | Android 26 |
| Java runtime | JDK 17 |

## Getting started

### Prerequisites

- [Android Studio Ladybug](https://developer.android.com/studio) or newer
- JDK 17
- Android SDK 35
- An Android device or emulator running API 26 or newer

### Run the app

1. Clone the repository:

   ```bash
   git clone https://github.com/merkezekre2026/vakko-style-concierge.git
   cd vakko-style-concierge
   ```

2. Open the project in Android Studio.
3. Allow Gradle to sync and install any missing SDK components.
4. Select the `app` run configuration.
5. Choose a device or emulator and click **Run**.

No backend credentials or environment variables are required for the prototype.

## Project structure

```text
vakko-style-concierge/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/vakko/concierge/
│       └── res/
├── build.gradle.kts
└── settings.gradle.kts
```

The repository intentionally keeps the implementation lightweight: a single Android application module, Compose-based UI, and local sample content.

## Project status

This repository is a **UI concept prototype**, not a production application. It is intended to demonstrate the experience, interaction model, and visual direction.

A production implementation would require:

- secure integration with product catalog and inventory services;
- authenticated customer profiles and CRM data;
- persistent wardrobe and preference storage;
- live stylist availability and appointment booking;
- a selected AR provider and device capability handling;
- analytics, localization, accessibility testing, and privacy review;
- automated tests, monitoring, and release infrastructure.

## Contributing

Suggestions and focused pull requests are welcome. If you plan a significant change, open an issue first so the scope and direction can be discussed.

When contributing, please keep the prototype's premium, accessible, and mobile-first experience consistent.

## Disclaimer

This repository is a design and engineering concept created for demonstration purposes. It is not a production commerce application. Vakko and related names or marks belong to their respective owners.

## License

No open-source license is currently included in this repository. Please contact the repository owner before reusing or distributing the code.
