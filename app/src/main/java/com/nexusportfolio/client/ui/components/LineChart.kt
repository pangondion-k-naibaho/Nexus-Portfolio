package com.nexusportfolio.client.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.nexusportfolio.client.data.model.LineChartData

//@Composable
//fun LineChart(lineChartData: LineChartData) {
//    Canvas(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//    ) {
//        val maxX = size.width
//        val maxY = size.height
//
//        // Menghitung jumlah titik data
//        val numPoints = lineChartData.month.size
//
//        // Menghitung panjang interval horizontal dan vertikal
//        val intervalX = maxX / (numPoints - 1)
//        val intervalY = maxY / 12 // karena data month antara 1-12
//
//        // Membuat path untuk menggambar garis
//        val path = Path()
//        path.moveTo(0f, maxY - lineChartData.month[0] * intervalY)
//
//        // Menggambar garis berdasarkan titik data
//        for (i in 1 until numPoints) {
//            path.lineTo(i * intervalX, maxY - lineChartData.month[i] * intervalY)
//        }
//
//        // Menggambar garis
//        drawPath(path = path, color = Color.Blue, alpha = 0.8f)
//    }
//}
//@Composable
//fun LineChart(data: LineChartData) {
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .height(200.dp)
//        .padding(16.dp)
//    ) {
//        // Implement Line Chart UI
//    }
//}

@Composable
fun LineChart(data: LineChartData) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(16.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val padding = 20f
            val maxY = (data.month.maxOrNull() ?: 0).toFloat()
            val minY = (data.month.minOrNull() ?: 0).toFloat()
            val yRange = maxY - minY
            val xInterval = (size.width - 2 * padding) / (data.month.size - 1)
            val yScale = (size.height - 2 * padding) / yRange

            // Draw the line
            data.month.forEachIndexed { index, value ->
                if (index < data.month.size - 1) {
                    val startX = padding + index * xInterval
                    val startY = size.height - padding - ((value - minY) * yScale)
                    val endX = padding + (index + 1) * xInterval
                    val endY = size.height - padding - ((data.month[index + 1] - minY) * yScale)

                    drawLine(
                        color = Color.Blue,
                        start = androidx.compose.ui.geometry.Offset(startX, startY),
                        end = androidx.compose.ui.geometry.Offset(endX, endY),
                        strokeWidth = 4f
                    )
                }
            }
        }
    }
}
