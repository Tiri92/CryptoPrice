plugins {
    id(libs.plugins.thierry.jvm.library.get().pluginId)
}

dependencies {
    implementation(libs.javaxInject)

    implementation(projects.libraries.resultOf)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinTest)
    testImplementation(libs.kotlinXCoroutinesTestJvm)
}