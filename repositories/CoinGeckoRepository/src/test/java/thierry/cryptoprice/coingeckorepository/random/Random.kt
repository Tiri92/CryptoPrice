package thierry.cryptoprice.coingeckorepository.random

import thierry.cryptoprice.coingeckorepository.BitcoinPriceResponse
import thierry.cryptoprice.coingeckorepository.CurrentPrice
import thierry.cryptoprice.coingeckorepository.MarketData
import kotlin.random.Random

internal fun Random.nextBitcoinPriceResponse() = BitcoinPriceResponse(
    id = "",
    marketData = MarketData(
        currentPrice =
        CurrentPrice(
            eur = 1.0,
            usd = 1.0,
            aud = 1.0,
            cad = 1.0,
            chf = 1.0,
            eth = 1.0,
            gbp = 1.0,
            jpy = 1.0,
            mxn = 1.0,
            pln = 1.0,
            rub = 1.0,
            xag = 1.0,
            xau = 1.0,
            zar = 1.0,
        ),
        high24h = CurrentPrice(
            eur = 1.0,
            usd = 1.0,
            aud = 1.0,
            cad = 1.0,
            chf = 1.0,
            eth = 1.0,
            gbp = 1.0,
            jpy = 1.0,
            mxn = 1.0,
            pln = 1.0,
            rub = 1.0,
            xag = 1.0,
            xau = 1.0,
            zar = 1.0,
        ),
        low24h = CurrentPrice(
            eur = 1.0,
            usd = 1.0,
            aud = 1.0,
            cad = 1.0,
            chf = 1.0,
            eth = 1.0,
            gbp = 1.0,
            jpy = 1.0,
            mxn = 1.0,
            pln = 1.0,
            rub = 1.0,
            xag = 1.0,
            xau = 1.0,
            zar = 1.0,
        )
    ),
    name = "", symbol = "",
)