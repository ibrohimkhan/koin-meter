package com.kodeco.koinmeter.presentation.components.appbars

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme


@Composable
fun FavoriteCoinsAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.favorites),
                style = MaterialTheme.typography.titleMedium,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier.padding(8.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FavoriteCoinsAppBarPreview() {
    KoinMeterTheme {
        FavoriteCoinsAppBar()
    }
}
