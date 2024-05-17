plugins {
    id(libs.plugins.thierry.jvm.library.get().pluginId)
}

dependencies {
    implementation(libs.javaxInject)
    implementation(libs.kotlinXCoroutinesCoreJvm)

    testImplementation(libs.kotlinTest)
    testImplementation(libs.kotlinXCoroutinesTestJvm)
    testImplementation(libs.turbine)
}