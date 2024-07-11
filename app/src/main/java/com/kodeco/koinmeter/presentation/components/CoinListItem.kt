package com.kodeco.koinmeter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.domain.model.Coin
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .clickable { onItemClicked(coin.id) }
    ) {

        coin.image?.let {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it)
                    .crossfade(true)
                    .build(),
                contentDescription = coin.id,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(8.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .clip(shape = CircleShape)
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(8.dp).weight(0.6f)
        ) {
            Text(
                text = coin.name ?: stringResource(R.string.unknown),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "${coin.marketCapRank ?: stringResource(R.string.unknown)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.outline,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(start = 8.dp, end = 8.dp)
                )

                Text(
                    text = coin.symbol?.uppercase() ?: stringResource(R.string.unknown),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(8.dp).weight(0.4f)
        ) {
            Text(
                text = "$ ${coin.currentPrice ?: stringResource(R.string.unknown)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 8.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${coin.priceChange24h ?: stringResource(R.string.unknown)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .background(
                        color = if ((coin.priceChange24h ?: 0.0) > 0) {
                            Color.Green
                        } else {
                            Color.Red
                        },
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(16.dp, 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinListItemPreview() {
    KoinMeterTheme {
        CoinListItem(
            coin = Coin(id = "bitcoin", name = "Bitcoin", symbol = "BTC", marketCapRank = 1),
            onItemClicked = {},
            modifier = Modifier.padding(8.dp)
        )
    }
}
