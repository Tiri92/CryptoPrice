package thierry.cryptoprice.bitcoininfo.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun BitcoinInfo(
    modifier: Modifier = Modifier,
    viewModel: BitcoinInfoViewModel = hiltViewModel()
) {
    when (val uiState = viewModel.bitcoinInfoUiState.collectAsStateWithLifecycle().value) {

        BitcoinInfoViewModel.BitcoinInfoUiState.Loading -> Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo -> {
            val pullRefreshState = rememberPullRefreshState(
                refreshing = uiState.isPullToRefreshLoading,
                onRefresh = viewModel::pullRefreshBitcoinPrice
            )

            Box {
                BitcoinInfoScreen(
                    modifier = modifier
                        .pullRefresh(pullRefreshState)
                        .verticalScroll(
                            rememberScrollState()
                        ),
                    uiState = uiState,
                    onCurrencySelected = viewModel::setPreferredCurrency,
                )

                PullRefreshIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    refreshing = uiState.isPullToRefreshLoading,
                    state = pullRefreshState,
                )
            }
        }

        is BitcoinInfoViewModel.BitcoinInfoUiState.Error -> BitcoinInfoScreenError(
            modifier = modifier,
            onRetry = viewModel::retryGetBitcoinPrice
        )
    }
}
