package thierry.cryptoprice.coingeckorepository.mapper

import thierry.cryptoprice.coingeckorepository.BitcoinPriceResponse
import thierry.cryptoprice.coingeckorepository.MarketData
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrentPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.MarketData as MarketDataFromUc

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