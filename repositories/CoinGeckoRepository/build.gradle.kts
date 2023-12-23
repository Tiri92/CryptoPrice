plugins {
    alias(libs.plugins.kotlin.plugin.serialization)
    id(libs.plugins.thierry.jvm.library.get().pluginId)
    id(libs.plugins.thierry.hilt.jvm.get().pluginId)
}

dependencies {
    implementation(project(":usecases:GetBitcoinPriceUseCase"))
    implementation(project(":libraries:ResultOf"))

    //Kotlin Serialization
    implementation(libs.kotlinSerializationJson)
    implementation(libs.retrofit2ConverterKotlinSerialization)
    implementation(libs.okhttp)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinTest)
    testImplementation(libs.kotlinXCouroutinesTestJvm)
    testImplementation(libs.mockkJvm)
}