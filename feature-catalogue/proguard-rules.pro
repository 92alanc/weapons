# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn com.alancamargo.weapons.common.R$string
-dontwarn com.alancamargo.weapons.common.ui.UiCalibre
-dontwarn com.alancamargo.weapons.common.ui.UiCountry
-dontwarn com.alancamargo.weapons.common.ui.UiMake
-dontwarn com.alancamargo.weapons.common.ui.UiWeapon
-dontwarn com.alancamargo.weapons.common.ui.UiWeaponListHeader
-dontwarn com.alancamargo.weapons.common.ui.UiWeaponQuery
-dontwarn com.alancamargo.weapons.common.ui.UiWeaponType
-dontwarn com.alancamargo.weapons.common.ui.UiYear
-dontwarn com.alancamargo.weapons.common.ui.model.UiCountryName
-dontwarn com.alancamargo.weapons.core.ads.AdLoader
-dontwarn com.alancamargo.weapons.core.design.view.BottomSheetHandleKt
-dontwarn com.alancamargo.weapons.core.design.view.ComposableAdViewKt
-dontwarn com.alancamargo.weapons.core.design.view.CustomFontTextKt
-dontwarn com.alancamargo.weapons.core.design.view.DotIndicatorsKt
-dontwarn com.alancamargo.weapons.core.extensions.ArgumentExtensionsKt
-dontwarn com.alancamargo.weapons.core.extensions.LifecycleOwnerExtensionsKt
-dontwarn com.alancamargo.weapons.core.resources.ResourcesHelper
