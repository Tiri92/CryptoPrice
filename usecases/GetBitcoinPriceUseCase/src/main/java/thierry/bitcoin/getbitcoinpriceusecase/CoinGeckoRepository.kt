package thierry.bitcoin.getbitcoinpriceusecase

import thierry.bitcoin.getbitcoinpriceusecase.model.BitcoinPrice

interface CoinGeckoRepository {
    suspend fun getBitcoinPrice(): BitcoinPrice
}