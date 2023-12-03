package thierry.bitcoin.getbitcoinpriceusecase

import thierry.bitcoin.getbitcoinpriceusecase.model.BitcoinPrice

interface CoingeckoRepository {
    suspend fun getBitcoinPrice(): BitcoinPrice?
}