package thierry.cryptoprice.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import thierry.cryptoprice.R
import thierry.cryptoprice.ui.theme.BitcoinTheme

@Composable
internal fun MainScreenError(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.error_occurred),
            fontStyle = FontStyle.Italic
        )

        Button(
            onClick = onRetry,
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenErrorPreview() { //TODO move it to debug folder ?
    BitcoinTheme {
        MainScreenError(
            onRetry = {}
        )
    }
}
