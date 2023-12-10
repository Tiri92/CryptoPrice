plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = "thierry.bitcoin"
    compileSdk = 34

    defaultConfig {
        applicationId = "thierry.bitcoin"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
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