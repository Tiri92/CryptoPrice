package thierry.cryptoprice.getbitcoinpriceusecase

import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.resultof.ResultOf
import javax.inject.Inject

fun interface GetBitcoinPriceUseCase {
    suspend operator fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException>
}

class GetBitcoinPriceUseCaseImpl @Inject constructor(
    private val coinGeckoRepository: CoinGeckoRepository,
) : GetBitcoinPriceUseCase {
    override suspend operator fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
        coinGeckoRepository.getBitcoinPrice()
}