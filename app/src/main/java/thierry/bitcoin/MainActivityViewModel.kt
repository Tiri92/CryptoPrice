package thierry.bitcoin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import thierry.bitcoin.getbitcoinpriceusecase.GetBitcoinPriceUseCase
import thierry.bitcoin.getbitcoinpriceusecase.model.BitcoinPrice
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
   private val getBitcoinPriceUseCase: GetBitcoinPriceUseCase,
): ViewModel() {

    private val _btcPrice = MutableStateFlow<BitcoinPrice?>(value = null)
     val btcPrice = _btcPrice.asStateFlow()

    init {
        getBitcoinPrice()
    }

   private fun getBitcoinPrice(): String? {
        var t :  String? = null
        viewModelScope.launch {
            t = getBitcoinPriceUseCase.invoke()?.market_data?.current_price.toString()
            _btcPrice.emit(getBitcoinPriceUseCase())
        }
        return t
    }
}