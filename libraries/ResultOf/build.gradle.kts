plugins {
    id(libs.plugins.thierry.jvm.library.get().pluginId)
}

dependencies {
    implementation(libs.kotlinSerializationJson)
    implementation(libs.retrofit)

    testImplementation(libs.junit)
    testImplementation(libs.mockkJvm)
}
