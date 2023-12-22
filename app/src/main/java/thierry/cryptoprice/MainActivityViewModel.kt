package thierry.cryptoprice

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

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getBitcoinPriceUseCase: GetBitcoinPriceUseCase,
) : ViewModel() { //TODO add test to vm (don't forget to fake uc instead of mocking it)

    val mainScreenUiState: StateFlow<MainScreenUiState> = savedStateHandle.getStateFlow(
        MAIN_SCREEN_UI_STATE,
        MainScreenUiState.Loading
    )

    init {
        getBitcoinPrice()
    }

    private fun getBitcoinPrice() =
        viewModelScope.launch {
            getBitcoinPriceUseCase()
                .mapSuccess {
                    savedStateHandle[MAIN_SCREEN_UI_STATE] = MainScreenUiState.MainScreenInfo(
                        btcPrice = it.market_data.current_price.eur.toString(),
                        btcName = it.name,
                    )
                }.mapFailure {
                    savedStateHandle[MAIN_SCREEN_UI_STATE] =
                        MainScreenUiState.Error
                }
        }

    internal fun retry() {
        savedStateHandle[MAIN_SCREEN_UI_STATE] = MainScreenUiState.Loading
        getBitcoinPrice()
    }

    sealed interface MainScreenUiState : Parcelable {

        @Immutable
        @Parcelize
        data object Loading : MainScreenUiState

        @Immutable
        @Parcelize
        data class MainScreenInfo(
            val btcPrice: String,
            val btcName: String,
        ) : MainScreenUiState

        @Immutable
        @Parcelize
        data object Error : MainScreenUiState
    }
}

private const val MAIN_SCREEN_UI_STATE = "main_screen_ui_state"
