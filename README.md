# Speedo Transfer

![Min SDK](https://img.shields.io/badge/Min%20SDK-26-brightgreen?style=flat-square)
![Language](https://img.shields.io/badge/Language-Kotlin-purple?style=flat-square&logo=kotlin)
![UI](https://img.shields.io/badge/UI-Jetpack%20Compose-blue?style=flat-square)
![License](https://img.shields.io/badge/License-All%20Rights%20Reserved-red?style=flat-square)

A **mobile banking-style UI** built in Jetpack Compose — transfers, cards, profile, and auth flows. Screens and navigation are fully functional; treat anything "banking" as **demo UX**, not a production financial product.

---

## Features

- **Auth flow** — Sign up and sign in with form validation
- **Home dashboard** — Animated card widget (front/back flip), live balance, recent transactions
- **Transfer flow** — Multi-step payment: pick recipient, enter amount, confirm, success
- **My Cards** — View linked accounts, add a new card with camera scanning via Blinkcard (Microblink)
- **Transaction history** — Scrollable list of past transfers
- **More / Profile** — Favourites, personal info, settings, change password, edit profile with country picker
- **Connectivity-aware navigation** — Handles offline state gracefully
- **Camera integration** — Runtime permission gate, Blinkcard UX SDK for card scanning

---
### Full App Walkthrough


https://github.com/user-attachments/assets/725b9933-1901-4dcd-a039-4c4245f15c13



---

## 📸 Screenshots

| Sign In | Sign Up | Home |
|:---:|:---:|:---:|
| ![Screenshot_20260331-072720_bm_app](https://github.com/user-attachments/assets/d34111b5-56bd-47e5-8a5d-e1096b94b995) | ![Screenshot_20260331-072724_bm_app](https://github.com/user-attachments/assets/4122b321-072d-41b8-b27d-c23509c280ea) | ![Screenshot_20260329-171742_bm_app](https://github.com/user-attachments/assets/b773f3b6-5a6c-4e65-ad98-0ae7d99cfc4b) |
| Card Flip (Back) | My Cards | Add Card |
|:---:|:---:|:---:|
|![Screenshot_20260329-171753_bm_app](https://github.com/user-attachments/assets/5621e970-97b6-447d-995b-cb30379aa5a7) | ![Screenshot_20260329-171905_bm_app](https://github.com/user-attachments/assets/6cdba169-b83d-4f9b-85b2-c38533d44c9e) | ![Screenshot_20260329-171933_bm_app](https://github.com/user-attachments/assets/ebd67a51-b7c9-479e-a870-acbfc29bf6a8) |
| Card Scanner (Blinkcard) | Profile | Settings |
|:---:|:---:|
| ![Screenshot_20260331-071951_bm_app](https://github.com/user-attachments/assets/5433f702-c541-4785-b20d-86d973a53c49) | ![Screenshot_20260331-072615_bm_app](https://github.com/user-attachments/assets/04dc9d2f-4798-4ee6-8808-6d7b37b06031) | ![Screenshot_20260331-072542_bm_app](https://github.com/user-attachments/assets/6343af2e-7c99-42bb-aef9-e1240ad71985) |


> 🎬 **Video walkthrough:** *(add your YouTube / unlisted link here)*

---

## Tech Stack

| Area | Stack |
|------|--------|
| UI | Jetpack Compose, Material 3 |
| Navigation | Navigation Compose |
| Async | Kotlin Coroutines, `lifecycle-viewmodel-ktx`, `lifecycle-runtime-compose` |
| Networking | Retrofit, Gson, OkHttp logging |
| Camera / cards | Blinkcard UX SDK (Microblink) |

- **Min SDK:** 26
- **Target / compile SDK:** see `app/build.gradle.kts`

---

## Project Structure

```
├── MainActivity              # Entry point, edge-to-edge, camera permission gate
├── approutes/                # NavHost, route names, connectivity-driven navigation
├── transfer/                 # Transfer flow and confirmation screens
├── mycard/                   # Cards list, add card, OTP screens, camera helpers
├── profile/                  # Profile, settings, change password, edit profile
├── signinscreen/             # Sign in
├── SignUp_Screen/            # Sign up
├── transaction/              # Transaction history and success screens
├── more/                     # More tab and favourites
└── viewModel/                # AddCard and SignUp ViewModels
```

---

## Running Locally

1. **Clone** the repo and open in **Android Studio** (recent stable).
2. Let Gradle **sync**; install any SDK platforms it prompts for.
3. **Run** on an emulator or physical device (**API 26+**).

### Blinkcard / Microblink

The app depends on the Blinkcard SDK. If Gradle can't resolve it or the scanner won't initialise, you'll need the Maven credentials and licence key from your Microblink account — these can't be shipped in the repo. Check [Microblink's docs](https://github.com/BlinkCard/blinkcard-android) when you fork this.

---

## Permissions

| Permission | Purpose |
|------------|---------|
| `INTERNET`, `ACCESS_NETWORK_STATE` | API calls and connectivity checks |
| `CAMERA` | Card scanning via Blinkcard |
| `POST_NOTIFICATIONS` | Notifications on supported API levels |

Camera is declared as **optional hardware** (`required="false"`) so the app installs on devices without a rear camera.

---

## Disclaimer

This project is for demonstrating **UI patterns, navigation architecture, and Android development** — not audited payments or real banking security. Do not enter real card details or secrets in debug builds you share publicly.

---



Thanks for stopping by. If something breaks on your machine, start with Gradle/SDK sync issues or Blinkcard setup — that covers 90% of cases.
