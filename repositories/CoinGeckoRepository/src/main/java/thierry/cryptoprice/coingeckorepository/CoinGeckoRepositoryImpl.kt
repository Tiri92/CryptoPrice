package thierry.cryptoprice.coingeckorepository

import thierry.cryptoprice.coingeckorepository.mapper.toCryptoPriceException
import thierry.cryptoprice.getbitcoinpriceusecase.CoinGeckoRepository
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrentPrice
import thierry.cryptoprice.resultof.ResultOf
import thierry.cryptoprice.resultof.apiCall
import thierry.cryptoprice.resultof.mapFailure
import thierry.cryptoprice.resultof.mapSuccess
import javax.inject.Inject
import thierry.cryptoprice.getbitcoinpriceusecase.model.MarketData as MarketDataFromUc

class CoinGeckoRepositoryImpl @Inject constructor( //TODO Add test
    private val coinGeckoService: CoingeckoService,
) : CoinGeckoRepository {
    override suspend fun getBitcoinPrice(): ResultOf<BitcoinPrice, CryptoPriceException> =
        apiCall {
            coinGeckoService.getCoinById("bitcoin")
        }.mapSuccess {
            it.toBitcoinPrice()
        }.mapFailure {
            it.toCryptoPriceException()
        }
}

internal fun BitcoinPriceResponse.toBitcoinPrice(): BitcoinPrice = BitcoinPrice(
    id = this.id,
    market_data = this.market_data.toMarketDataUc(),
    name = this.name,
    symbol = this.symbol,
)

internal fun MarketData.toMarketDataUc(): MarketDataFromUc =
    MarketDataFromUc(
        CurrentPrice(
            eur = this.current_price.eur,
            usd = this.current_price.usd
        )
    )
