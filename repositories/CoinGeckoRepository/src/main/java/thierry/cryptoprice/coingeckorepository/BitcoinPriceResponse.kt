package thierry.cryptoprice.coingeckorepository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BitcoinPriceResponse(
    @SerialName("id")
    val id: String,
    @SerialName("market_data")
    val marketData: MarketData,
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String,
)

@Serializable
data class MarketData(
    @SerialName("current_price")
    val currentPrice: CurrentPrice,
    @SerialName("high_24h")
    val high24h: CurrentPrice,
    @SerialName("low_24h")
    val low24h: CurrentPrice,
)

@Serializable
class CurrentPrice(
    @SerialName("eur")
    val eur: Double,
    @SerialName("usd")
    val usd: Double,
    @SerialName("aud")
    val aud: Double,
    @SerialName("cad")
    val cad: Double,
    @SerialName("chf")
    val chf: Double,
    @SerialName("eth")
    val eth: Double,
    @SerialName("gbp")
    val gbp: Double,
    @SerialName("jpy")
    val jpy: Double,
    @SerialName("mxn")
    val mxn: Double,
    @SerialName("pln")
    val pln: Double,
    @SerialName("rub")
    val rub: Double,
    @SerialName("xag")
    val xag: Double,
    @SerialName("xau")
    val xau: Double,
    @SerialName("zar")
    val zar: Double,
)