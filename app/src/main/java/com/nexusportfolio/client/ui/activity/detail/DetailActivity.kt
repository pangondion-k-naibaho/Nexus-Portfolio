package com.nexusportfolio.client.ui.activity.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nexusportfolio.client.data.model.DonutChartData
import com.nexusportfolio.client.ui.components.DetailScreen
import com.nexusportfolio.client.ui.theme.NexusPortfolioTheme


class DetailActivity : ComponentActivity() {
    private val TAG = DetailActivity::class.java.simpleName
    private lateinit var deliveredDData : DonutChartData

    companion object{
        const val EXTRA_TRANSACTION = "EXTRA_TRANSACTION"
        const val EXTRA_DDATA = "EXTRA_DDATA"

        fun newIntent(context: Context, deliveredData: DonutChartData):Intent = Intent(context, DetailActivity::class.java)
            .putExtra(EXTRA_DDATA, deliveredData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deliveredDData = intent.getParcelableExtra<DonutChartData>(EXTRA_DDATA)!!

        Log.d(TAG, "delivered Donut Data : $deliveredDData")

        setContent {
            NexusPortfolioTheme {
                DetailScreen(context = this@DetailActivity, donutChartData = deliveredDData)
            }
        }
    }
}