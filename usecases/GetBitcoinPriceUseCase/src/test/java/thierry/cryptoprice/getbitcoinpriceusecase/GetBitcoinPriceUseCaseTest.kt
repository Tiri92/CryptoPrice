package thierry.cryptoprice.getbitcoinpriceusecase

import kotlinx.coroutines.test.runTest
import org.junit.Test
import thierry.cryptoprice.getbitcoinpriceusecase.fake.FakeCoinGeckoRepository
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrentPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.MarketData
import thierry.cryptoprice.resultof.ResultOf
import kotlin.random.Random
import kotlin.test.assertTrue

class GetBitcoinPriceUseCaseTest {
    @Test
    fun `GetBitcoinPriceUseCase should return success when repo Succeeded`() = runTest {
        //GIVEN
        val getBitcoinPriceUseCase =
            GetBitcoinPriceUseCase(coinGeckoRepository = object : FakeCoinGeckoRepository() {
                override suspend fun getBitcoinPrice(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(Random.nextBitcoinPrice())
            })

        //WHEN
        val result = getBitcoinPriceUseCase()

        //THEN
        assertTrue(result.isSuccess)
    }

    @Test
    fun `GetBitcoinPriceUseCase should return failure when repo fail`() = runTest {
        //GIVEN
        val getBitcoinPriceUseCase =
            GetBitcoinPriceUseCase(coinGeckoRepository = object : FakeCoinGeckoRepository() {
                override suspend fun getBitcoinPrice(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.failure(CryptoPriceException.InfoNotFoundPriceException)
            })

        //WHEN
        val result = getBitcoinPriceUseCase()

        //THEN
        assertTrue(result.isFailure)
    }
}

private fun Random.nextBitcoinPrice() =
    BitcoinPrice(
        id = "",
        market_data = MarketData(
            current_price = CurrentPrice(
                eur = 1.0,
                usd = 1.0
            )
        ),
        name = "",
        symbol = ""
    )