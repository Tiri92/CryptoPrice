package thierry.cryptoprice.coingeckorepository

import kotlinx.coroutines.test.runTest
import org.junit.Test
import thierry.cryptoprice.coingeckorepository.fake.FakeCoinGeckoService
import kotlin.random.Random
import kotlin.test.assertTrue

class CoinGeckoRepositoryImplTest {
    @Test
    fun `getBitcoinPrice should return success when api succeeded`() = runTest {
        //GIVEN
        val expected = Random.nextBitcoinPriceResponse()
        val repository =
            CoinGeckoRepositoryImpl(coinGeckoService = object : FakeCoinGeckoService() {
                override suspend fun getCoinById(id: String): BitcoinPriceResponse =
                    expected
            })

        //WHEN
        val result = repository.getBitcoinPrice()

        //THEN
        assertTrue(result.isSuccess)
    }

    @Test
    fun `getBitcoinPrice should return failure when api fail`() = runTest {
        //GIVEN
        val repository =
            CoinGeckoRepositoryImpl(coinGeckoService = object : FakeCoinGeckoService() {
                override suspend fun getCoinById(id: String): BitcoinPriceResponse =
                    throw Throwable()
            })

        //WHEN
        val result = repository.getBitcoinPrice()

        //THEN
        assertTrue(result.isFailure)
    }
}

private fun Random.nextBitcoinPriceResponse() = BitcoinPriceResponse(
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