plugins {
    id(libs.plugins.thierry.android.application.get().pluginId)
    id(libs.plugins.thierry.hilt.android.get().pluginId)
    id(libs.plugins.thierry.kotlin.parcelize.get().pluginId)
}

android {
    namespace = "thierry.cryptoprice"
}

dependencies {
    //TODO Clean dependencies here

    implementation(libs.androidXCoreKtx)
    implementation(libs.androidXLifecycleRuntimeKtx)
    implementation(libs.androidXLifecycleRuntimeCompose)
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

    implementation(libs.androidXHiltNavigationCompose)

    implementation(project(":repositories:CoinGeckoRepository"))
    implementation(project(":features:BitcoinInfo"))

    implementation(libs.javaxInject)
}