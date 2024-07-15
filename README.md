<div align="center">

<p><img src="https://github.com/ibrohimkhan/koin-meter/blob/main/metadata/en-US/images/gecko.png" width="200" /></p>

# KoinMeter

### Open Source cryptocurrency monitor

[![Android](https://img.shields.io/badge/Android-grey?logo=android&style=flat)](https://www.android.com/)
[![AndroidAPI](https://img.shields.io/badge/API-28%2B-brightgreen.svg?style=flat)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/coingecko-api:3.0.1-green.svg?logo=coingecko)](https://docs.coingecko.com/v3.0.1/reference/introduction)
[![Kotlin](https://img.shields.io/badge/kotlin-2.0.0-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Koin](https://img.shields.io/badge/koin-3.5.6-red.svg?logo=koin)](https://insert-koin.io)
[![JetpackCompose](https://img.shields.io/badge/Jetpack%20Compose-bom:2024.06.00-yellow)](https://developer.android.com/jetpack/compose)

</div>

---

This is a lightweight, fast and personal cryptocurrencies monitor powered by [**CoinGecko Public API**](https://docs.coingecko.com/v3.0.1/reference/introduction) for Android devices

## Features

- **Market:** the top 250 cryptocurrencies by market cup
- **Favorites:** track your favorite coins by adding them into favorite list
- **Settings:** choose your price change percentage period
- **Offline:** look at your favorite coins even when you are disconnected

<br>

<p float="left">
<img src="https://github.com/ibrohimkhan/koin-meter/blob/main/metadata/en-US/images/screenshots/1.jpg" width="23%" />
<img src="https://github.com/ibrohimkhan/koin-meter/blob/main/metadata/en-US/images/screenshots/2.jpg" width="23%" />
<img src="https://github.com/ibrohimkhan/koin-meter/blob/main/metadata/en-US/images/screenshots/3.jpg" width="23%" />
<img src="https://github.com/ibrohimkhan/koin-meter/blob/main/metadata/en-US/images/screenshots/4.jpg" width="23%" />
</p>


## Technical Details

- *Jetpack Compose UI*
- *Material Design 3*
- *Koin for DI*
- *Clean Architecture*
  - ***data*** - contains remote, local and prefrences DataSources, Repositories implementation, Room's entities for persistence and data adapters
  - ***domain*** - contains UseCases, models and Repositories interfaces
  - ***presentation*** - contains screens, composable components, navigation, ui states and ViewModels. *MVI* pattern is used to manage state and display data in UI
- *Unit Testing* of the logic with *MockK* and *Koin-Test*


### Upcoming Features:

- **Search coin**
- **Trending coins**

