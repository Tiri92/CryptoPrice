package thierry.bitcoin.coingeckorepository

import android.util.Log
import thierry.bitcoin.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.bitcoin.getbitcoinpriceusecase.CoingeckoRepository
import thierry.bitcoin.getbitcoinpriceusecase.model.CurrentPrice
import javax.inject.Inject

class CoingeckoRepositoryImpl @Inject constructor(
    private val coingeckoService: CoingeckoService,
): CoingeckoRepository {
    override suspend fun getBitcoinPrice(): BitcoinPrice? {

       return coingeckoService.getCoinById("bitcoin").toBitcoinPrice()
    }

    // ResultOf ? better mapping with exception etc

    // Dependency Inversion with UseCase module wich contain the model return by the repo and the repo interface
    // so model return by api must be different than model return by repo to usecase, each layer have his own model

}

fun BitcoinPriceResponse.toBitcoinPrice(): BitcoinPrice = BitcoinPrice(
    id = this.id,
    market_data = this.market_data.toMarketDataUc(),
    name = this.name,
    symbol = this.symbol,
)

fun MarketData.toMarketDataUc(): thierry.bitcoin.getbitcoinpriceusecase.model.MarketData =
    thierry.bitcoin.getbitcoinpriceusecase.model.MarketData(CurrentPrice(eur = this.current_price.eur, usd = this.current_price.usd))
