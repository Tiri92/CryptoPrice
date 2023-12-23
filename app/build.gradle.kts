plugins {
    id(libs.plugins.thierry.android.application.get().pluginId)
    id(libs.plugins.thierry.hilt.android.get().pluginId)
    id(libs.plugins.thierry.kotlin.parcelize.get().pluginId)
}

android {
    namespace = "thierry.cryptoprice"
}

//TODO Clean dependencies
dependencies {
    implementation(libs.androidXCoreKtx)
    implementation(libs.androidXHiltNavigationCompose)
    implementation(libs.androidXLifecycleRuntimeKtx)
    implementation(libs.androidXLifecycleRuntimeCompose)
    implementation(libs.activityCompose)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.javaxInject)

    implementation(libs.bundles.compose)
    implementation(platform(libs.composeBom))

    implementation(projects.repositories.coinGeckoRepository)
    implementation(projects.features.bitcoinInfo)

    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidXTestJunit)
    androidTestImplementation(libs.androidXTestEspressoCore)
    androidTestImplementation(libs.composeUiTestJunit4)
}