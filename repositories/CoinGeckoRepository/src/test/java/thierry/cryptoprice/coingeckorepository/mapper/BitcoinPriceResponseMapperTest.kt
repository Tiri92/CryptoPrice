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
            market_data = response.market_data.toMarketDataUc(),
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
                eur = response.market_data.current_price.eur,
                usd = response.market_data.current_price.usd
            )
        )

        //WHEN
        val result = response.market_data.toMarketDataUc()

        //THEN
        assertEquals(
            expected = expected,
            actual = result
        )
    }
}