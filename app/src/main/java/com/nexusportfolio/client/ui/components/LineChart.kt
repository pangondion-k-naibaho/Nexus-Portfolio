package com.nexusportfolio.client.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.nexusportfolio.client.data.model.LineChartData

@Composable
fun LineChart(data: LineChartData) {
    val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val padding = 40f
            val maxY = (data.month.maxOrNull() ?: 0).toFloat()
            val minY = (data.month.minOrNull() ?: 0).toFloat()
            val yRange = maxY - minY
            val xInterval = (size.width - 2 * padding) / (data.month.size - 1)
            val yScale = (size.height - 2 * padding) / yRange

            // Draw the x and y axis lines
            drawLine(
                color = Color.Gray,
                start = Offset(padding, size.height - padding),
                end = Offset(size.width - padding, size.height - padding),
                strokeWidth = 2f
            )

            drawLine(
                color = Color.Gray,
                start = Offset(padding, padding),
                end = Offset(padding, size.height - padding),
                strokeWidth = 2f
            )

            // Draw x-axis labels
            data.month.forEachIndexed { index, _ ->
                val x = padding + index * xInterval
                drawContext.canvas.nativeCanvas.drawText(
                    months.getOrNull(index) ?: (index + 1).toString(),
                    x,
                    size.height - padding / 2,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textAlign = android.graphics.Paint.Align.CENTER
                        textSize = 24f
                    }
                )
            }

            // Draw y-axis labels
            val yLabelCount = 5
            for (i in 0..yLabelCount) {
                val y = padding + i * (size.height - 2 * padding) / yLabelCount
                val yValue = maxY - i * yRange / yLabelCount
                drawContext.canvas.nativeCanvas.drawText(
                    yValue.toInt().toString(),
                    padding / 2,
                    y,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textAlign = android.graphics.Paint.Align.RIGHT
                        textSize = 24f
                    }
                )
            }

            // Draw the line chart
            data.month.forEachIndexed { index, value ->
                if (index < data.month.size - 1) {
                    val startX = padding + index * xInterval
                    val startY = size.height - padding - ((value - minY) * yScale)
                    val endX = padding + (index + 1) * xInterval
                    val endY = size.height - padding - ((data.month[index + 1] - minY) * yScale)

                    drawLine(
                        color = Color.Blue,
                        start = Offset(startX, startY),
                        end = Offset(endX, endY),
                        strokeWidth = 4f
                    )
                }
            }
        }
    }
}


