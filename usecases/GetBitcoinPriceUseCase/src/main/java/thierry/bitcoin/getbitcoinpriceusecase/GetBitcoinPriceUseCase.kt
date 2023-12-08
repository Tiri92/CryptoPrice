package thierry.bitcoin.getbitcoinpriceusecase

import thierry.bitcoin.getbitcoinpriceusecase.model.BitcoinPrice
import javax.inject.Inject

class GetBitcoinPriceUseCase @Inject constructor(
    private val coinGeckoRepository: CoinGeckoRepository,
) {
    suspend operator fun invoke(): BitcoinPrice = coinGeckoRepository.getBitcoinPrice()
}