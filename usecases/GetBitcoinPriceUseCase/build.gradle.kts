plugins {
    id(libs.plugins.thierry.jvm.library.get().pluginId)
}

dependencies {
    implementation(libs.javaxInject)
    testImplementation(libs.junit)
    implementation(project(":libraries:ResultOf"))
}