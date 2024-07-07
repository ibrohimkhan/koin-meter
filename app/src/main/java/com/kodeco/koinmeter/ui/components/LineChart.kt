package com.kodeco.koinmeter.ui.components

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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeco.koinmeter.model.MarketChartPrice
import com.kodeco.koinmeter.ui.theme.KoinMeterTheme
import java.time.LocalDateTime
import kotlin.math.round
import kotlin.math.roundToInt


@Composable
fun LineChart(
    marketData: List<MarketChartPrice>,
    graphColor: Color,
    modifier: Modifier = Modifier,
) {
    val spacing = 100f

    val transparentGraphColor = remember {
        graphColor.copy(alpha = 0.5f)
    }

    val upperValue = remember(marketData) {
        (marketData.maxOfOrNull { it.price }?.plus(1))?.roundToInt() ?: 0
    }

    val lowerValue = remember(marketData) {
        marketData.minOfOrNull { it.price }?.toInt() ?: 0
    }

    val density = LocalDensity.current

    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {
        val spacePerHour = (size.width - spacing) / marketData.size

        (0 until marketData.size - 1 step 2).forEach { i ->
            val info = marketData[i]
            val hour = info.date.hour

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hour.toString(),
                    spacing + i * spacePerHour,
                    size.height - 5,
                    textPaint
                )
            }
        }

        val priceStep = (upperValue - lowerValue) / 5f

        (0..4).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    round(lowerValue + priceStep * i).toString(),
                    30f,
                    size.height - spacing - i * size.height / 5f,
                    textPaint
                )
            }
        }

        var lastX = 0f

        val strokePath = Path().apply {
            val height = size.height

            for (i in marketData.indices) {
                val info = marketData[i]
                val nextInfo = marketData.getOrNull(i + 1) ?: marketData.last()
                val leftRatio = (info.price - lowerValue) / (upperValue - lowerValue)
                val rightRatio = (nextInfo.price - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * spacePerHour
                val y1 = height - spacing - (leftRatio * height).toFloat()
                val x2 = spacing + (i + 1) * spacePerHour
                val y2 = height - spacing - (rightRatio * height).toFloat()

                if (i == 0) {
                    moveTo(x1, y1)
                }

                lastX = (x1 + x2) / 2f
                quadraticBezierTo(
                    x1, y1, lastX, (y1 + y2) / 2f
                )
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
    }
}

@Preview(showBackground = true)
@Composable
fun LineChartPreview() {
    KoinMeterTheme {
        LineChart(
            marketData = listOf(
                MarketChartPrice(LocalDateTime.now(), 113.50),
                MarketChartPrice(LocalDateTime.now(), 150.0),
                MarketChartPrice(LocalDateTime.now(), 150.0),
                MarketChartPrice(LocalDateTime.now(), 150.0),
                MarketChartPrice(LocalDateTime.now(), 213.50),
                MarketChartPrice(LocalDateTime.now(), 313.50),
                MarketChartPrice(LocalDateTime.now(), 113.50),
                MarketChartPrice(LocalDateTime.now(), 150.50),
                MarketChartPrice(LocalDateTime.now(), 170.50),
                MarketChartPrice(LocalDateTime.now(), 213.50),
            ),
            graphColor = Color.Red,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        )
    }
}
