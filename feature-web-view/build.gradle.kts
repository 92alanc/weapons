plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelise)
}

android {
    namespace = "com.alancamargo.weapons.webview"
    compileSdk = Config.Build.TARGET_SDK

    defaultConfig {
        minSdk = Config.Build.MIN_SDK

        testInstrumentationRunner = Config.Testing.CUSTOM_TEST_RUNNER
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
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

        create(Config.Korea.FLAVOUR_NAME) {
            dimension = Config.Build.FLAVOUR_DIMENSION
        }

        create(Config.Vietnam.FLAVOUR_NAME) {
            dimension = Config.Build.FLAVOUR_DIMENSION
        }
    }

    packaging {
        resources.excludes.add(Config.Build.META_INF_DIR)
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
        viewBinding = Config.Build.ENABLE_VIEW_BINDING
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        animationsDisabled = Config.Testing.DISABLE_ANIMATIONS
        execution = Config.Testing.ANDROID_TEST_ORCHESTRATOR
    }
}

dependencies {
    implementation(project(Config.Modules.CORE))
    implementation(project(Config.Modules.NAVIGATION))

    implementation(libs.android.appcompat)
    implementation(libs.android.activity)
    implementation(libs.android.material)
    implementation(libs.google.ads)
    implementation(libs.hilt.android)

    kapt(libs.hilt.compiler)

    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.mockk.android)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)

    androidTestImplementation(project(Config.Modules.CORE_TEST))

    androidTestImplementation(libs.android.espresso.core)
    androidTestImplementation(libs.mockk.android)

    androidTestUtil(libs.android.test.orchestrator)

    kaptAndroidTest(libs.hilt.compiler)
}
