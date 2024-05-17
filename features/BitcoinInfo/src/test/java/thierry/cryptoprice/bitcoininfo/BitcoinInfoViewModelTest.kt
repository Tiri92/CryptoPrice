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
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.Error
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.Loading
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.TimeFrame
import thierry.cryptoprice.bitcoininfo.fake.FakeGetBitcoinPriceUseCase
import thierry.cryptoprice.bitcoininfo.fake.FakePreferredCurrencyUseCase
import thierry.cryptoprice.bitcoininfo.fake.FakePreferredTimeFrameUseCase
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
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow("")
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow("")
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<Loading>(awaitItem())
                assertIs<BitcoinInfo>(awaitItem())
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
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow("")
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow("")
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<Loading>(awaitItem())
                assertIs<Error>(awaitItem())
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
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow("")
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow("")
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                skipItems(2) //Skip initialization call for getBitcoinPrice
                viewModel.retryGetBitcoinPrice()

                assertIs<Loading>(awaitItem())
                assertIs<BitcoinInfo>(awaitItem())
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
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow("")
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow("")
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                skipItems(2) //Skip initialization call for getBitcoinPrice
                viewModel.pullRefreshBitcoinPrice()

                assertEquals(
                    true,
                    (awaitItem() as BitcoinInfo).isPullToRefreshLoading
                )
                (awaitItem() as BitcoinInfo).run {
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
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow("usd")
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow("")
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<Loading>(awaitItem())
                assertEquals(
                    "usd",
                    (awaitItem() as BitcoinInfo).preferredCurrency
                )
            }
        }
    }

    @Test
    fun `Should return preferredTimeFrame when preferredTimeFrameUseCase succeeded`() = runTest {
        //WHEN
        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(expectedBitcoinPrice())
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow("usd")
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow(TimeFrame.TWO_WEEKS.value)
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<Loading>(awaitItem())
                assertEquals(
                    TimeFrame.TWO_WEEKS.value,
                    (awaitItem() as BitcoinInfo).preferredTimeFrame
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
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow(null)
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow("")
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<Loading>(awaitItem())
                assertEquals(
                    "eur",
                    (awaitItem() as BitcoinInfo).preferredCurrency
                )
            }
        }
    }

    @Test
    fun `Should return defaultTimeFrame when preferredTimeFrameUseCase return null`() = runTest {
        //WHEN
        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(expectedBitcoinPrice())
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow(null)
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow(null)
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                assertIs<Loading>(awaitItem())
                assertEquals(
                    TimeFrame.ONE_DAY.value,
                    (awaitItem() as BitcoinInfo).preferredTimeFrame
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
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow(defaultPreferredCurrency)

                override suspend fun setPreferredCurrency(preferredCurrency: String) {
                    defaultPreferredCurrency = preferredCurrency
                }
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow("")
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                skipItems(2) //Skip items initialization

                viewModel.setPreferredCurrency("usd")
                viewModel.getBitcoinPrice()
                (awaitItem() as BitcoinInfo).run {
                    assertEquals("usd", preferredCurrency)
                }
            }
        }
    }

    @Test
    fun `Should update defaultTimeFrame when setPreferredTimeFrameUseCase is called`() = runTest {
        //WHEN
        var defaultPreferredTimeFrame = TimeFrame.ONE_DAY.value

        viewModel = BitcoinInfoViewModel(
            savedStateHandle = savedStateHandle,
            getBitcoinPriceUseCase = object : FakeGetBitcoinPriceUseCase() {
                override suspend fun invoke(): ResultOf<BitcoinPrice, CryptoPriceException> =
                    ResultOf.success(expectedBitcoinPrice())
            },
            preferredCurrencyUseCase = object : FakePreferredCurrencyUseCase() {
                override suspend fun getPreferredCurrency(): Flow<String?> =
                    MutableStateFlow(null)
            },
            preferredTimeFrameUseCase = object : FakePreferredTimeFrameUseCase() {
                override suspend fun getPreferredTimeFrame(): Flow<String?> =
                    MutableStateFlow(defaultPreferredTimeFrame)

                override suspend fun setPreferredTimeFrame(preferredTimeFrame: String) {
                    defaultPreferredTimeFrame = preferredTimeFrame
                }
            },
        )

        //THEN
        turbineScope {
            viewModel.bitcoinInfoUiState.test {
                skipItems(2) //Skip items initialization

                viewModel.onTimeFrameSelected(TimeFrame.TWO_WEEKS.value)
                viewModel.getBitcoinPrice()
                (awaitItem() as BitcoinInfo).run {
                    assertEquals(TimeFrame.TWO_WEEKS.value, preferredTimeFrame)
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