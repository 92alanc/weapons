buildscript {
    dependencies {
        classpath(libs.r8)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.compose.compiler) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.parcelise) apply false
    alias(libs.plugins.ksp) apply false
}
