package thierry.cryptoprice.coingeckorepository.fake

import thierry.cryptoprice.coingeckorepository.BitcoinPriceResponse
import thierry.cryptoprice.coingeckorepository.CoinGeckoService

open class FakeCoinGeckoService : CoinGeckoService {
    override suspend fun getCoinById(id: String): BitcoinPriceResponse {
        TODO("Not yet implemented")
    }
}