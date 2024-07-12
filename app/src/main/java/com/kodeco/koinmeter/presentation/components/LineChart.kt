package com.kodeco.koinmeter.presentation.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeco.koinmeter.domain.model.CoinMarketChartPrice
import com.kodeco.koinmeter.presentation.extensions.formatAsCurrency
import com.kodeco.koinmeter.presentation.ui.theme.KoinMeterTheme
import java.time.LocalDateTime

/*
* Inspired by:
* https://github.com/CoinTrend/CoinTrend/blob/develop/presentation/src/main/java/com/cointrend/presentation/customcomposables/LineChart.kt
*/

@Composable
fun LineChart(
    marketData: List<CoinMarketChartPrice>,
    graphColor: Color,
    modifier: Modifier = Modifier,
) {
    if (marketData.isEmpty()) return

    val spacing = 0f

    val transparentGraphColor = remember {
        graphColor.copy(alpha = 0.5f)
    }

    val (lowerValue, upperValue) = remember(marketData) {
        Pair(
            marketData.minOfOrNull { it.price } ?: 0.0,
            marketData.maxOfOrNull { it.price } ?: 0.0
        )
    }

    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.RIGHT
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {
        val spacePerHour = (size.width - spacing) / marketData.size

        var lastX = 0f
        var firstY = 0f

        val strokePath = Path().apply {
            val height = size.height

            for (i in marketData.indices) {
                val info = marketData[i]
                val nextInfo = marketData.getOrNull(i + 1) ?: marketData.last()

                val leftRatio = (info.price - lowerValue) / (upperValue - lowerValue)
                val rightRatio = (nextInfo.price - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * spacePerHour
                val y1 = height - spacing - (leftRatio * height).toFloat()

                if (i == 0) firstY = y1

                val x2 = spacing + (i + 1) * spacePerHour
                val y2 = height - spacing - (rightRatio * height).toFloat()

                if (i == 0) moveTo(x1, y1)

                lastX = (x1 + x2) / 2f
                quadraticBezierTo(x1, y1, lastX, (y1 + y2) / 2f)
            }
        }

        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(lastX, size.height - spacing)
                lineTo(spacing, size.height - spacing)
                close()
            }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )

        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        // Dashed Lines
        val dottedPath = Path().apply {
            moveTo(0f, firstY)
            lineTo(lastX, firstY)
        }

        drawPath(
            path = dottedPath,
            color = graphColor.copy(alpha = .8f),
            style = Stroke(
                width = 1.5.dp.toPx(),
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f), 0f)
            )
        )

        drawContext.canvas.nativeCanvas.apply {
            drawText(
                "MAX ${upperValue.formatAsCurrency()}",
                size.width - 16.dp.toPx(),
                0 + 8.dp.toPx(),
                textPaint
            )
            drawText(
                "MIN ${lowerValue.formatAsCurrency()}",
                size.width - 16.dp.toPx(),
                size.height - 4.dp.toPx(),
                textPaint
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LineChartPreview() {
    KoinMeterTheme {
        LineChart(
            marketData = listOf(
                CoinMarketChartPrice(LocalDateTime.now(), 113.50),
                CoinMarketChartPrice(LocalDateTime.now(), 150.0),
                CoinMarketChartPrice(LocalDateTime.now(), 150.0),
                CoinMarketChartPrice(LocalDateTime.now(), 150.0),
                CoinMarketChartPrice(LocalDateTime.now(), 213.50),
                CoinMarketChartPrice(LocalDateTime.now(), 313.50),
                CoinMarketChartPrice(LocalDateTime.now(), 113.50),
                CoinMarketChartPrice(LocalDateTime.now(), 150.50),
                CoinMarketChartPrice(LocalDateTime.now(), 170.50),
                CoinMarketChartPrice(LocalDateTime.now(), 213.50),
            ),
            graphColor = Color.Red,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        )
    }
}
