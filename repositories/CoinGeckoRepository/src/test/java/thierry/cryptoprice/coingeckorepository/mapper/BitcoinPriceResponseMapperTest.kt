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