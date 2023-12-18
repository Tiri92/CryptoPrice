package thierry.cryptoprice.getbitcoinpriceusecase

import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.resultof.ResultOf
import thierry.cryptoprice.resultof.entity.ApiCallFailure

interface CoinGeckoRepository {
    suspend fun getBitcoinPrice(): ResultOf<BitcoinPrice, ApiCallFailure>
}