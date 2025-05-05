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
-dontwarn com.alancamargo.weapons.core.design.R$string
-dontwarn com.alancamargo.weapons.common.ui.UiWeaponQuery$ByName
-dontwarn com.alancamargo.weapons.common.ui.UiWeaponQuery
-dontwarn com.alancamargo.weapons.core.ads.AdLoader
-dontwarn com.alancamargo.weapons.core.consent.UserConsentManager
-dontwarn com.alancamargo.weapons.core.design.dialogue.DialogueHelper$DefaultImpls
-dontwarn com.alancamargo.weapons.core.design.dialogue.DialogueHelper
-dontwarn com.alancamargo.weapons.core.design.view.ComposableAdViewKt
-dontwarn com.alancamargo.weapons.core.design.view.CustomFontTextKt
-dontwarn com.alancamargo.weapons.core.design.view.CustomTextFieldKt
-dontwarn com.alancamargo.weapons.core.design.view.GenericDropdownMenuKt
-dontwarn com.alancamargo.weapons.core.extensions.LifecycleOwnerExtensionsKt
-dontwarn com.alancamargo.weapons.navigation.WeaponListActivityNavigation
-dontwarn com.alancamargo.weapons.navigation.WebViewActivityNavigation
