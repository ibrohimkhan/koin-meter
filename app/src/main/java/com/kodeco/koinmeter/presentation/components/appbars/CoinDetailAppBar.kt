package com.kodeco.koinmeter.presentation.components.appbars

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme


@Composable
fun CoinDetailAppBar(
    coin: Coin,
    isFavoriteCoin: Boolean,
    onBackClicked: () -> Unit,
    addFavoriteCoin: (Coin) -> Unit,
    deleteFavoriteCoin: (Coin) -> Unit
) {
    var isFavorite by rememberSaveable { mutableStateOf(isFavoriteCoin) }

    val rotationAnimation = animateFloatAsState(
        label = "rotationAnimation",
        targetValue = if (isFavorite) 360f else 0f
    )

    val painter =
        if (isFavorite) painterResource(id = R.drawable.star_filled)
        else painterResource(id = R.drawable.star_outline)

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = coin.name ?: stringResource(R.string.unknown),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )

                IconButton(
                    onClick = {
                        isFavorite = !isFavorite

                        if (isFavorite) addFavoriteCoin(coin)
                        else deleteFavoriteCoin(coin)
                    },
                    modifier = Modifier
                        .size(24.dp)
                        .graphicsLayer(rotationZ = rotationAnimation.value)
                ) {
                    Icon(
                        painter = painter,
                        contentDescription = stringResource(R.string.favorite)
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.arrow_back)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CoinDetailAppBarPreview() {
    KoinMeterTheme {
        CoinDetailAppBar(
            coin = Coin(
                id = "bitcoin",
                name = "Bitcoin",
                symbol = "BTC",
                image = "https://example.com/bitcoin.png",
                currentPrice = 70000.0
            ),
            isFavoriteCoin = true,
            onBackClicked = {},
            addFavoriteCoin = {},
            deleteFavoriteCoin = {}
        )
    }
}
