plugins {
    //alias(libs.plugins.android.application)
    //alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id("thierry.android.application")
}

android {
    namespace = "thierry.bitcoin"
}

dependencies {

    implementation(libs.androidXCoreKtx)
    implementation(libs.androidXLifecycleRuntimeKtx)
    implementation(libs.activityCompose)
    implementation(platform(libs.composeBom))

    implementation(libs.bundles.compose)

    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidXTestJunit)
    androidTestImplementation(libs.androidXTestEspressoCore)
    androidTestImplementation(libs.composeUiTestJunit4)
    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)


    //Hilt
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(mapOf("path" to ":usecases:GetBitcoinPriceUseCase")))
    implementation(project(mapOf("path" to ":repositories:CoinGeckoRepository")))

    implementation(libs.javaxInject)
}