package com.nexusportfolio.client.ui.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nexusportfolio.client.R
import com.nexusportfolio.client.data.model.DonutChartData
import com.nexusportfolio.client.ui.theme.blue2
import com.nexusportfolio.client.ui.theme.blue3

@Composable
fun DetailScreen(context: Context, donutChartData: DonutChartData){
    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        val (label, percentage,
            listofTransactionTitle, listofTransactionValue) = createRefs()

        Text(
            text = String.format(context.getString(R.string.tvTransactionLabel), donutChartData.label),
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(label) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(percentage.start)
                }
                .padding(start = 16.dp),
            color = Color.Black
        )

        Text(
            text = String.format(context.getString(R.string.tvPercentage), donutChartData.percentage.toInt()),
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(percentage) {
                    start.linkTo(label.end)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(end = 16.dp),
            color = Color.Black
        )

        Text(
            text = context.getString(R.string.tvListTransaction),
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(listofTransactionTitle) {
                    start.linkTo(parent.start)
                    top.linkTo(label.bottom)
                }
                .padding(top = 10.dp),
            color = blue3
        )

        TransactionListScreen(
            labelName = donutChartData.label,
            listTransaction = donutChartData.data,
            modifier = Modifier
                .constrainAs(listofTransactionValue){
                    start.linkTo(parent.start)
                    top.linkTo(listofTransactionTitle.bottom)
                }
        )
    }
}