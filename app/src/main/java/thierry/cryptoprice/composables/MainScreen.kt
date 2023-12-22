package thierry.cryptoprice.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import thierry.cryptoprice.MainActivityViewModel

@Composable
fun MainScreenInfo(
    modifier: Modifier = Modifier,
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    when (val uiState = viewModel.mainScreenUiState.collectAsStateWithLifecycle().value) {

        is MainActivityViewModel.MainScreenUiState.MainScreenInfo -> MainScreenInfo(
            modifier = modifier,
            uiState = uiState,
        )

        is MainActivityViewModel.MainScreenUiState.Error -> MainScreenError(
            modifier = modifier,
            onRetry = viewModel::retry
        )

        MainActivityViewModel.MainScreenUiState.Loading -> Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
