package thierry.cryptoprice.bitcoininfo.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.BitcoinInfo
import thierry.cryptoprice.bitcoininfo.BitcoinInfoViewModel.BitcoinInfoUiState.TimeFrame
import thierry.cryptoprice.bitcoininfo.R

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun BitcoinInfoScreen(
    onCurrencySelected: (selectedCurrency: String) -> Unit,
    modifier: Modifier = Modifier,
    uiState: BitcoinInfo,
    onChipSelected: (String) -> Unit,
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
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
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
                            availableCurrenciesList = uiState.availableCurrenciesList,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(id = R.string.ath),
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = uiState.ath,
                        color = Color.Green,
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = uiState.preferredCurrency,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = stringResource(id = R.string.highest_price_24h),
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = uiState.btcHigh24h,
                        color = Color.Green,
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = uiState.preferredCurrency,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = stringResource(id = R.string.lowest_price_24h),
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = uiState.btcLow24h,
                        color = Color.Red,
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = uiState.preferredCurrency,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    TimeFrame.entries.forEach {
                        FilterChip(
                            onClick = {
                                onChipSelected(it.value)
                            },
                            label = {
                                Text(it.value)
                            },
                            selected = it.value == uiState.preferredTimeFrame,
                            leadingIcon = if (it.value == uiState.preferredTimeFrame) {
                                {
                                    Icon(
                                        imageVector = Icons.Filled.Done,
                                        contentDescription = null,
                                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                                    )
                                }
                            } else {
                                null
                            },
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = when (uiState.preferredTimeFrame) {
                        TimeFrame.ONE_HOUR.value -> "${uiState.priceChangePercentage1h}%"
                        TimeFrame.ONE_DAY.value -> "${uiState.priceChangePercentage1d}%"
                        TimeFrame.ONE_WEEK.value -> "${uiState.priceChangePercentage1w}%"
                        TimeFrame.TWO_WEEKS.value -> "${uiState.priceChangePercentage2w}%"
                        TimeFrame.ONE_MONTH.value -> "${uiState.priceChangePercentage1m}%"
                        TimeFrame.TWO_MONTHS.value -> "${uiState.priceChangePercentage2m}%"
                        TimeFrame.TWO_HUNDRED_DAYS.value -> "${uiState.priceChangePercentage200d}%"
                        TimeFrame.ONE_YEAR.value -> "${uiState.priceChangePercentage1y}%"
                        else -> "${uiState.priceChangePercentage1d}%"
                    },
                    color = when (uiState.preferredTimeFrame) {
                        TimeFrame.ONE_HOUR.value -> if (uiState.isPriceChangePercentage1hPositive)
                            Color.Green
                        else
                            Color.Red

                        TimeFrame.ONE_DAY.value -> if (uiState.isPriceChangePercentage1dPositive)
                            Color.Green
                        else
                            Color.Red

                        TimeFrame.ONE_WEEK.value -> if (uiState.isPriceChangePercentage1wPositive)
                            Color.Green
                        else
                            Color.Red

                        TimeFrame.TWO_WEEKS.value -> if (uiState.isPriceChangePercentage2wPositive)
                            Color.Green
                        else
                            Color.Red

                        TimeFrame.ONE_MONTH.value -> if (uiState.isPriceChangePercentage1mPositive)
                            Color.Green
                        else
                            Color.Red

                        TimeFrame.TWO_MONTHS.value -> if (uiState.isPriceChangePercentage2mPositive)
                            Color.Green
                        else
                            Color.Red

                        TimeFrame.TWO_HUNDRED_DAYS.value -> if (uiState.isPriceChangePercentage200dPositive)
                            Color.Green
                        else
                            Color.Red

                        TimeFrame.ONE_YEAR.value -> if (uiState.isPriceChangePercentage1yPositive)
                            Color.Green
                        else
                            Color.Red

                        else -> if (uiState.isPriceChangePercentage1dPositive)
                            Color.Green
                        else
                            Color.Red

                    }
                )
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
        uiState = BitcoinInfo(
            availableCurrenciesList = listOf(),
            btcPrice = "500 000",
            preferredCurrency = "eur",
            ath = "2000000",
            btcName = "Bitcoin",
            btcHigh24h = "1 500 000",
            priceChangePercentage1h = "100",
            priceChangePercentage1d = "100",
            priceChangePercentage1w = "100",
            priceChangePercentage2w = "100",
            priceChangePercentage1m = "100",
            priceChangePercentage2m = "100",
            priceChangePercentage200d = "100",
            priceChangePercentage1y = "10000",
            btcLow24h = "499 999",
        ),
        onCurrencySelected = {},
        onChipSelected = {},
    )
    // }
}