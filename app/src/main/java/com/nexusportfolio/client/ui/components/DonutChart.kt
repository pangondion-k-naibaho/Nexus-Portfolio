package com.nexusportfolio.client.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.nexusportfolio.client.data.model.DonutChartData
import kotlin.math.atan2

@Composable
fun DonutChart(data: List<DonutChartData>, onClick: (DonutChartData) -> Unit) {
    val colors = listOf(
        Color(0xFF81D4FA), // Light Blue
        Color(0xFFA5D6A7), // Light Green
        Color(0xFFFFAB91), // Light Orange
        Color(0xFFCE93D8)  // Light Purple
    ) // Generate and remember colors once

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(horizontal = 50.dp)
            .padding(top = 100.dp)
    ) {
        var canvasSize by remember { mutableStateOf(Size.Zero) }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val centerX = size.width / 2
                        val centerY = size.height / 2
                        val dx = offset.x - centerX
                        val dy = offset.y - centerY
                        val angle = (Math.toDegrees(atan2(dy.toDouble(), dx.toDouble())).toFloat() + 450) % 360
                        var cumulativeAngle = 0f

                        data.forEachIndexed { index, chartData ->
                            val sweepAngle = (chartData.percentage / 100).toFloat() * 360f
                            if (angle >= cumulativeAngle && angle < cumulativeAngle + sweepAngle) {
                                onClick(chartData)
                                return@detectTapGestures
                            }
                            cumulativeAngle += sweepAngle
                        }
                    }
                }
        ) {
            val radius = size.minDimension / 2
            var startAngle = -90f
            val strokeWidth = 200f // Adjust this value to make the donut hole smaller
            val textPaint = android.graphics.Paint().apply {
                color = android.graphics.Color.BLACK
                textAlign = android.graphics.Paint.Align.CENTER
                textSize = 30f // Adjust the text size as needed
                isAntiAlias = true
            }

            data.forEachIndexed { index, chartData ->
                val sweepAngle = (chartData.percentage / 100).toFloat() * 360f
                drawArc(
                    color = colors[index],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(strokeWidth)
                )

                // Calculate the position for the text
                val angle = Math.toRadians((startAngle + sweepAngle / 2).toDouble())
                val textX = (size.center.x + (radius - strokeWidth / 4) * Math.cos(angle)).toFloat()
                val textY = (size.center.y + (radius - strokeWidth / 4) * Math.sin(angle)).toFloat()

                // Calculate the offset to stack the texts
                val percentageOffset = 30f // Adjust as needed
                val labelOffset = percentageOffset + 30f // Adjust as needed to ensure text doesn't overlap

                // Draw the percentage at the top center of the chunk
                drawContext.canvas.nativeCanvas.drawText(
                    "${chartData.percentage}%",
                    textX,
                    textY - percentageOffset,
                    textPaint
                )

                // Draw the label below the percentage
                drawContext.canvas.nativeCanvas.drawText(
                    chartData.label,
                    textX,
                    textY - labelOffset,
                    textPaint
                )

                startAngle += sweepAngle
            }
        }
    }
}





