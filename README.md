# Weather Locator app for Android

Weather Locator is a project for Android that allows you to quickly connect common UI elements to [WeatherWorldOnline](https://api.worldweatheronline.com/premium/v1/).
Application will locally store last 10 recently checked city names. And can be handy to quick check those city for weather report.
Currently in weather details we are showing weather icon, current temperature, humidity and Sunrise, Sunset, maxTemp & minTemp of today.
After the project is synchronized, we're ready to start and get weather results.


## Table of contents

1. [Usage](#usage)
1. [Installation](#installation)
1. [TODO](#todo)

## Usage

This is a project created to locate weather data of any city based on City Name.
The weather details are fetched from https://api.worldweatheronline.com/premium/v1/
You can fork this project to update and add new UIs or modify this application freely.


## Installation

Directly pull this project from github repo. Build the project locally. And install it in your android application.
The minSdkVersion is 23 (Android M). So you can install it in Android M and above devices.
The application is tested in Android 7, 9 and Android 10 devices.

Below dependencies are used in this project:
```groovy
dependencies {
    // Kotlin dependency
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61"

    // Picasso library
    implementation 'com.squareup.picasso:picasso:2.71828'

    // Dagger library for dependency injections
    implementation "com.google.dagger:dagger:2.24"
    kapt "com.google.dagger:dagger-compiler:2.21"

    // Networking api
    implementation "com.squareup.retrofit2:retrofit:2.4.0"
    implementation "com.squareup.retrofit2:converter-gson:2.4.0"

    // OK http Logging library
    implementation "com.squareup.okhttp3:okhttp:3.12.8"
    implementation "com.squareup.okhttp3:logging-interceptor:3.11.0"

    // Rx Java for async calls
    implementation "io.reactivex.rxjava2:rxjava:2.2.7"
    implementation "io.reactivex.rxjava2:rxandroid:2.1.1"

    // Mockito library for testings
    testImplementation 'org.mockito:mockito-core:2.19.0'
    testImplementation "androidx.arch.core:core-testing:2.1.0"

}
```


## TODO
    Support device rotation.
    Add UI tests for used Fragments
    Update City details page to show many more items from weather report.

