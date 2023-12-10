plugins {
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.java.library.get().pluginId)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.javaxInject)
}