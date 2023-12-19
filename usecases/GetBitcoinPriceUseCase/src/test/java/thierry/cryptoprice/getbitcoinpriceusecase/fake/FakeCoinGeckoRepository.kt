package thierry.cryptoprice.getbitcoinpriceusecase.fake

import thierry.cryptoprice.getbitcoinpriceusecase.CoinGeckoRepository
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.resultof.ResultOf

open class FakeCoinGeckoRepository: CoinGeckoRepository {
    override suspend fun getBitcoinPrice(): ResultOf<BitcoinPrice, CryptoPriceException> {
        TODO("Not yet implemented")
    }
}