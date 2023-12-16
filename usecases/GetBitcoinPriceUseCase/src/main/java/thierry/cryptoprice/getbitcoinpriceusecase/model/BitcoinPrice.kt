package thierry.cryptoprice.getbitcoinpriceusecase.model

data class BitcoinPrice(
    val id:String,
    val market_data: MarketData,
    val name: String,
    val symbol: String,
)