import org.jetbrains.kotlin.cli.jvm.main

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
    compileSdk = 34

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "com.alancamargo.weapons.core.test.runner.InstrumentedTestRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
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

    packaging {
        resources.excludes.add("META-INF/*")
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion
    }

    buildFeatures {
        viewBinding = true
    }

    sourceSets {
        getByName("main") {
            assets.setSrcDirs(listOf("src/main/assets", "src/test/assets"))
        }
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        animationsDisabled = true
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":core"))
    implementation(project(":core-design"))
    implementation(project(":navigation"))

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

    androidTestImplementation(project(":core-test"))

    androidTestImplementation(libs.android.espresso.core)
    androidTestImplementation(libs.mockk.android)

    androidTestUtil(libs.android.test.orchestrator)

    kaptAndroidTest(libs.hilt.compiler)
}
