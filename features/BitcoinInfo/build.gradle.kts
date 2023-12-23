plugins {
    id(libs.plugins.thierry.android.library.get().pluginId)
    id(libs.plugins.thierry.hilt.android.get().pluginId)
    id(libs.plugins.thierry.kotlin.parcelize.get().pluginId)
    id(libs.plugins.thierry.compose.get().pluginId)
}

android {
    namespace = "thierry.cryptoprice.bitcoininfo"
}

//TODO Clean dependencies
dependencies {

    implementation(libs.androidXCoreKtx)
    implementation(libs.androidXLifecycleRuntimeKtx)
    implementation(libs.androidXLifecycleRuntimeCompose)
    implementation(libs.activityCompose)

    implementation(libs.bundles.compose)

    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)

    implementation(libs.androidXAppCompat)
    implementation(libs.materialDeisgn)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidXTestJunit)
    androidTestImplementation(libs.androidXTestEspressoCore)

    implementation(libs.androidXHiltNavigationCompose)

    implementation(libs.javaxInject)

    implementation(project(":usecases:GetBitcoinPriceUseCase"))
    implementation(project(":libraries:ResultOf"))
}