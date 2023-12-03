package thierry.bitcoin.coingeckorepository

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import thierry.bitcoin.getbitcoinpriceusecase.model.BitcoinPrice

interface CoingeckoService {

    @GET("/api/v3/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): BitcoinPriceResponse
}