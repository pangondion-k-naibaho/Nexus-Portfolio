package com.nexusportfolio.client.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DonutChartData(
    val label: String,
    val percentage: Double,
    val data: List<Transaction>
):Parcelable