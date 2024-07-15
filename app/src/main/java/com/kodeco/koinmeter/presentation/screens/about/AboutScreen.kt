package com.kodeco.koinmeter.presentation.screens.about

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.presentation.components.Info

@Composable
fun AboutScreen(
    appBar: @Composable () -> Unit,
    onBackClicked: () -> Unit
) {
    BackHandler {
        onBackClicked()
    }

    Scaffold(
        topBar = appBar,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.coingecko_logo),
                contentDescription = "CoinGecko Logo",
                modifier = Modifier.size(170.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.source_code),
                style = MaterialTheme.typography.titleMedium,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Info(
                    image = painterResource(id = R.drawable.github),
                    title = "GitHub",
                    description = "https://github.com/ibrohimkhan/koin-meter"
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(
                            color = Color.Gray.copy(alpha = 0.5f),
                        )
                )

                Info(
                    image = painterResource(id = R.drawable.github),
                    title = "Issues",
                    description = "https://github.com/ibrohimkhan/koin-meter/issues"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.contact),
                style = MaterialTheme.typography.titleMedium,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Info(
                    image = rememberVectorPainter(image = Icons.Outlined.Email),
                    title = "Email",
                    description = "ibrohimkhan@gmail.com"
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(
                            color = Color.Gray.copy(alpha = 0.5f),
                        )
                )

                Info(
                    image = painterResource(id = R.drawable.stackoverflow),
                    title = "Stack Overflow",
                    description = "https://stackoverflow.com/users/5652315/ibrokhim-kholmatov"
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(
                            color = Color.Gray.copy(alpha = 0.5f),
                        )
                )

                Info(
                    image = painterResource(id = R.drawable.leetcode),
                    title = "Leetcode",
                    description = "https://leetcode.com/u/ibrohimkhan/"
                )
            }
        }
    }
}
