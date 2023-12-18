package thierry.cryptoprice.getbitcoinpriceusecase

import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.resultof.ResultOf
import thierry.cryptoprice.resultof.entity.ApiCallFailure
import javax.inject.Inject

class GetBitcoinPriceUseCase @Inject constructor(
    private val coinGeckoRepository: CoinGeckoRepository,
) {
    suspend operator fun invoke(): ResultOf<BitcoinPrice, ApiCallFailure> =
        coinGeckoRepository.getBitcoinPrice()
}