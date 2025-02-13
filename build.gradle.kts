// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        // other repositories
        mavenCentral()
    }
    dependencies {
        // other dependencies
        classpath ("com.facebook.android:facebook-android-sdk:[8,9)")
    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.2" apply false
}