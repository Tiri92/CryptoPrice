plugins {
    id(libs.plugins.thierry.jvm.library.get().pluginId)
}

dependencies {
    implementation(project(":libraries:ResultOf"))

    implementation(libs.javaxInject)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinTest)
    testImplementation(libs.kotlinXCouroutinesTestJvm)
}