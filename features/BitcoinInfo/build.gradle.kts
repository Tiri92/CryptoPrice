plugins {
    id(libs.plugins.thierry.android.library.get().pluginId)
    id(libs.plugins.thierry.hilt.android.get().pluginId)
    id(libs.plugins.thierry.kotlin.parcelize.get().pluginId)
}

android {
    namespace = "thierry.cryptoprice.bitcoininfo"

    //Compose Region //TODO Create custom Plugin for compose ?
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    //End Region
}

//TODO Clean dependencies
dependencies {

    implementation(libs.androidXCoreKtx)
    implementation(libs.androidXLifecycleRuntimeKtx)
    implementation(libs.androidXLifecycleRuntimeCompose)
    implementation(libs.activityCompose)
    implementation(platform(libs.composeBom))

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