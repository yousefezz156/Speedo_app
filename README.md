# BM App

An Android app I’m building as a **mobile banking-style UI** — transfers, cards, profile, and auth flows — mostly in **Jetpack Compose**. It’s a learning/portfolio project: the screens and navigation are real, but treat anything “banking” as **demo UX**, not a production financial product.

---

## What it does (high level)

You can move through a **sign up / sign in** flow, a **home / transfer** area (pick amount, confirm, payment-style steps), **transaction history** and success screens, **My Cards** (currency, add card, OTP-style verification), a **More** section with favourites, and **profile** bits (info, settings, change password, edit profile with country list).

There’s also **camera** integration for card-related flows: **CameraX**, **ML Kit text recognition**, and **Blinkcard** (Microblink) for scanning-style UX. The app asks for **camera** permission at runtime where needed, plus **network** access for API-style wiring.

---

## Tech I’m using

| Area | Stack |
|------|--------|
| UI | Jetpack Compose, Material 3 |
| Navigation | Navigation Compose |
| Async | Kotlin Coroutines, `lifecycle-viewmodel-ktx`, `lifecycle-runtime-compose` |
| Networking | Retrofit, Gson, OkHttp logging |
| Camera / cards | CameraX, ML Kit Text Recognition, Blinkcard UX SDK |

- **Min SDK:** 26  
- **Target / compile:** aligned with the project (see `app/build.gradle.kts`)

---

## Project structure (rough map)

- `MainActivity` — entry, edge-to-edge, camera permission gate for CameraX  
- `approutes/` — `NavHost`, route names, connectivity-driven navigation bits  
- `transfer/` — main transfer flow and confirmations  
- `mycard/` — cards, OTP-style screens, camera helpers  
- `profile/`, `signinscreen/`, `SignUp_Screen/`, `transaction/`, `more/` — the rest of the flows  
- `viewModel/` — e.g. add-card and sign-up view models  

---

## Running it locally

1. **Clone** the repo and open the folder in **Android Studio** (recent stable version is fine).  
2. Let Gradle **sync**; install any SDK platforms it asks for.  
3. **Run** on an emulator or device (**API 26+**).

### Blinkcard / Microblink

The app depends on **Blinkcard**. If Gradle can’t resolve it or the scanner doesn’t initialise, you’ll need whatever **credentials / Maven repo / licence** Microblink expects for your account. I can’t ship those in the repo — check their docs when you fork this.

---

## Permissions

Declared in the manifest (summarised):

- `INTERNET`, `ACCESS_NETWORK_STATE` — connectivity and API calls  
- `CAMERA` — camera and card capture flows  
- `POST_NOTIFICATIONS` — notifications (where used on supported API levels)  

Camera is **optional hardware** (`required="false"`) so the app can still install on devices without a back camera.

---

## Honest disclaimer

This repo is for **UI, navigation, and Android patterns** — not audited payments or real bank security. Don’t put real card data or secrets in debug builds you share.

---

## Licence

Unless you add a `LICENSE` file yourself, **all rights reserved** by default. If you open-source this later, drop a licence in the root and update this section.

---

Thanks for stopping by — if something’s broken on your machine, it’s usually Gradle/SDK or Blinkcard setup first, then sync again.
