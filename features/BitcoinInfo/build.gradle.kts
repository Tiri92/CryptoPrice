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
    implementation(libs.androidXHiltNavigationCompose)
    implementation(libs.androidXLifecycleRuntimeKtx)
    implementation(libs.androidXLifecycleRuntimeCompose)
    implementation(libs.activityCompose)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.javaxInject)
    implementation(libs.kotlinReflect)

    implementation(libs.bundles.compose)

    implementation(projects.usecases.getBitcoinPriceUseCase)
    implementation(projects.usecases.preferredCurrencyUseCase)
    implementation(projects.libraries.resultOf)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinTest)
    testImplementation(libs.kotlinXCoroutinesTestJvm)
    testImplementation(libs.turbine)
}