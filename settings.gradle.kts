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
include(":app")
include(":repositories:CoinGeckoRepository")
include(":usecases:GetBitcoinPriceUseCase")
include(":libraries:ResultOf")
include(":features:BitcoinInfo")
