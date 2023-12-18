package thierry.cryptoprice.getbitcoinpriceusecase

import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.resultof.ResultOf

interface CoinGeckoRepository {
    suspend fun getBitcoinPrice(): ResultOf<BitcoinPrice, CryptoPriceException>
}