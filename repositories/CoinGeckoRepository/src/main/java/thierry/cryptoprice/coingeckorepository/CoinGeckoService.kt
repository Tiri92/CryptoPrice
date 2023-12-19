package thierry.cryptoprice.coingeckorepository

import retrofit2.http.GET
import retrofit2.http.Path

interface CoinGeckoService {

    @GET("/api/v3/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): BitcoinPriceResponse
}