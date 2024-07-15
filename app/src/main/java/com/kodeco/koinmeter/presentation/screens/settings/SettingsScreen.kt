package com.kodeco.koinmeter.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.domain.model.TimeFrame
import com.kodeco.koinmeter.presentation.components.appbars.CommonAppBar
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    onAboutIconClicked: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CommonAppBar(
                title = stringResource(R.string.settings),
                imageVector = Icons.Default.Info,
                onIconClicked = onAboutIconClicked
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) { paddingValues ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
        ) {
            Text(
                text = "Price Change Percentage Period",
                style = MaterialTheme.typography.titleMedium,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = Modifier.padding(8.dp)
            )

            TimeFrame.entries.forEach { timeFrame ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .selectable(
                            selected = uiState.timeFrame == timeFrame,
                            onClick = {
                                viewModel.processIntent(
                                    SettingsIntent.SetTimeFrame(timeFrame)
                                )
                            },
                            role = Role.RadioButton
                        )
                ) {
                    Text(
                        text = timeFrame.value.title,
                        modifier = Modifier.weight(0.5f)
                    )

                    RadioButton(
                        selected = uiState.timeFrame == timeFrame,
                        onClick = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    KoinMeterTheme {
        SettingsScreen(
            onAboutIconClicked = {}
        )
    }
}
