@file:Suppress(
    "USELESS_CAST",
    "UNRESOLVED_REFERENCE"
)

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

import java.util.Properties
import java.io.FileInputStream

val keystorePropertiesFile: File = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "com.alancamargo.weapons"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.alancamargo.weapons"
        minSdk = 23
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    flavorDimensions += "version"

    productFlavors {
        create("ww1") {
            dimension = "version"
            applicationIdSuffix = ".ww1"
            versionCode = 7
            versionName = "2023.3.3"
        }

        create("ww2") {
            dimension = "version"
            applicationIdSuffix = ".ww2"
            versionCode = 1
            versionName = "2023.4.0"
        }

        create("korea") {
            dimension = "version"
            applicationIdSuffix = ".korea"
            versionCode = 1
            versionName = "2023.4.0"
        }

        create("vietnam") {
            dimension = "version"
            applicationIdSuffix = ".vietnam"
            versionCode = 1
            versionName = "2023.4.0"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources.excludes.add("META-INF/*")
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion
    }

    kotlin {
        jvmToolchain(JavaVersion.VERSION_17.majorVersion.toInt())
    }
}

dependencies {
    implementation(project(":core-design"))
    implementation(project(":feature-home"))
    implementation(project(":feature-catalogue"))
    implementation(project(":feature-web-view"))

    implementation(libs.android.core)
    implementation(libs.google.ads)
    implementation(libs.hilt.android)

    kapt(libs.hilt.compiler)
}