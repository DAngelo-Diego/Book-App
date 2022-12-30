package com.example.bookapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFBF9F7)
val DarkGray = Color(0xFF1D1D27)

val titleOnBoardingDay = Color(0xFF70655D)
val titleOnBoardingNight = Color(0xFFF9A826)

val descOnBoardingDay = Color(0xFF7B7875)
val descOnBoardingNight = Color(0xFFD7D7D7)

val indicatorActiveDay = Color(0xFF997B66)
val indicatorActiveNight = Color(0xFFF9A826)

val indicatorInactiveDay = Color(0xFFD4D4D4)
val indicatorInactiveNight = Color(0xFFD9D9D9)

val buttonOnBoardingDay = Color(0xFF997B66)
val buttonOnBoardingNight = Color(0xFFF9A826)

val buttonOnBoardingDayText = Color(0xFFFBF9F7)
val buttonOnBoardingNightText = Color(0xFF1D1D27)


val Colors.welcomeScreenBackgroundColor
    get() = if (isLight) LightGray else DarkGray

val Colors.titleColor
    get() = if (isLight) titleOnBoardingDay else titleOnBoardingNight

val Colors.descriptionColor
    get() = if (isLight) descOnBoardingDay else descOnBoardingNight


val Colors.activeIndicatorColor
    get() = if (isLight) indicatorActiveDay else indicatorActiveNight

val Colors.inactiveIndicatorColor
    get() = if (isLight) indicatorInactiveDay else indicatorInactiveNight

val Colors.buttonBackgroundColor
    get() = if (isLight) buttonOnBoardingDay else buttonOnBoardingNight

val Colors.contentBtnText
get() = if (isLight) buttonOnBoardingDayText else buttonOnBoardingNightText