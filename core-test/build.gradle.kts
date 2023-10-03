plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.alancamargo.weapons.core.test"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "version"

    productFlavors {
        create("ww1") {
            dimension = "version"
        }

        create("ww2") {
            dimension = "version"
        }

        create("korea") {
            dimension = "version"
        }

        create("vietnam") {
            dimension = "version"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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

    implementation(libs.android.appcompat)
    implementation(libs.android.espresso.core)
    implementation(libs.android.material)
    implementation(libs.android.test.runner)
    implementation(libs.truth)

    api(libs.hilt.testing)
}