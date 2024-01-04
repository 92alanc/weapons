import org.gradle.api.JavaVersion

object Config {

    object Build {

        const val ENABLE_MINIFY = true
        const val ENABLE_VIEW_BINDING = true
        const val FLAVOUR_DIMENSION = "version"
        const val META_INF_DIR = "META-INF/*"
        const val MIN_SDK = 23
        const val TARGET_SDK = 34

        val javaVersion = JavaVersion.VERSION_17
        val javaVersionString = javaVersion.majorVersion
        val javaVersionInt = javaVersionString.toInt()
    }

    object Testing {

        const val ANDROID_TEST_ORCHESTRATOR = "ANDROIDX_TEST_ORCHESTRATOR"
        const val CUSTOM_TEST_RUNNER = "com.alancamargo.weapons.core.test.runner.InstrumentedTestRunner"
        const val DISABLE_ANIMATIONS = true
    }

    object Modules {

        const val COMMON = ":common"
        const val CORE = ":core"
        const val CORE_DESIGN = ":core-design"
        const val CORE_TEST = ":core-test"
        const val FEATURE_CATALOGUE = ":feature-catalogue"
        const val FEATURE_HOME = ":feature-home"
        const val FEATURE_WEB_VIEW = ":feature-web-view"
        const val NAVIGATION = ":navigation"
    }

    object WW1 {

        const val FLAVOUR_NAME = "ww1"
        const val SUFFIX = ".$FLAVOUR_NAME"
        const val VERSION_CODE = 10
        const val VERSION_NAME = "2024.1.0"
    }

    object WW2 {

        const val FLAVOUR_NAME = "ww2"
        const val SUFFIX = ".$FLAVOUR_NAME"
        const val VERSION_CODE = 1
        const val VERSION_NAME = "2023.4.0"
    }

    object Korea {

        const val FLAVOUR_NAME = "korea"
        const val SUFFIX = ".$FLAVOUR_NAME"
        const val VERSION_CODE = 1
        const val VERSION_NAME = "2023.4.0"
    }

    object Vietnam {

        const val FLAVOUR_NAME = "vietnam"
        const val SUFFIX = ".$FLAVOUR_NAME"
        const val VERSION_CODE = 1
        const val VERSION_NAME = "2023.4.0"
    }
}
