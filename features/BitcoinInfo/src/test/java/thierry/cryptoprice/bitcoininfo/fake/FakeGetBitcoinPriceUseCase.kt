package thierry.cryptoprice.bitcoininfo.fake

import thierry.cryptoprice.getbitcoinpriceusecase.GetBitcoinPriceUseCase
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.resultof.ResultOf

open class FakeGetBitcoinPriceUseCase : GetBitcoinPriceUseCase {
    override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> {
        TODO("Not yet implemented")
    }
}