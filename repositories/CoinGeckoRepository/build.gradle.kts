plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "thierry.bitcoin.coingeckorepository"
    compileSdk = 33

    defaultConfig {
        // applicationId = "thierry.bitcoin.coingeckorepository"
        minSdk = 24
        targetSdk = 33
        //
        //versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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