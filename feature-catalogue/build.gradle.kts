plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelise)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.alancamargo.weapons.catalogue"
    compileSdk = Config.Build.TARGET_SDK

    defaultConfig {
        minSdk = Config.Build.MIN_SDK

        testInstrumentationRunner = "com.alancamargo.weapons.core.test.runner.InstrumentedTestRunner"
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

    buildFeatures {
        viewBinding = Config.Build.ENABLE_VIEW_BINDING
    }

    sourceSets {
        getByName("main") {
            assets.setSrcDirs(listOf("src/main/assets", "src/test/assets"))
        }
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        animationsDisabled = Config.Testing.DISABLE_ANIMATIONS
        execution = Config.Testing.ANDROID_TEST_ORCHESTRATOR
    }
}

dependencies {
    implementation(project(Config.Modules.COMMON))
    implementation(project(Config.Modules.CORE))
    implementation(project(Config.Modules.CORE_DESIGN))
    implementation(project(Config.Modules.NAVIGATION))

    implementation(libs.android.activity)
    implementation(libs.android.appcompat)
    implementation(libs.android.core)
    implementation(libs.android.fragment)
    implementation(libs.android.material)
    implementation(libs.coil)
    implementation(libs.google.ads)
    implementation(libs.hilt.android)
    implementation(libs.room.ktx)

    kapt(libs.hilt.compiler)
    ksp(libs.room.compiler)

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
