plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.alancamargo.weapons.core.test"
    compileSdk = Config.Build.TARGET_SDK

    defaultConfig {
        minSdk = Config.Build.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.Build.ENABLE_MINIFY
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += Config.Build.FLAVOUR_DIMENSION

    productFlavors {
        create(Config.WW1.FLAVOUR_NAME) {
            dimension = Config.Build.FLAVOUR_DIMENSION
        }

        create(Config.WW2.FLAVOUR_NAME) {
            dimension = Config.Build.FLAVOUR_DIMENSION
        }

        create(Config.Vietnam.FLAVOUR_NAME) {
            dimension = Config.Build.FLAVOUR_DIMENSION
        }
    }

    compileOptions {
        sourceCompatibility = Config.Build.javaVersion
        targetCompatibility = Config.Build.javaVersion
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

    implementation(libs.android.appcompat)
    implementation(libs.android.espresso.core)
    implementation(libs.android.material)
    implementation(libs.android.test.runner)
    implementation(libs.truth)

    api(libs.hilt.testing)
}