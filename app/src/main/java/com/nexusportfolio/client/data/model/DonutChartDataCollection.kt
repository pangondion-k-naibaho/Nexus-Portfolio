package com.nexusportfolio.client.data.model

data class DonutChartDataCollection(
    var items: List<DonutChartData>
){
    internal var totalAmount: Float = items.sumOf { it.percentage }.toFloat()
        private set
}