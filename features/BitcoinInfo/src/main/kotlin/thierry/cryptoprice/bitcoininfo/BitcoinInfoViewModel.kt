package thierry.cryptoprice.bitcoininfo

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import thierry.cryptoprice.getbitcoinpriceusecase.GetBitcoinPriceUseCase
import thierry.cryptoprice.resultof.mapFailure
import thierry.cryptoprice.resultof.mapSuccess
import javax.inject.Inject

private const val BITCOIN_INFO_UI_STATE = "bitcoin_info_ui_state"

@HiltViewModel
class BitcoinInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getBitcoinPriceUseCase: GetBitcoinPriceUseCase,
) : ViewModel() { //TODO add test to vm (don't forget to fake uc instead of mocking it)

    val bitcoinInfoUiState: StateFlow<BitcoinInfoUiState> = savedStateHandle.getStateFlow(
        BITCOIN_INFO_UI_STATE,
        BitcoinInfoUiState.Loading
    )

    init {
        getBitcoinPrice()
    }

    private fun getBitcoinPrice() =
        viewModelScope.launch {
            getBitcoinPriceUseCase()
                .mapSuccess {
                    savedStateHandle[BITCOIN_INFO_UI_STATE] = BitcoinInfoUiState.BitcoinInfo(
                        btcPrice = it.market_data.current_price.eur.toString(),
                        btcName = it.name,
                    )
                }.mapFailure {
                    savedStateHandle[BITCOIN_INFO_UI_STATE] =
                        BitcoinInfoUiState.Error
                }
        }

    internal fun retry() {
        savedStateHandle[BITCOIN_INFO_UI_STATE] = BitcoinInfoUiState.Loading
        getBitcoinPrice()
    }

    sealed interface BitcoinInfoUiState : Parcelable {

        @Immutable
        @Parcelize
        data object Loading : BitcoinInfoUiState

        @Immutable
        @Parcelize
        data class BitcoinInfo(
            val btcPrice: String,
            val btcName: String,
        ) : BitcoinInfoUiState

        @Immutable
        @Parcelize
        data object Error : BitcoinInfoUiState
    }
}