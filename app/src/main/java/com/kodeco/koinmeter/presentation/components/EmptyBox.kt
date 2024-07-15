package com.kodeco.koinmeter.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme

@Composable
fun EmptyBox(
    appbar: @Composable () -> Unit,
) {
    Scaffold(
        topBar = appbar,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_satellite_alt_24),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.empty_box_fav_coins),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyBoxPreview() {
    KoinMeterTheme {
        EmptyBox(
            appbar = {}
        )
    }
}
