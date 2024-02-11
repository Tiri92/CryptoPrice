package thierry.cryptoprice.getbitcoinpriceusecase.model

data class MarketData(
    val currencyPrices: CurrencyPrices,
    val high24h: CurrencyPrices,
    val low24h: CurrencyPrices,
    val ath: CurrencyPrices,
    val priceChangePercentage1hInCurrency: CurrencyPrices,
    val priceChangePercentage24hInCurrency: CurrencyPrices,
    val priceChangePercentage7dInCurrency: CurrencyPrices,
    val priceChangePercentage14dInCurrency: CurrencyPrices,
    val priceChangePercentage30dInCurrency: CurrencyPrices,
    val priceChangePercentage60dInCurrency: CurrencyPrices,
    val priceChangePercentage200dInCurrency: CurrencyPrices,
    val priceChangePercentage1yInCurrency: CurrencyPrices,
    val marketCapChangePercentage24hInCurrency: CurrencyPrices,
    val maxSupply: Double,
    val circulatingSupply: Double,
)