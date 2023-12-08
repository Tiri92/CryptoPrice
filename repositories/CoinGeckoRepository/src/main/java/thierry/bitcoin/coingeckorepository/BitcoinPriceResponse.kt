package thierry.bitcoin.coingeckorepository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BitcoinPriceResponse(
    @SerialName("id")
    val id: String,
    @SerialName("market_data")
    val market_data: MarketData,
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String,
)

@Serializable
data class MarketData(
    @SerialName("current_price")
    val current_price: CurrentPrice
)

@Serializable
class CurrentPrice(
    @SerialName("eur")
    val eur: Double,
    @SerialName("usd")
    val usd: Double,
)