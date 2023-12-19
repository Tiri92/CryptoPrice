package thierry.cryptoprice.coingeckorepository.random

import thierry.cryptoprice.coingeckorepository.BitcoinPriceResponse
import thierry.cryptoprice.coingeckorepository.CurrentPrice
import thierry.cryptoprice.coingeckorepository.MarketData
import kotlin.random.Random

internal fun Random.nextBitcoinPriceResponse() = BitcoinPriceResponse(
    id = "",
    market_data = MarketData(
        current_price =
        CurrentPrice(
            eur = 1.0,
            usd = 1.0
        )
    ),
    name = "", symbol = "",
)