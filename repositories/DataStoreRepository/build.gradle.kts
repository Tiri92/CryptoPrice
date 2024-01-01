plugins {
    id(libs.plugins.thierry.android.library.get().pluginId)
    id(libs.plugins.thierry.hilt.android.get().pluginId)
}

android {
    namespace = "thierry.cryptoprice.datastorerepository"
}

dependencies {
    implementation(libs.androidXCoreKtx)
    implementation(libs.dataStore)
    implementation(libs.javaxInject)

    implementation(projects.usecases.preferredCurrencyUseCase)

    testImplementation(libs.junit)
}