package thierry.cryptoprice.getbitcoinpriceusecase

import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.resultof.ResultOf
import javax.inject.Inject

class GetBitcoinPriceUseCase @Inject constructor(
    private val coinGeckoRepository: CoinGeckoRepository,
) {
    suspend operator fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
        coinGeckoRepository.getBitcoinPrice()
}