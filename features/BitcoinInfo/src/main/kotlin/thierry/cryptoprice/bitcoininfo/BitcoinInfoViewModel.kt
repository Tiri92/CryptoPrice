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
import thierry.cryptoprice.getbitcoinpriceusecase.GetBitcoinPriceUseCase
import thierry.cryptoprice.getbitcoinpriceusecase.model.CurrentPrice
import thierry.cryptoprice.preferredcurrencyusecase.PreferredCurrencyUseCase
import thierry.cryptoprice.resultof.mapFailure
import thierry.cryptoprice.resultof.mapSuccess
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

private const val BITCOIN_INFO_UI_STATE = "bitcoin_info_ui_state"

@HiltViewModel
class BitcoinInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getBitcoinPriceUseCase: GetBitcoinPriceUseCase,
    private val preferredCurrencyUseCase: PreferredCurrencyUseCase,
) : ViewModel() {

    val bitcoinInfoUiState: StateFlow<BitcoinInfoUiState> = savedStateHandle.getStateFlow(
        BITCOIN_INFO_UI_STATE,
        BitcoinInfoUiState.Loading
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

                        val btcPrice = CurrentPrice::class.memberProperties.find { predicate ->
                            preferredCurrency == predicate.name
                        }

                        val availableCurrenciesList = mutableListOf<String>()
                        CurrentPrice::class.memberProperties.forEach { currentPrice ->
                            availableCurrenciesList.add(currentPrice.name)
                        }

                        savedStateHandle[BITCOIN_INFO_UI_STATE] =
                            BitcoinInfoUiState.BitcoinInfo(
                                btcPrice = btcPrice?.get(bitcoinPrice.market_data.current_price)
                                    ?.toString()
                                    ?: bitcoinPrice.market_data.current_price.eur.toString(),
                                preferredCurrency = btcPrice?.name ?: "eur",
                                availableCurrenciesList = availableCurrenciesList,
                                btcName = bitcoinPrice.name,
                                isPullToRefreshLoading = false
                            )
                    }
            }.mapFailure {
                savedStateHandle[BITCOIN_INFO_UI_STATE] =
                    BitcoinInfoUiState.Error
            }
        }

    internal fun retryGetBitcoinPrice() {
        savedStateHandle[BITCOIN_INFO_UI_STATE] = BitcoinInfoUiState.Loading
        getBitcoinPrice()
    }

    internal fun pullRefreshBitcoinPrice() {
        (savedStateHandle[BITCOIN_INFO_UI_STATE]) =
            (bitcoinInfoUiState.value as BitcoinInfoUiState.BitcoinInfo)
                .copy(isPullToRefreshLoading = true)

        getBitcoinPrice()
    }

    internal fun setPreferredCurrency(preferredCurrency: String) =
        viewModelScope.launch {
            preferredCurrencyUseCase.setPreferredCurrencyUseCase(preferredCurrency)
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
            val preferredCurrency: String,
            val availableCurrenciesList: List<String>,
            val isPullToRefreshLoading: Boolean = false,
        ) : BitcoinInfoUiState

        @Immutable
        @Parcelize
        data object Error : BitcoinInfoUiState
    }
}