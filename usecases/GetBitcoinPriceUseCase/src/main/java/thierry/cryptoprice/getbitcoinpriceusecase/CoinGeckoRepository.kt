package thierry.cryptoprice.getbitcoinpriceusecase

import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice

interface CoinGeckoRepository {
    suspend fun getBitcoinPrice(): BitcoinPrice
}