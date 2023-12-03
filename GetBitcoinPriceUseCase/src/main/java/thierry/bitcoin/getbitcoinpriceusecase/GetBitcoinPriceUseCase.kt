package thierry.bitcoin.getbitcoinpriceusecase

import thierry.bitcoin.getbitcoinpriceusecase.model.BitcoinPrice
import javax.inject.Inject

class GetBitcoinPriceUseCase @Inject constructor(
    private val repository: CoingeckoRepository,
) {
    suspend operator fun invoke(): BitcoinPrice? = repository.getBitcoinPrice()
}