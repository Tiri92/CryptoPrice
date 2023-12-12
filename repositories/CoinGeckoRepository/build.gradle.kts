plugins {
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.plugin.serialization)
    id(libs.plugins.thierry.android.library.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = "thierry.bitcoin.coingeckorepository"
}

dependencies {

    implementation(libs.androidXCoreKtx)
    implementation(libs.androidXAppCompat)
    implementation(libs.materialDeisgn)
    implementation(project(":usecases:GetBitcoinPriceUseCase"))
    implementation(project(":usecases:GetBitcoinPriceUseCase"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidXTestJunit)
    androidTestImplementation(libs.androidXTestEspressoCore)

    //Hilt
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    //Kotlin Serialization
    implementation(libs.kotlinSerializationJson)
    implementation(libs.retrofit2ConverterKotlinSerialization)
    implementation(libs.okhttp)
}