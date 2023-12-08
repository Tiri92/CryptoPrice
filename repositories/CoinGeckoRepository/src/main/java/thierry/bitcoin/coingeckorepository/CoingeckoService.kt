package thierry.bitcoin.coingeckorepository

import retrofit2.http.GET
import retrofit2.http.Path

interface CoingeckoService {

    @GET("/api/v3/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): BitcoinPriceResponse
}