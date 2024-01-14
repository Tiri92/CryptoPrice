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
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrentPrice
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
}

private fun expectedBitcoinPrice(): BitcoinPrice =
    BitcoinPrice(
        id = "",
        market_data = MarketData(
            current_price = CurrentPrice(
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
            )
        ),
        name = "",
        symbol = ""
    )