plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.alancamargo.weapons.core.design"
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

    buildFeatures {
        compose = Config.Build.ENABLE_COMPOSE
    }
}

dependencies {
    implementation(project(Config.Modules.CORE))

    implementation(libs.android.material)
    implementation(libs.android.compose.activity)
    implementation(platform(libs.android.compose.bom))
    implementation(libs.android.compose.material3)
    implementation(libs.android.compose.preview)
    implementation(libs.google.ads)
    implementation(libs.hilt.android)

    api(libs.android.splashscreen)

    ksp(libs.hilt.compiler)
}
