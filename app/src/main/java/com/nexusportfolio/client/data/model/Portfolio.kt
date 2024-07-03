package com.nexusportfolio.client.data.model

import android.os.Parcelable
import com.nexusportfolio.client.data.model.DonutChartData
import com.nexusportfolio.client.data.model.LineChartData
import kotlinx.android.parcel.Parcelize

//@Parcelize
//data class Portfolio(
//    val type: String,
//    val donutChartData: List<DonutChartData>? = null,
//    val lineChartData: LineChartData? = null
//):Parcelable

@Parcelize
data class Portfolio(
    val type: String,
    val donutChartData: List<DonutChartData>? = null,
    val lineChartData: LineChartData? = null
):Parcelable