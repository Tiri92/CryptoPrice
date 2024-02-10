package thierry.cryptoprice.bitcoininfo.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel
import thierry.cryptoprice.bitcoininfo.R

@Composable
internal fun BitcoinInfoScreen(
    onCurrencySelected: (selectedCurrency: String) -> Unit,
    modifier: Modifier = Modifier,
    uiState: BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo,
) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        var expanded by rememberSaveable { mutableStateOf(false) }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color.Gray,
            ),
            shadowElevation = 8.dp,
            tonalElevation = 8.dp,
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                Image(
                    painterResource(id = R.drawable.bitoin_logo),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "${uiState.btcName} ${uiState.btcPrice} ${uiState.preferredCurrency}",
                )

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                expanded = true
                            },
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )

                    DropDownMenu(
                        expanded = expanded,
                        onDismiss = {
                            expanded = false
                        },
                        onCurrencySelected = onCurrencySelected,
                        availableCurrenciesList = uiState.availableCurrenciesList
                    )
                }
            }
        }
    }
}

@Composable
private fun DropDownMenu(
    availableCurrenciesList: List<String>,
    expanded: Boolean,
    onDismiss: () -> Unit,
    onCurrencySelected: (selectedCurrency: String) -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismiss() }
    ) {
        availableCurrenciesList.forEach {
            DropdownMenuItem(
                text = { Text(it) },
                onClick = {
                    onCurrencySelected(it)
                    onDismiss()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenInfoPreview() { //TODO move it to debug folder and how to get Theme ?
    //BitcoinTheme {
    BitcoinInfoScreen(
        uiState = BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo(
            availableCurrenciesList = listOf(),
            btcPrice = "500 000",
            preferredCurrency = "eur",
            btcName = "Bitcoin",
        ),
        onCurrencySelected = {},
    )
    // }
}