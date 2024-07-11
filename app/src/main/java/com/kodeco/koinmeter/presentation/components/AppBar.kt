package com.kodeco.koinmeter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme

@Composable
fun TopCoinsAppBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = stringResource(R.string.powered_by),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_coingecko),
                    contentDescription = stringResource(R.string.coingecko),
                    modifier = Modifier.width(100.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopCoinsAppBarPreview() {
    KoinMeterTheme {
        TopCoinsAppBar()
    }
}

@Composable
fun FavoriteCoinsAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.favorites),
                style = MaterialTheme.typography.titleMedium,
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
