package com.nexusportfolio.client.ui.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexusportfolio.client.data.model.DonutChartData
import com.nexusportfolio.client.data.model.DonutChartDataCollection
import com.nexusportfolio.client.data.model.DrawingAngles
import com.nexusportfolio.client.data.model.Transaction
import com.nexusportfolio.client.ui.theme.blue
import com.nexusportfolio.client.ui.theme.green
import com.nexusportfolio.client.ui.theme.red
import java.lang.Math.cos
import java.lang.Math.sin
import kotlin.math.atan2
import kotlin.math.sqrt

//@Composable
//fun DonutChart(donutChartDatas: List<DonutChartData>, onClickChunk: (List<Transaction>) -> Unit) {
//    Box(
//        modifier = Modifier
//            .size(200.dp)
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onTap = { offset ->
//                        val radius = 200.dp.toPx() / 2
//                        val clickedTransactions = findClickedTransactions(offset, donutChartDatas, radius)
//                        if (clickedTransactions.isNotEmpty()) {
//                            onClickChunk(clickedTransactions)
//                        }
//                    }
//                )
//            }
//    ) {
//        Canvas(modifier = Modifier.matchParentSize()) {
//            val radius = size.minDimension / 2
//            var startAngle = -90f
//            donutChartDatas.forEach { donutChartData ->
//                val sweepAngle = donutChartData.percentage * 360 / 100
//                drawArc(
//                    color = getRandomColor(),
//                    startAngle = startAngle,
//                    sweepAngle = sweepAngle,
//                    useCenter = true,
//                    size = Size(size.width, size.height)
//                )
//
//                // Menggambar label dan persentase pada tiap chunk
//                val angle = startAngle + sweepAngle / 2
//                val labelX = (radius * cos(Math.toRadians(angle.toDouble()))).toFloat() + size.width / 2
//                val labelY = (radius * sin(Math.toRadians(angle.toDouble()))).toFloat() + size.height / 2
//                drawContext.canvas.nativeCanvas.drawText(
//                    "${donutChartData.label}: ${donutChartData.percentage}%",
//                    labelX,
//                    labelY,
//                    android.graphics.Paint().apply {
//                        textAlign = android.graphics.Paint.Align.CENTER
//                        textSize = 14.sp.toPx()
//                        color = android.graphics.Color.BLACK
//                    }
//                )
//
//                startAngle += sweepAngle
//            }
//        }
//    }
//}
//
//private fun findClickedTransactions(offset: Offset, data: List<DonutChartData>, radius: Float): List<Transaction> {
//    val center = Offset(radius, radius)
//    val touchAngle = Math.toDegrees(atan2((offset.y - center.y).toDouble(), (offset.x - center.x).toDouble())).toFloat() + 90
//    val adjustedAngle = if (touchAngle < 0) touchAngle + 360 else touchAngle
//
//    var startAngle = 0f
//    data.forEach { donutChartData ->
//        val sweepAngle = donutChartData.percentage * 360 / 100
//        if (adjustedAngle in startAngle..(startAngle + sweepAngle)) {
//            return donutChartData.data
//        }
//        startAngle += sweepAngle
//    }
//    return emptyList()
//}
//
//fun getRandomColor(): Color {
//    val colors = listOf(Color.Blue, Color.Green, Color.Red, Color.Magenta, Color.Yellow)
//    return colors.random()
//}

//@Composable
//fun DonutChart(data: List<DonutChartData>, onClick: (DonutChartData) -> Unit) {
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .height(200.dp)
//        .padding(16.dp)
//    ) {
//        // Implement Donut Chart UI
//        data.forEach { chartData ->
//            Text(
//                text = "${chartData.label}: ${chartData.percentage}%",
//                modifier = Modifier.clickable { onClick(chartData) }
//            )
//        }
//    }
//}

//@Composable
//fun DonutChart(data: List<DonutChartData>, onClick: (DonutChartData) -> Unit) {
//    val colors = listOf(
//        Color(0xFF81D4FA), // Light Blue
//        Color(0xFFA5D6A7), // Light Green
//        Color(0xFFFFAB91), // Light Orange
//        Color(0xFFCE93D8)  // Light Purple
//    ) // Generate and remember colors once
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(500.dp)
//            .padding(50.dp)
//    ) {
//        Canvas(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//                .clickable { onClick(data.firstOrNull() ?: return@clickable) }
//        ) {
//            val radius = size.minDimension / 2
//            var startAngle = -90f
//            val strokeWidth = 200f // Adjust this value to make the donut hole smaller
//            val textPaint = android.graphics.Paint().apply {
//                color = android.graphics.Color.BLACK
//                textAlign = android.graphics.Paint.Align.CENTER
//                textSize = 30f // Adjust the text size as needed
//                isAntiAlias = true
//            }
//
//            data.forEachIndexed { index, chartData ->
//                val sweepAngle = (chartData.percentage / 100).toFloat() * 360f
//                drawArc(
//                    color = colors[index],
//                    startAngle = startAngle,
//                    sweepAngle = sweepAngle,
//                    useCenter = false,
//                    style = Stroke(strokeWidth)
//                )
//
//                // Calculate the position for the text
//                val angle = Math.toRadians((startAngle + sweepAngle / 2).toDouble())
//                val textX = (size.center.x + (radius - strokeWidth / 4) * Math.cos(angle)).toFloat()
//                val textY = (size.center.y + (radius - strokeWidth / 4) * Math.sin(angle)).toFloat()
//
//                // Calculate the offset to stack the texts
//                val percentageOffset = 30f // Adjust as needed
//                val labelOffset = percentageOffset + 30f // Adjust as needed to ensure text doesn't overlap
//
//                // Draw the percentage at the top center of the chunk
//                drawContext.canvas.nativeCanvas.drawText(
//                    "${chartData.percentage}%",
//                    textX,
//                    textY - percentageOffset,
//                    textPaint
//                )
//
//                // Draw the label below the percentage
//                drawContext.canvas.nativeCanvas.drawText(
//                    chartData.label,
//                    textX,
//                    textY - labelOffset,
//                    textPaint
//                )
//
//                startAngle += sweepAngle
//            }
//        }
//    }
//}

//Ini yang memungkinkan
//@Composable
//fun DonutChart(
//    data: List<DonutChartData>, onClick: (DonutChartData) -> Unit
//) {
//    val colors = listOf(
//        Color(0xFF81D4FA), // Light Blue
//        Color(0xFFA5D6A7), // Light Green
//        Color(0xFFFFAB91), // Light Orange
//        Color(0xFFCE93D8)  // Light Purple
//    )
//
//    val anglesList: MutableList<DrawingAngles> = remember { mutableListOf() }
//    val center = Offset(0f, 0f)
////    val dataTarget = (0..data.items.size).map {
////        remember { mutableStateOf(DonutChartState()) }
////    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(500.dp)
//            .padding(50.dp)
//    ) {
//        Canvas(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//                .pointerInput(Unit) {
//                    detectTapGestures{ offset ->
//                        handleCanvasTap(
//                            center = center,
//                            tapOffset = offset,
//                            anglesList = anglesList,
//                            onItemSelected = { selectedIndex ->
//                                onClick(data[selectedIndex])
//                            }
//                        )
//                    }
//                }
////                .clickable {
////                    val selectedIndex = getIndexFromCanvasTapHandling(
////                        center = center,
////                        tapOffset = it,
////                        anglesList = anglesList,
////                        size = size.value,
////                        data = data
////                    )
////                    if (selectedIndex != -1) {
////                        onClick(data[selectedIndex])
////                    }
////                }
//
//        ) {
//            val radius = size.minDimension / 2
//            var startAngle = -90f
//            val strokeWidth = 200f // Adjust this value to make the donut hole smaller
//            val textPaint = android.graphics.Paint().apply {
//                color = android.graphics.Color.BLACK // Ensure text color contrasts with chunk colors
//                textAlign = android.graphics.Paint.Align.CENTER
//                textSize = 30f // Adjust the text size as needed
//                isAntiAlias = true
//            }
//
//            data.forEachIndexed { index, chartData ->
//                val sweepAngle = (chartData.percentage / 100).toFloat() * 360f
//                drawArc(
//                    color = colors[index % colors.size],
//                    startAngle = startAngle,
//                    sweepAngle = sweepAngle,
//                    useCenter = false,
//                    style = Stroke(strokeWidth)
//                )
//
//                // Calculate the position for the text
//                val angle = Math.toRadians((startAngle + sweepAngle / 2).toDouble())
//                val textX = (size.center.x + (radius - strokeWidth / 2) * Math.cos(angle)).toFloat()
//                val textY = (size.center.y + (radius - strokeWidth / 2) * Math.sin(angle)).toFloat()
//
//                // Calculate the offset to stack the texts
//                val percentageOffset = 30f // Adjust as needed
//                val labelOffset = percentageOffset + 30f // Adjust as needed to ensure text doesn't overlap
//
//                // Draw the percentage at the top center of the chunk
//                drawContext.canvas.nativeCanvas.drawText(
//                    "${chartData.percentage}%",
//                    textX,
//                    textY - percentageOffset,
//                    textPaint
//                )
//
//                // Draw the label below the percentage
//                drawContext.canvas.nativeCanvas.drawText(
//                    chartData.label,
//                    textX,
//                    textY - labelOffset,
//                    textPaint
//                )
//
//                // Increment the start angle for the next chunk
//                startAngle += sweepAngle
//            }
//        }
//    }
//}

//@Composable
//fun DonutChart(data: List<DonutChartData>, onClick: (DonutChartData) -> Unit) {
//    val colors = listOf(
//        Color(0xFFe57373), // Light red
//        Color(0xFF81c784), // Light green
//        Color(0xFF64b5f6), // Light blue
//        Color(0xFFffb74d)  // Light orange
//    )
//
//    val center by remember { mutableStateOf(Offset.Zero) }
//    val anglesList = remember { mutableStateListOf<DrawingAngles>() }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(500.dp)
//            .padding(50.dp)
//    ) {
//        Canvas(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//                .pointerInput(Unit) {
//                    detectTapGestures(onTap = { tapOffset ->
//                        handleCanvasTap(center, tapOffset, anglesList) { index ->
//                            onClick(data[index])
//                        }
//                    })
//                }
//        ) {
//            val radius = size.minDimension / 2
//            var startAngle = -90f
//            val strokeWidth = 200f
//            val anglesList = mutableListOf<DrawingAngles>()
//
//            data.forEachIndexed { index, chartData ->
//                val sweepAngle = (chartData.percentage / 100).toFloat() * 360f
//                anglesList.add(DrawingAngles(startAngle, sweepAngle))
//                drawArc(
//                    color = colors[index % colors.size],
//                    startAngle = startAngle,
//                    sweepAngle = sweepAngle,
//                    useCenter = false,
//                    style = Stroke(strokeWidth, cap = StrokeCap.Butt)
//                )
//                startAngle += sweepAngle
//            }
//        }
//    }
//}

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





