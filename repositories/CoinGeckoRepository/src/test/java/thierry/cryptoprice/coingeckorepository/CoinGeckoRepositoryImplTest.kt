package thierry.cryptoprice.coingeckorepository

import kotlinx.coroutines.test.runTest
import org.junit.Test
import thierry.cryptoprice.coingeckorepository.fake.FakeCoinGeckoService
import thierry.cryptoprice.coingeckorepository.random.nextBitcoinPriceResponse
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