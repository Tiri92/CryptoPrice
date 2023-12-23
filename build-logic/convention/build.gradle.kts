import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "thierry.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "thierry.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("jvmLibrary") {
            id = "thierry.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("hiltAndroid") {
            id = "thierry.hilt.android"
            implementationClass = "HiltAndroidConventionPlugin"
        }
        register("hiltJvm") {
            id = "thierry.hilt.jvm"
            implementationClass = "HiltJvmConventionPlugin"
        }
        register("kotlinParcelize") {
            id = "thierry.kotlin.parcelize"
            implementationClass = "KotlinParcelizeConventionPlugin"
        }
        register("compose") {
            id = "thierry.compose"
            implementationClass = "ComposeConventionPlugin"
        }
    }
}