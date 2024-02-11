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
    val currencyPrices: CurrencyPrices,
    @SerialName("high_24h")
    val high24h: CurrencyPrices,
    @SerialName("low_24h")
    val low24h: CurrencyPrices,
    @SerialName("ath")
    val ath: CurrencyPrices,
    @SerialName("price_change_percentage_1h_in_currency")
    val priceChangePercentage1hInCurrency: CurrencyPrices,
    @SerialName("price_change_percentage_24h_in_currency")
    val priceChangePercentage24hInCurrency: CurrencyPrices,
    @SerialName("price_change_percentage_7d_in_currency")
    val priceChangePercentage7dInCurrency: CurrencyPrices,
    @SerialName("price_change_percentage_14d_in_currency")
    val priceChangePercentage14dInCurrency: CurrencyPrices,
    @SerialName("price_change_percentage_30d_in_currency")
    val priceChangePercentage30dInCurrency: CurrencyPrices,
    @SerialName("price_change_percentage_60d_in_currency")
    val priceChangePercentage60dInCurrency: CurrencyPrices,
    @SerialName("price_change_percentage_200d_in_currency")
    val priceChangePercentage200dInCurrency: CurrencyPrices,
    @SerialName("price_change_percentage_1y_in_currency")
    val priceChangePercentage1yInCurrency: CurrencyPrices,
    @SerialName("market_cap_change_percentage_24h_in_currency")
    val marketCapChangePercentage24hInCurrency: CurrencyPrices,
    @SerialName("max_supply")
    val maxSupply: Double,
    @SerialName("circulating_supply")
    val circulatingSupply: Double,
)

@Serializable
class CurrencyPrices(
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