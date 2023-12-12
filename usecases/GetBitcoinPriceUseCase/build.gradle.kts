plugins {
    id(libs.plugins.thierry.jvm.library.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.java.library.get().pluginId)
}

dependencies {
    implementation(libs.javaxInject)
}