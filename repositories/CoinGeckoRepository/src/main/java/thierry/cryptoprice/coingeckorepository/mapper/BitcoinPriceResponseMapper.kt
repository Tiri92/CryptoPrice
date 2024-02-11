package thierry.cryptoprice.coingeckorepository.mapper

import thierry.cryptoprice.coingeckorepository.BitcoinPriceResponse
import thierry.cryptoprice.coingeckorepository.MarketData
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrentPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.MarketData as MarketDataFromUc

internal fun BitcoinPriceResponse.toBitcoinPrice(): BitcoinPrice = BitcoinPrice(
    id = id,
    market_data = marketData.toMarketDataUc(),
    name = name,
    symbol = symbol,
)

internal fun MarketData.toMarketDataUc(): MarketDataFromUc =
    MarketDataFromUc(
        currentPrice = CurrentPrice(
            eur = currentPrice.eur,
            usd = currentPrice.usd,
            aud = currentPrice.aud,
            cad = currentPrice.cad,
            chf = currentPrice.chf,
            eth = currentPrice.eth,
            gbp = currentPrice.gbp,
            jpy = currentPrice.jpy,
            mxn = currentPrice.mxn,
            pln = currentPrice.pln,
            rub = currentPrice.rub,
            xag = currentPrice.xag,
            xau = currentPrice.xau,
            zar = currentPrice.zar,
        ),
        high24h = CurrentPrice(
            eur = high24h.eur,
            usd = high24h.usd,
            aud = high24h.aud,
            cad = high24h.cad,
            chf = high24h.chf,
            eth = high24h.eth,
            gbp = high24h.gbp,
            jpy = high24h.jpy,
            mxn = high24h.mxn,
            pln = high24h.pln,
            rub = high24h.rub,
            xag = high24h.xag,
            xau = high24h.xau,
            zar = high24h.zar,
        ),
        low24h = CurrentPrice(
            eur = low24h.eur,
            usd = low24h.usd,
            aud = low24h.aud,
            cad = low24h.cad,
            chf = low24h.chf,
            eth = low24h.eth,
            gbp = low24h.gbp,
            jpy = low24h.jpy,
            mxn = low24h.mxn,
            pln = low24h.pln,
            rub = low24h.rub,
            xag = low24h.xag,
            xau = low24h.xau,
            zar = low24h.zar,
        )
    )