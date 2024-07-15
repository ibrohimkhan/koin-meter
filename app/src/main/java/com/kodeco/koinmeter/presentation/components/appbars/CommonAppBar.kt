package com.kodeco.koinmeter.presentation.components.appbars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.koinmeter.R
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme


@Composable
fun CommonAppBar(
    title: String,
    imageVector: ImageVector? = null,
    onIconClicked: () -> Unit = {},
    onBackClicked: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    modifier = Modifier.weight(1f)
                )

                imageVector?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = stringResource(R.string.about),
                        modifier = Modifier.clickable { onIconClicked() }
                    )
                }
            }
        },
        navigationIcon = {
            onBackClicked?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.arrow_back)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CommonAppBarPreview() {
    KoinMeterTheme {
        CommonAppBar(stringResource(R.string.favorites))
    }
}
