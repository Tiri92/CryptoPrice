pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CryptoPrice"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":repositories:CoinGeckoRepository")
include(":repositories:DataStoreRepository")
include(":usecases:GetBitcoinPriceUseCase")
include(":usecases:UserPreferences")
include(":libraries:ResultOf")
include(":features:BitcoinInfo")
