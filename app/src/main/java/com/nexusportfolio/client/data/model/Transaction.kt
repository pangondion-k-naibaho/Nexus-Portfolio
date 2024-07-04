package com.nexusportfolio.client.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaction(
    val trx_date: String,
    val nominal: Int
): Parcelable