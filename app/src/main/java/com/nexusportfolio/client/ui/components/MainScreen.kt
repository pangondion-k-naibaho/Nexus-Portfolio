package com.nexusportfolio.client.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nexusportfolio.client.ui.activity.detail.DetailActivity
import com.nexusportfolio.client.ui.viewmodels.MainViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun MainScreen(viewModel: MainViewModel, context: Context) {
    val portfolioData by viewModel.portfolioData.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF067BFA))
            }
        } else {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                portfolioData.forEach { data ->
                    when (data.type) {
                        "donutChart" -> {
                            data.donutChartData?.let { donutData ->
                                DonutChart(data = donutData) { clickedData ->
                                    Log.d(context::class.java.toString(), "clickedData: $clickedData")
                                    val intent = DetailActivity.newIntent(context, clickedData)
                                    context.startActivity(intent)
                                }
                            }
                        }
                        "lineChart" -> {
                            data.lineChartData?.let { lineData ->
                                LineChart(data = lineData)
                            }
                        }
                    }
                }
            }
        }
    }
}

