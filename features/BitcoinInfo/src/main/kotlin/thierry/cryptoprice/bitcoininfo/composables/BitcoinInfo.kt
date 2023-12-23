package thierry.cryptoprice.bitcoininfo.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel

@Composable
internal fun BitcoinInfo(
    modifier: Modifier = Modifier,
    viewModel: BitcoinInfoViewModel = hiltViewModel()
) {
    when (val uiState = viewModel.bitcoinInfoUiState.collectAsStateWithLifecycle().value) {

        is BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo -> BitcoinInfoScreen(
            modifier = modifier,
            uiState = uiState,
        )

        is BitcoinInfoViewModel.BitcoinInfoUiState.Error -> BitcoinInfoScreenError(
            modifier = modifier,
            onRetry = viewModel::retry
        )

        BitcoinInfoViewModel.BitcoinInfoUiState.Loading -> Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
