package thierry.cryptoprice.getbitcoinpriceusecase.model

data class BitcoinPrice(
    val id:String,
    val marketData: MarketData,
    val name: String,
    val symbol: String,
)