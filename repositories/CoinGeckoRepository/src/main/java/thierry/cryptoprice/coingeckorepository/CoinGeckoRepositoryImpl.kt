package thierry.cryptoprice.coingeckorepository

import thierry.cryptoprice.coingeckorepository.mapper.toBitcoinPrice
import thierry.cryptoprice.coingeckorepository.mapper.toCryptoPriceException
import thierry.cryptoprice.getbitcoinpriceusecase.CoinGeckoRepository
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.resultof.ResultOf
import thierry.cryptoprice.resultof.apiCall
import thierry.cryptoprice.resultof.mapFailure
import thierry.cryptoprice.resultof.mapSuccess
import javax.inject.Inject

class CoinGeckoRepositoryImpl @Inject constructor(
    private val coinGeckoService: CoinGeckoService,
) : CoinGeckoRepository {
    override suspend fun getBitcoinPrice(): ResultOf<BitcoinPrice, CryptoPriceException> =
        apiCall {
            coinGeckoService.getCoinById("bitcoin")
        }.mapSuccess {
            it.toBitcoinPrice()
        }.mapFailure {
            it.toCryptoPriceException()
        }
}
