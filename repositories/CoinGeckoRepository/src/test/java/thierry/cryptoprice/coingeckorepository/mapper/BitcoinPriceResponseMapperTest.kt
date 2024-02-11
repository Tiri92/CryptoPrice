package thierry.cryptoprice.coingeckorepository.mapper

import org.junit.Test
import thierry.cryptoprice.coingeckorepository.random.nextBitcoinPriceResponse
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrencyPrices
import thierry.cryptoprice.getbitcoinpriceusecase.model.MarketData
import kotlin.random.Random
import kotlin.test.assertEquals

class BitcoinPriceResponseMapperTest {

    @Test
    fun `toBitcoinPrice test`() {
        //GIVEN
        val response = Random.nextBitcoinPriceResponse()
        val expected = BitcoinPrice(
            id = response.id,
            marketData = response.marketData.toMarketDataUc(),
            name = response.name,
            symbol = response.symbol
        )

        //WHEN
        val result = response.toBitcoinPrice()

        //THEN
        assertEquals(
            expected = expected,
            actual = result
        )
    }

    @Test
    fun `toMarketDataUc test`() {
        //GIVEN
        val response = Random.nextBitcoinPriceResponse()
        val expected = MarketData(
            CurrencyPrices(
                eur = response.marketData.currencyPrices.eur,
                usd = response.marketData.currencyPrices.usd,
                aud = response.marketData.currencyPrices.aud,
                cad = response.marketData.currencyPrices.cad,
                chf = response.marketData.currencyPrices.chf,
                eth = response.marketData.currencyPrices.eth,
                gbp = response.marketData.currencyPrices.gbp,
                jpy = response.marketData.currencyPrices.jpy,
                mxn = response.marketData.currencyPrices.mxn,
                pln = response.marketData.currencyPrices.pln,
                rub = response.marketData.currencyPrices.rub,
                xag = response.marketData.currencyPrices.xag,
                xau = response.marketData.currencyPrices.xau,
                zar = response.marketData.currencyPrices.zar,
            ),
            high24h = CurrencyPrices(
                eur = response.marketData.high24h.eur,
                usd = response.marketData.high24h.usd,
                aud = response.marketData.high24h.aud,
                cad = response.marketData.high24h.cad,
                chf = response.marketData.high24h.chf,
                eth = response.marketData.high24h.eth,
                gbp = response.marketData.high24h.gbp,
                jpy = response.marketData.high24h.jpy,
                mxn = response.marketData.high24h.mxn,
                pln = response.marketData.high24h.pln,
                rub = response.marketData.high24h.rub,
                xag = response.marketData.high24h.xag,
                xau = response.marketData.high24h.xau,
                zar = response.marketData.high24h.zar,
            ),
            low24h = CurrencyPrices(
                eur = response.marketData.low24h.eur,
                usd = response.marketData.low24h.usd,
                aud = response.marketData.low24h.aud,
                cad = response.marketData.low24h.cad,
                chf = response.marketData.low24h.chf,
                eth = response.marketData.low24h.eth,
                gbp = response.marketData.low24h.gbp,
                jpy = response.marketData.low24h.jpy,
                mxn = response.marketData.low24h.mxn,
                pln = response.marketData.low24h.pln,
                rub = response.marketData.low24h.rub,
                xag = response.marketData.low24h.xag,
                xau = response.marketData.low24h.xau,
                zar = response.marketData.low24h.zar,
            ),
            ath = CurrencyPrices(
                eur = response.marketData.ath.eur,
                usd = response.marketData.ath.usd,
                aud = response.marketData.ath.aud,
                cad = response.marketData.ath.cad,
                chf = response.marketData.ath.chf,
                eth = response.marketData.ath.eth,
                gbp = response.marketData.ath.gbp,
                jpy = response.marketData.ath.jpy,
                mxn = response.marketData.ath.mxn,
                pln = response.marketData.ath.pln,
                rub = response.marketData.ath.rub,
                xag = response.marketData.ath.xag,
                xau = response.marketData.ath.xau,
                zar = response.marketData.ath.zar,
            ),
            priceChangePercentage1hInCurrency = CurrencyPrices(
                eur = response.marketData.priceChangePercentage1hInCurrency.eur,
                usd = response.marketData.priceChangePercentage1hInCurrency.usd,
                aud = response.marketData.priceChangePercentage1hInCurrency.aud,
                cad = response.marketData.priceChangePercentage1hInCurrency.cad,
                chf = response.marketData.priceChangePercentage1hInCurrency.chf,
                eth = response.marketData.priceChangePercentage1hInCurrency.eth,
                gbp = response.marketData.priceChangePercentage1hInCurrency.gbp,
                jpy = response.marketData.priceChangePercentage1hInCurrency.jpy,
                mxn = response.marketData.priceChangePercentage1hInCurrency.mxn,
                pln = response.marketData.priceChangePercentage1hInCurrency.pln,
                rub = response.marketData.priceChangePercentage1hInCurrency.rub,
                xag = response.marketData.priceChangePercentage1hInCurrency.xag,
                xau = response.marketData.priceChangePercentage1hInCurrency.xau,
                zar = response.marketData.priceChangePercentage1hInCurrency.zar,
            ),
            priceChangePercentage24hInCurrency = CurrencyPrices(
                eur = response.marketData.priceChangePercentage24hInCurrency.eur,
                usd = response.marketData.priceChangePercentage24hInCurrency.usd,
                aud = response.marketData.priceChangePercentage24hInCurrency.aud,
                cad = response.marketData.priceChangePercentage24hInCurrency.cad,
                chf = response.marketData.priceChangePercentage24hInCurrency.chf,
                eth = response.marketData.priceChangePercentage24hInCurrency.eth,
                gbp = response.marketData.priceChangePercentage24hInCurrency.gbp,
                jpy = response.marketData.priceChangePercentage24hInCurrency.jpy,
                mxn = response.marketData.priceChangePercentage24hInCurrency.mxn,
                pln = response.marketData.priceChangePercentage24hInCurrency.pln,
                rub = response.marketData.priceChangePercentage24hInCurrency.rub,
                xag = response.marketData.priceChangePercentage24hInCurrency.xag,
                xau = response.marketData.priceChangePercentage24hInCurrency.xau,
                zar = response.marketData.priceChangePercentage24hInCurrency.zar,
            ),
            priceChangePercentage7dInCurrency = CurrencyPrices(
                eur = response.marketData.priceChangePercentage7dInCurrency.eur,
                usd = response.marketData.priceChangePercentage7dInCurrency.usd,
                aud = response.marketData.priceChangePercentage7dInCurrency.aud,
                cad = response.marketData.priceChangePercentage7dInCurrency.cad,
                chf = response.marketData.priceChangePercentage7dInCurrency.chf,
                eth = response.marketData.priceChangePercentage7dInCurrency.eth,
                gbp = response.marketData.priceChangePercentage7dInCurrency.gbp,
                jpy = response.marketData.priceChangePercentage7dInCurrency.jpy,
                mxn = response.marketData.priceChangePercentage7dInCurrency.mxn,
                pln = response.marketData.priceChangePercentage7dInCurrency.pln,
                rub = response.marketData.priceChangePercentage7dInCurrency.rub,
                xag = response.marketData.priceChangePercentage7dInCurrency.xag,
                xau = response.marketData.priceChangePercentage7dInCurrency.xau,
                zar = response.marketData.priceChangePercentage7dInCurrency.zar,
            ),
            priceChangePercentage14dInCurrency = CurrencyPrices(
                eur = response.marketData.priceChangePercentage14dInCurrency.eur,
                usd = response.marketData.priceChangePercentage14dInCurrency.usd,
                aud = response.marketData.priceChangePercentage14dInCurrency.aud,
                cad = response.marketData.priceChangePercentage14dInCurrency.cad,
                chf = response.marketData.priceChangePercentage14dInCurrency.chf,
                eth = response.marketData.priceChangePercentage14dInCurrency.eth,
                gbp = response.marketData.priceChangePercentage14dInCurrency.gbp,
                jpy = response.marketData.priceChangePercentage14dInCurrency.jpy,
                mxn = response.marketData.priceChangePercentage14dInCurrency.mxn,
                pln = response.marketData.priceChangePercentage14dInCurrency.pln,
                rub = response.marketData.priceChangePercentage14dInCurrency.rub,
                xag = response.marketData.priceChangePercentage14dInCurrency.xag,
                xau = response.marketData.priceChangePercentage14dInCurrency.xau,
                zar = response.marketData.priceChangePercentage14dInCurrency.zar,
            ),
            priceChangePercentage30dInCurrency = CurrencyPrices(
                eur = response.marketData.priceChangePercentage30dInCurrency.eur,
                usd = response.marketData.priceChangePercentage30dInCurrency.usd,
                aud = response.marketData.priceChangePercentage30dInCurrency.aud,
                cad = response.marketData.priceChangePercentage30dInCurrency.cad,
                chf = response.marketData.priceChangePercentage30dInCurrency.chf,
                eth = response.marketData.priceChangePercentage30dInCurrency.eth,
                gbp = response.marketData.priceChangePercentage30dInCurrency.gbp,
                jpy = response.marketData.priceChangePercentage30dInCurrency.jpy,
                mxn = response.marketData.priceChangePercentage30dInCurrency.mxn,
                pln = response.marketData.priceChangePercentage30dInCurrency.pln,
                rub = response.marketData.priceChangePercentage30dInCurrency.rub,
                xag = response.marketData.priceChangePercentage30dInCurrency.xag,
                xau = response.marketData.priceChangePercentage30dInCurrency.xau,
                zar = response.marketData.priceChangePercentage30dInCurrency.zar,
            ),
            priceChangePercentage60dInCurrency = CurrencyPrices(
                eur = response.marketData.priceChangePercentage60dInCurrency.eur,
                usd = response.marketData.priceChangePercentage60dInCurrency.usd,
                aud = response.marketData.priceChangePercentage60dInCurrency.aud,
                cad = response.marketData.priceChangePercentage60dInCurrency.cad,
                chf = response.marketData.priceChangePercentage60dInCurrency.chf,
                eth = response.marketData.priceChangePercentage60dInCurrency.eth,
                gbp = response.marketData.priceChangePercentage60dInCurrency.gbp,
                jpy = response.marketData.priceChangePercentage60dInCurrency.jpy,
                mxn = response.marketData.priceChangePercentage60dInCurrency.mxn,
                pln = response.marketData.priceChangePercentage60dInCurrency.pln,
                rub = response.marketData.priceChangePercentage60dInCurrency.rub,
                xag = response.marketData.priceChangePercentage60dInCurrency.xag,
                xau = response.marketData.priceChangePercentage60dInCurrency.xau,
                zar = response.marketData.priceChangePercentage60dInCurrency.zar,
            ),
            priceChangePercentage200dInCurrency = CurrencyPrices(
                eur = response.marketData.priceChangePercentage200dInCurrency.eur,
                usd = response.marketData.priceChangePercentage200dInCurrency.usd,
                aud = response.marketData.priceChangePercentage200dInCurrency.aud,
                cad = response.marketData.priceChangePercentage200dInCurrency.cad,
                chf = response.marketData.priceChangePercentage200dInCurrency.chf,
                eth = response.marketData.priceChangePercentage200dInCurrency.eth,
                gbp = response.marketData.priceChangePercentage200dInCurrency.gbp,
                jpy = response.marketData.priceChangePercentage200dInCurrency.jpy,
                mxn = response.marketData.priceChangePercentage200dInCurrency.mxn,
                pln = response.marketData.priceChangePercentage200dInCurrency.pln,
                rub = response.marketData.priceChangePercentage200dInCurrency.rub,
                xag = response.marketData.priceChangePercentage200dInCurrency.xag,
                xau = response.marketData.priceChangePercentage200dInCurrency.xau,
                zar = response.marketData.priceChangePercentage200dInCurrency.zar,
            ),
            priceChangePercentage1yInCurrency = CurrencyPrices(
                eur = response.marketData.priceChangePercentage1yInCurrency.eur,
                usd = response.marketData.priceChangePercentage1yInCurrency.usd,
                aud = response.marketData.priceChangePercentage1yInCurrency.aud,
                cad = response.marketData.priceChangePercentage1yInCurrency.cad,
                chf = response.marketData.priceChangePercentage1yInCurrency.chf,
                eth = response.marketData.priceChangePercentage1yInCurrency.eth,
                gbp = response.marketData.priceChangePercentage1yInCurrency.gbp,
                jpy = response.marketData.priceChangePercentage1yInCurrency.jpy,
                mxn = response.marketData.priceChangePercentage1yInCurrency.mxn,
                pln = response.marketData.priceChangePercentage1yInCurrency.pln,
                rub = response.marketData.priceChangePercentage1yInCurrency.rub,
                xag = response.marketData.priceChangePercentage1yInCurrency.xag,
                xau = response.marketData.priceChangePercentage1yInCurrency.xau,
                zar = response.marketData.priceChangePercentage1yInCurrency.zar,
            ),
            marketCapChangePercentage24hInCurrency = CurrencyPrices(
                eur = response.marketData.marketCapChangePercentage24hInCurrency.eur,
                usd = response.marketData.marketCapChangePercentage24hInCurrency.usd,
                aud = response.marketData.marketCapChangePercentage24hInCurrency.aud,
                cad = response.marketData.marketCapChangePercentage24hInCurrency.cad,
                chf = response.marketData.marketCapChangePercentage24hInCurrency.chf,
                eth = response.marketData.marketCapChangePercentage24hInCurrency.eth,
                gbp = response.marketData.marketCapChangePercentage24hInCurrency.gbp,
                jpy = response.marketData.marketCapChangePercentage24hInCurrency.jpy,
                mxn = response.marketData.marketCapChangePercentage24hInCurrency.mxn,
                pln = response.marketData.marketCapChangePercentage24hInCurrency.pln,
                rub = response.marketData.marketCapChangePercentage24hInCurrency.rub,
                xag = response.marketData.marketCapChangePercentage24hInCurrency.xag,
                xau = response.marketData.marketCapChangePercentage24hInCurrency.xau,
                zar = response.marketData.marketCapChangePercentage24hInCurrency.zar,
            ),
            maxSupply = response.marketData.maxSupply,
            circulatingSupply = response.marketData.circulatingSupply,
        )

        //WHEN
        val result = response.marketData.toMarketDataUc()

        //THEN
        assertEquals(
            expected = expected,
            actual = result
        )
    }
}