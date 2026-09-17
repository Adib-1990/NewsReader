pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    
    plugins {
        kotlin("multiplatform") version "2.0.21"
        id("com.android.library") version "8.5.2"
        id("com.android.application") version "8.5.2"
        id("org.jetbrains.compose") version "1.7.3"
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
    }
}

rootProject.name = "anews-kmp"

include(":shared")
include(":androidApp")
