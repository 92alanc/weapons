plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.alancamargo.weapons"
    compileSdk = Config.Build.TARGET_SDK

    defaultConfig {
        applicationId = "com.alancamargo.weapons"
        minSdk = Config.Build.MIN_SDK
        targetSdk = Config.Build.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            keyAlias = properties["weapons_key_alias"] as String
            keyPassword = properties["weapons_key_password"] as String
            storeFile = file(path = properties["weapons_store_file"] as String)
            storePassword = properties["weapons_store_password"] as String
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }

        release {
            isDebuggable = false
            isMinifyEnabled = Config.Build.ENABLE_MINIFY
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        viewBinding = Config.Build.ENABLE_VIEW_BINDING
    }

    flavorDimensions += Config.Build.FLAVOUR_DIMENSION

    productFlavors {
        create(Config.WW1.FLAVOUR_NAME) {
            dimension = Config.Build.FLAVOUR_DIMENSION
            applicationIdSuffix = Config.WW1.SUFFIX
            versionCode = Config.WW1.VERSION_CODE
            versionName = Config.WW2.VERSION_NAME
        }

        create(Config.WW2.FLAVOUR_NAME) {
            dimension = Config.Build.FLAVOUR_DIMENSION
            applicationIdSuffix = Config.WW2.SUFFIX
            versionCode = Config.WW2.VERSION_CODE
            versionName = Config.WW2.VERSION_NAME
        }

        create(Config.Korea.FLAVOUR_NAME) {
            dimension = Config.Build.FLAVOUR_DIMENSION
            applicationIdSuffix = Config.Korea.SUFFIX
            versionCode = Config.Korea.VERSION_CODE
            versionName = Config.Korea.VERSION_NAME
        }

        create(Config.Vietnam.FLAVOUR_NAME) {
            dimension = Config.Build.FLAVOUR_DIMENSION
            applicationIdSuffix = Config.Vietnam.SUFFIX
            versionCode = Config.Vietnam.VERSION_CODE
            versionName = Config.Vietnam.VERSION_NAME
        }
    }

    compileOptions {
        sourceCompatibility = Config.Build.javaVersion
        targetCompatibility = Config.Build.javaVersion
    }

    packaging {
        resources.excludes.add(Config.Build.META_INF_DIR)
    }

    kotlinOptions {
        jvmTarget = Config.Build.javaVersionString
    }

    kotlin {
        jvmToolchain(Config.Build.javaVersionInt)
    }
}

dependencies {
    implementation(project(Config.Modules.CORE_DESIGN))
    implementation(project(Config.Modules.FEATURE_HOME))
    implementation(project(Config.Modules.FEATURE_CATALOGUE))
    implementation(project(Config.Modules.FEATURE_WEB_VIEW))

    implementation(libs.android.core)
    implementation(libs.hilt.android)

    kapt(libs.hilt.compiler)
}