package thierry.cryptoprice.bitcoininfo

import android.os.Parcelable
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.Error
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.Loading
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.TimeFrame
import thierry.cryptoprice.getbitcoinpriceusecase.GetBitcoinPriceUseCase
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrencyPrices
import thierry.cryptoprice.preferredcurrencyusecase.PreferredCurrencyUseCase
import thierry.cryptoprice.resultof.mapFailure
import thierry.cryptoprice.resultof.mapSuccess
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

private const val BITCOIN_INFO_UI_STATE = "bitcoin_info_ui_state"
private const val DEFAULT_CURRENCY = "eur"

@HiltViewModel
class BitcoinInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getBitcoinPriceUseCase: GetBitcoinPriceUseCase,
    private val preferredCurrencyUseCase: PreferredCurrencyUseCase,
) : ViewModel() {

    val bitcoinInfoUiState: StateFlow<BitcoinInfoUiState> = savedStateHandle.getStateFlow(
        BITCOIN_INFO_UI_STATE,
        Loading
    )

    init {
        getBitcoinPrice()
    }

    @VisibleForTesting
    fun getBitcoinPrice() =
        viewModelScope.launch {
            getBitcoinPriceUseCase().mapSuccess { bitcoinPrice ->
                preferredCurrencyUseCase.getPreferredCurrencyUseCase()
                    .collect { preferredCurrency: String? ->

                        val btcPrice = CurrencyPrices::class.memberProperties.find { predicate ->
                            preferredCurrency == predicate.name
                        }

                        val availableCurrenciesList = mutableListOf<String>()
                        CurrencyPrices::class.memberProperties.forEach { currentPrice ->
                            availableCurrenciesList.add(currentPrice.name)
                        }

                        savedStateHandle[BITCOIN_INFO_UI_STATE] =
                            BitcoinInfo(
                                btcName = bitcoinPrice.name,
                                btcPrice = btcPrice?.get(bitcoinPrice.marketData.currencyPrices)
                                    ?.toString()
                                    ?: bitcoinPrice.marketData.currencyPrices.eur.toString(),
                                ath = btcPrice?.get(bitcoinPrice.marketData.ath)?.toString()
                                    ?: bitcoinPrice.marketData.ath.eur.toString(),
                                btcHigh24h = btcPrice?.get(bitcoinPrice.marketData.high24h)
                                    ?.toString()
                                    ?: bitcoinPrice.marketData.high24h.eur.toString(),
                                btcLow24h = btcPrice?.get(bitcoinPrice.marketData.low24h)
                                    ?.toString()
                                    ?: bitcoinPrice.marketData.low24h.eur.toString(),
                                priceChangePercentage1h = btcPrice?.get(
                                    bitcoinPrice.marketData
                                        .priceChangePercentage1hInCurrency
                                )?.toString()
                                    ?: bitcoinPrice.marketData.priceChangePercentage1hInCurrency.eur.toString(),
                                priceChangePercentage1d = btcPrice?.get(
                                    bitcoinPrice.marketData
                                        .priceChangePercentage24hInCurrency
                                )?.toString()
                                    ?: bitcoinPrice.marketData.priceChangePercentage24hInCurrency.eur.toString(),
                                priceChangePercentage1w = btcPrice?.get(
                                    bitcoinPrice.marketData
                                        .priceChangePercentage7dInCurrency
                                )?.toString()
                                    ?: bitcoinPrice.marketData.priceChangePercentage7dInCurrency.eur.toString(),
                                priceChangePercentage2w = btcPrice?.get(
                                    bitcoinPrice.marketData
                                        .priceChangePercentage14dInCurrency
                                )?.toString()
                                    ?: bitcoinPrice.marketData.priceChangePercentage14dInCurrency.eur.toString(),
                                priceChangePercentage1m = btcPrice?.get(
                                    bitcoinPrice.marketData
                                        .priceChangePercentage30dInCurrency
                                )?.toString()
                                    ?: bitcoinPrice.marketData.priceChangePercentage30dInCurrency.eur.toString(),
                                priceChangePercentage2m = btcPrice?.get(
                                    bitcoinPrice.marketData
                                        .priceChangePercentage60dInCurrency
                                )?.toString()
                                    ?: bitcoinPrice.marketData.priceChangePercentage60dInCurrency.eur.toString(),
                                priceChangePercentage200d = btcPrice?.get(
                                    bitcoinPrice.marketData
                                        .priceChangePercentage200dInCurrency
                                )?.toString()
                                    ?: bitcoinPrice.marketData.priceChangePercentage200dInCurrency.eur.toString(),
                                priceChangePercentage1y = btcPrice?.get(
                                    bitcoinPrice.marketData
                                        .priceChangePercentage1yInCurrency
                                )?.toString()
                                    ?: bitcoinPrice.marketData.priceChangePercentage1yInCurrency.eur.toString(),
                                preferredCurrency = btcPrice?.name ?: DEFAULT_CURRENCY,
                                availableCurrenciesList = availableCurrenciesList,
                                isPullToRefreshLoading = false,
                            )
                    }
            }.mapFailure {
                savedStateHandle[BITCOIN_INFO_UI_STATE] =
                    Error
            }
        }

    internal fun retryGetBitcoinPrice() {
        savedStateHandle[BITCOIN_INFO_UI_STATE] = Loading
        getBitcoinPrice()
    }

    internal fun pullRefreshBitcoinPrice() {
        (savedStateHandle[BITCOIN_INFO_UI_STATE]) =
            (bitcoinInfoUiState.value as BitcoinInfo)
                .copy(isPullToRefreshLoading = true)

        getBitcoinPrice()
    }

    internal fun setPreferredCurrency(preferredCurrency: String) =
        viewModelScope.launch {
            preferredCurrencyUseCase.setPreferredCurrencyUseCase(preferredCurrency)
        }

    internal fun onChipSelected(ship: String) { //Verify datastore to enable a ship on init if there is a user preference TODO
        when (ship) {
            TimeFrame.ONE_HOUR.value -> savedStateHandle[BITCOIN_INFO_UI_STATE] =
                (bitcoinInfoUiState.value as BitcoinInfo).copy(
                    preferredTimeFrame = TimeFrame.ONE_HOUR.value,
                )

            TimeFrame.ONE_DAY.value -> savedStateHandle[BITCOIN_INFO_UI_STATE] =
                (bitcoinInfoUiState.value as BitcoinInfo).copy(
                    preferredTimeFrame = TimeFrame.ONE_DAY.value,
                )

            TimeFrame.ONE_WEEK.value -> savedStateHandle[BITCOIN_INFO_UI_STATE] =
                (bitcoinInfoUiState.value as BitcoinInfo).copy(
                    preferredTimeFrame = TimeFrame.ONE_WEEK.value,
                )

            TimeFrame.TWO_WEEKS.value -> savedStateHandle[BITCOIN_INFO_UI_STATE] =
                (bitcoinInfoUiState.value as BitcoinInfo).copy(
                    preferredTimeFrame = TimeFrame.TWO_WEEKS.value,
                )

            TimeFrame.ONE_MONTH.value -> savedStateHandle[BITCOIN_INFO_UI_STATE] =
                (bitcoinInfoUiState.value as BitcoinInfo).copy(
                    preferredTimeFrame = TimeFrame.ONE_MONTH.value,
                )

            TimeFrame.TWO_MONTHS.value -> savedStateHandle[BITCOIN_INFO_UI_STATE] =
                (bitcoinInfoUiState.value as BitcoinInfo).copy(
                    preferredTimeFrame = TimeFrame.TWO_MONTHS.value,
                )

            TimeFrame.TWO_HUNDRED_DAYS.value -> savedStateHandle[BITCOIN_INFO_UI_STATE] =
                (bitcoinInfoUiState.value as BitcoinInfo).copy(
                    preferredTimeFrame = TimeFrame.TWO_HUNDRED_DAYS.value,
                )

            TimeFrame.ONE_YEAR.value -> savedStateHandle[BITCOIN_INFO_UI_STATE] =
                (bitcoinInfoUiState.value as BitcoinInfo).copy(
                    preferredTimeFrame = TimeFrame.ONE_YEAR.value,
                )
        }
    }

    sealed interface BitcoinInfoUiState : Parcelable {

        @Immutable
        @Parcelize
        data object Loading : BitcoinInfoUiState

        @Immutable
        @Parcelize
        data class BitcoinInfo(
            val btcName: String,
            val btcPrice: String,
            val ath: String,
            val btcHigh24h: String,
            val btcLow24h: String,
            val priceChangePercentage1h: String,
            val priceChangePercentage1d: String,
            val priceChangePercentage1w: String,
            val priceChangePercentage2w: String,
            val priceChangePercentage1m: String,
            val priceChangePercentage2m: String,
            val priceChangePercentage200d: String,
            val priceChangePercentage1y: String,
            val preferredTimeFrame: String = TimeFrame.ONE_DAY.value,
            val preferredCurrency: String,
            val availableCurrenciesList: List<String>,
            val isPullToRefreshLoading: Boolean = false,
        ) : BitcoinInfoUiState

        @Immutable
        @Parcelize
        data object Error : BitcoinInfoUiState

        enum class TimeFrame(val value: String) {
            ONE_HOUR("1h"),
            ONE_DAY("1d"),
            ONE_WEEK("1w"),
            TWO_WEEKS("2w"),
            ONE_MONTH("1m"),
            TWO_MONTHS("2m"),
            TWO_HUNDRED_DAYS("200d"),
            ONE_YEAR("1y"),
        }
    }
}