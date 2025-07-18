// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.androidksp) apply false
    kotlin("jvm") version "2.1.21" apply false
    kotlin("plugin.serialization") version "2.1.21" apply false
}