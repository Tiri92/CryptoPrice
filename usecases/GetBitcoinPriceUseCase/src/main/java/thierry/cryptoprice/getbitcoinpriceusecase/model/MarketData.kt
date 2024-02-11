package thierry.cryptoprice.getbitcoinpriceusecase.model

data class MarketData(
    val currentPrice: CurrentPrice,
    val high24h: CurrentPrice,
    val low24h: CurrentPrice,
)