package thierry.cryptoprice.bitcoininfo

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import app.cash.turbine.turbineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import thierry.cryptoprice.bitcoininfo.fake.FakeGetBitcoinPriceUseCase
import thierry.cryptoprice.bitcoininfo.fake.FakePreferredCurrencyUseCase
import thierry.cryptoprice.getbitcoinpriceusecase.model.BitcoinPrice
import thierry.cryptoprice.getbitcoinpriceusecase.model.CryptoPriceException
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrencyPrices
import thierry.cryptoprice.getbitcoinpriceusecase.model.MarketData
import thierry.cryptoprice.resultof.ResultOf
import kotlin.test.assertEquals
import kotlin.test.assertIs

class BitcoinInfoViewModelTest {

    private val savedStateHandle = SavedStateHandle()
    private lateinit var viewModel: BitcoinInfoViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun reset() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Should return Loading then BitcoinInfo when getBitcoinPriceUseCase succeeded`() = runTest {
        //WHEN
        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(expectedBitcoinPrice())
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrencyUseCase(): Flow<String?> =
                    MutableStateFlow("")
            }
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<BitcoinInfoViewModel.BitcoinInfoUiState.Loading>(awaitItem())
                assertIs<BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo>(awaitItem())
            }
        }
    }

    @Test
    fun `Should return Loading then Error when getBitcoinPriceUseCase failed`() = runTest {
        //WHEN
        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.failure(CryptoPriceException.GenericPriceException)
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrencyUseCase(): Flow<String?> =
                    MutableStateFlow("")
            }
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<BitcoinInfoViewModel.BitcoinInfoUiState.Loading>(awaitItem())
                assertIs<BitcoinInfoViewModel.BitcoinInfoUiState.Error>(awaitItem())
            }
        }
    }

    @Test
    fun `retryGetBitcoinPrice should call getBitcoinPrice`() = runTest {
        //WHEN
        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(expectedBitcoinPrice())
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrencyUseCase(): Flow<String?> =
                    MutableStateFlow("")
            }
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                skipItems(2) //Skip initialization call for getBitcoinPrice
                viewModel.retryGetBitcoinPrice()

                assertIs<BitcoinInfoViewModel.BitcoinInfoUiState.Loading>(awaitItem())
                assertIs<BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo>(awaitItem())
            }
        }
    }

    @Test
    fun `pullRefreshBitcoinPrice should call getBitcoinPrice`() = runTest {
        //WHEN
        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(expectedBitcoinPrice())
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrencyUseCase(): Flow<String?> =
                    MutableStateFlow("")
            }
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                skipItems(2) //Skip initialization call for getBitcoinPrice
                viewModel.pullRefreshBitcoinPrice()

                assertEquals(
                    true,
                    (awaitItem() as BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo).isPullToRefreshLoading
                )
                (awaitItem() as BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo).run {
                    assertEquals("Bitcoin", btcName)
                    assertEquals("1000000.0", btcPrice)
                    assertEquals(false, isPullToRefreshLoading)
                }
            }
        }
    }

    @Test
    fun `Should return preferredCurrency when preferredCurrencyUseCase succeeded`() = runTest {
        //WHEN
        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(expectedBitcoinPrice())
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrencyUseCase(): Flow<String?> =
                    MutableStateFlow("usd")
            }
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<BitcoinInfoViewModel.BitcoinInfoUiState.Loading>(awaitItem())
                assertEquals(
                    "usd",
                    (awaitItem() as BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo).preferredCurrency
                )
            }
        }
    }

    @Test
    fun `Should return defaultCurrency when preferredCurrencyUseCase return null`() = runTest {
        //WHEN
        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(expectedBitcoinPrice())
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrencyUseCase(): Flow<String?> =
                    MutableStateFlow(null)
            }
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<BitcoinInfoViewModel.BitcoinInfoUiState.Loading>(awaitItem())
                assertEquals(
                    "eur",
                    (awaitItem() as BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo).preferredCurrency
                )
            }
        }
    }

    @Test
    fun `Should update defaultCurrency when setPreferredCurrencyUseCase is called`() = runTest {
        //WHEN
        var defaultPreferredCurrency = "eur"
        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(expectedBitcoinPrice())
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrencyUseCase(): Flow<String?> =
                    MutableStateFlow(defaultPreferredCurrency)

                override suspend fun setPreferredCurrencyUseCase(preferredCurrency: String) {
                    defaultPreferredCurrency = preferredCurrency
                }
            }
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                skipItems(2) //Skip items initialization

                viewModel.setPreferredCurrency("usd")
                viewModel.getBitcoinPrice()
                (awaitItem() as BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo).run {
                    assertEquals("usd", preferredCurrency)
                }
            }
        }
    }
}

private fun expectedBitcoinPrice(): BitcoinPrice =
    BitcoinPrice(
        id = "bitcoin",
        marketData = MarketData(
            currencyPrices = CurrencyPrices(
                eur = 1000000.0,
                usd = 1000000.0,
                aud = 1000000.0,
                cad = 1000000.0,
                chf = 1000000.0,
                eth = 1000000.0,
                gbp = 1000000.0,
                jpy = 1000000.0,
                mxn = 1000000.0,
                pln = 1000000.0,
                rub = 1000000.0,
                xag = 1000000.0,
                xau = 1000000.0,
                zar = 1000000.0,
            ),
            high24h = CurrencyPrices(
                eur = 1000000.0,
                usd = 1000000.0,
                aud = 1000000.0,
                cad = 1000000.0,
                chf = 1000000.0,
                eth = 1000000.0,
                gbp = 1000000.0,
                jpy = 1000000.0,
                mxn = 1000000.0,
                pln = 1000000.0,
                rub = 1000000.0,
                xag = 1000000.0,
                xau = 1000000.0,
                zar = 1000000.0,
            ),
            low24h = CurrencyPrices(
                eur = 1000000.0,
                usd = 1000000.0,
                aud = 1000000.0,
                cad = 1000000.0,
                chf = 1000000.0,
                eth = 1000000.0,
                gbp = 1000000.0,
                jpy = 1000000.0,
                mxn = 1000000.0,
                pln = 1000000.0,
                rub = 1000000.0,
                xag = 1000000.0,
                xau = 1000000.0,
                zar = 1000000.0,
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
        name = "Bitcoin",
        symbol = "btc"
    )