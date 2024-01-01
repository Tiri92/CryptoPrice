package thierry.cryptoprice.coingeckorepository.mapper

import thierry.cryptoprice.coingeckorepository.BitcoinPriceResponse
import thierry.cryptoprice.coingeckorepository.MarketData
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrentPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.MarketData as MarketDataFromUc

internal fun BitcoinPriceResponse.toBitcoinPrice(): BitcoinPrice = BitcoinPrice(
    id = this.id,
    market_data = this.marketData.toMarketDataUc(),
    name = this.name,
    symbol = this.symbol,
)

internal fun MarketData.toMarketDataUc(): MarketDataFromUc =
    MarketDataFromUc(
        CurrentPrice(
            eur = this.currentPrice.eur,
            usd = this.currentPrice.usd,
            aud = this.currentPrice.aud,
            cad = this.currentPrice.cad,
            chf = this.currentPrice.chf,
            eth = this.currentPrice.eth,
            gbp = this.currentPrice.gbp,
            jpy = this.currentPrice.jpy,
            mxn = this.currentPrice.mxn,
            pln = this.currentPrice.pln,
            rub = this.currentPrice.rub,
            xag = this.currentPrice.xag,
            xau = this.currentPrice.xau,
            zar = this.currentPrice.zar,
        )
    )