package thierry.cryptoprice.coingeckorepository.mapper

import org.junit.Test
import thierry.cryptoprice.coingeckorepository.random.nextBitcoinPriceResponse
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrentPrice
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
            market_data = response.marketData.toMarketDataUc(),
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
            CurrentPrice(
                eur = response.marketData.currentPrice.eur,
                usd = response.marketData.currentPrice.usd,
                aud = response.marketData.currentPrice.aud,
                cad = response.marketData.currentPrice.cad,
                chf = response.marketData.currentPrice.chf,
                eth = response.marketData.currentPrice.eth,
                gbp = response.marketData.currentPrice.gbp,
                jpy = response.marketData.currentPrice.jpy,
                mxn = response.marketData.currentPrice.mxn,
                pln = response.marketData.currentPrice.pln,
                rub = response.marketData.currentPrice.rub,
                xag = response.marketData.currentPrice.xag,
                xau = response.marketData.currentPrice.xau,
                zar = response.marketData.currentPrice.zar,
            ),
            high24h = CurrentPrice(
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
            low24h = CurrentPrice(
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
            )
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