plugins {
    alias(libs.plugins.kotlin.plugin.serialization)
    id(libs.plugins.thierry.android.library.get().pluginId)
    id(libs.plugins.thierry.hilt.android.get().pluginId)
}

android {
    namespace = "thierry.cryptoprice.coingeckorepository"
}

dependencies {
    implementation(project(":usecases:GetBitcoinPriceUseCase"))
    implementation(project(":usecases:GetBitcoinPriceUseCase"))

    //Hilt
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    //Kotlin Serialization
    implementation(libs.kotlinSerializationJson)
    implementation(libs.retrofit2ConverterKotlinSerialization)
    implementation(libs.okhttp)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidXTestJunit)
    androidTestImplementation(libs.androidXTestEspressoCore)
}