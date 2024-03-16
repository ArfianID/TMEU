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
# Hilt
-keep,allowobfuscation,allowshrinking class dagger.*
 -keep,allowobfuscation,allowshrinking class * extends dagger.android.AndroidInjector
-keep,allowobfuscation,allowshrinking class * extends dagger.Component

# Hilt ViewModel
-keep,allowobfuscation,allowshrinking class * extends androidx.lifecycle.ViewModel

# Hilt LiveData
-keep,allowobfuscation,allowshrinking class * extends androidx.lifecycle.LiveData
# Hilt Room
-keep,allowobfuscation,allowshrinking class * extends androidx.room.*

# Hilt Navigation
-keep,allowobfuscation,allowshrinking class * extends androidx.navigation.*

# Hilt Fragment
-keep,allowobfuscation,allowshrinking class * extends androidx.fragment.app.Fragment

# Hilt Activity
-keep,allowobfuscation,allowshrinking class * extends androidx.activity.ComponentActivity

# Hilt Service
-keep,allowobfuscation,allowshrinking class * extends android.app.Service

# Hilt ContentProvider
-keep,allowobfuscation,allowshrinking class * extends android.content.ContentProvider

# MyApplication class
-keep,allowobfuscation,allowshrinking class com.arfian.tmeu.*