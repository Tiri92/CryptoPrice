package thierry.cryptoprice.bitcoininfo.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import thierry.cryptoprice.bitcoininfo.composables.BitcoinInfo

const val MainScreenRoute = "main_screen"

fun NavGraphBuilder.mainScreen(

) {
    composable(
        route = MainScreenRoute,
    ) {
        BitcoinInfo(Modifier.fillMaxSize())
    }
}