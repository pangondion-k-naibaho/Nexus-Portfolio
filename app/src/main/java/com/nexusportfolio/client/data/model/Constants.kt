package com.nexusportfolio.client.data.model

import androidx.compose.ui.unit.dp

object Constants {
    interface DUMMY_DATA{
        companion object {
            val data = listOf(
                Portfolio(
                    type = "donutChart",
                    donutChartData = listOf(
                        DonutChartData(
                            label = "Tarik Tunai",
                            percentage = 55.0,
                            data = listOf(
                                Transaction("21/01/2023", 1000000),
                                Transaction("20/01/2023", 500000),
                                Transaction("19/01/2023", 1000000)
                            )
                        ),
                        DonutChartData(
                            label = "QRIS Payment",
                            percentage = 31.0,
                            data = listOf(
                                Transaction("21/01/2023", 159000),
                                Transaction("20/01/2023", 35000),
                                Transaction("19/01/2023", 1500)
                            )
                        ),
                        DonutChartData(
                            label = "Topup Gopay",
                            percentage = 7.7,
                            data = listOf(
                                Transaction("21/01/2023", 200000),
                                Transaction("20/01/2023", 195000),
                                Transaction("19/01/2023", 5000000)
                            )
                        ),
                        DonutChartData(
                            label = "Lainnya",
                            percentage = 6.3,
                            data = listOf(
                                Transaction("21/01/2023", 1000000),
                                Transaction("20/01/2023", 500000),
                                Transaction("19/01/2023", 1000000)
                            )
                        )
                    )
                ),
                Portfolio(
                    type = "lineChart",
                    lineChartData = LineChartData(
                        month = listOf(3, 7, 8, 10, 5, 10, 1, 3, 5, 10, 7, 7)
                    )
                )
            )
        }
    }

    interface MEASURE{
        companion object{
            const val animationDuration = 800
            const val chartDegrees = 360f
            const val emptyIndex = -1
            val defaultSliceWidth = 20.dp
            val defaultSlicePadding = 5.dp
            val defaultSliceClickPadding = 10.dp
        }
    }

    interface TRANSACTION_TYPE{
        companion object{
            const val CASH_WITHDRAWAL = "Tarik Tunai"
            const val QRIS_PAYMENT = "Qris Payment"
            const val TOPUP_GOPAY = "Topup Gopay"
        }
    }
}
