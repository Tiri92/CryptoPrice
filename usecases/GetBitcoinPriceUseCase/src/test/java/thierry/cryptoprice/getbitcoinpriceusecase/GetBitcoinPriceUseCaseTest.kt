package thierry.cryptoprice.getbitcoinpriceusecase

import kotlinx.coroutines.test.runTest
import org.junit.Test
import thierry.cryptoprice.getbitcoinpriceusecase.fake.FakeCoinGeckoRepository
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrencyPrices
import thierry.cryptoprice.getbitcoinpriceusecase.model.MarketData
import thierry.cryptoprice.resultof.ResultOf
import kotlin.random.Random
import kotlin.test.assertTrue

class GetBitcoinPriceUseCaseTest {
    @Test
    fun `GetBitcoinPriceUseCase should return success when repo Succeeded`() = runTest {
        //GIVEN
        val getBitcoinPriceUseCase =
            GetBitcoinPriceUseCaseImpl(coinGeckoRepository = object : FakeCoinGeckoRepository() {
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
            GetBitcoinPriceUseCaseImpl(coinGeckoRepository = object : FakeCoinGeckoRepository() {
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
        marketData = MarketData(
            currencyPrices = CurrencyPrices(
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
            high24h = CurrencyPrices(
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
            low24h = CurrencyPrices(
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
            ath = CurrencyPrices(
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
            priceChangePercentage1hInCurrency = CurrencyPrices(
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
            priceChangePercentage24hInCurrency = CurrencyPrices(
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
            priceChangePercentage7dInCurrency = CurrencyPrices(
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
            priceChangePercentage14dInCurrency = CurrencyPrices(
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
            priceChangePercentage30dInCurrency = CurrencyPrices(
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
            priceChangePercentage60dInCurrency = CurrencyPrices(
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
            priceChangePercentage200dInCurrency = CurrencyPrices(
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
            priceChangePercentage1yInCurrency = CurrencyPrices(
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
            marketCapChangePercentage24hInCurrency = CurrencyPrices(
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
            maxSupply = 20.999,
            circulatingSupply = 19.8,
        ),
        name = "",
        symbol = ""
    )