# BasisTask
This app is a Task given by Basis(https://getbasis.co/)

# Installation
Clone this repository and import into Android Studio

```   https://github.com/cybertronjc/BasisTask.git   ```


# Android Configuration

Android studio version : 3.4.2
```
minSdkVersion 21
targetSdkVersion 28
```

project configured with AndroidX

# Libraries used

* Volley : A library that make networking for Android apps easier and most importantly, faster.
* Gson : A Java library  that is used to convert a JSON string to an equivalent Java object.

# App Overview
App consist one activity which has a Linear layout on top that shows Current item position (Card track), progress indicator and a restart button. It also contains a ViewPager for showing swipable cards.

Swiping up : Next Card
Swiping down : Previous Card

OnBackPressed() : When user press back button the app shows previous card and if user is seeing first card then it will finish the activity.


