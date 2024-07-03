package com.nexusportfolio.client.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nexusportfolio.client.R
import com.nexusportfolio.client.data.model.Constants.TRANSACTION_TYPE.Companion.CASH_WITHDRAWAL
import com.nexusportfolio.client.data.model.Constants.TRANSACTION_TYPE.Companion.QRIS_PAYMENT
import com.nexusportfolio.client.data.model.Constants.TRANSACTION_TYPE.Companion.TOPUP_GOPAY
import com.nexusportfolio.client.data.model.Constants.TRANSACTION_TYPE.Companion.TYPE_OTHER
import com.nexusportfolio.client.data.model.Extension.Companion.rupiahFormatting
import com.nexusportfolio.client.data.model.Transaction
import com.nexusportfolio.client.ui.theme.blue2
import com.nexusportfolio.client.ui.theme.grey
import com.nexusportfolio.client.ui.theme.white

@Composable
fun TransactionListScreen(labelName: String, listTransaction: List<Transaction>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(listTransaction){transaction ->
            TransactionItem(labelName = labelName, transaction = transaction)
        }
    }
}

@Composable
fun TransactionItem(labelName: String, transaction: Transaction){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = white,
                shape = RoundedCornerShape(5.dp)
            )
            .border(
                width = 2.dp,
                color = grey,
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        val (image, date, nominal) = createRefs()

        val imageModifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(5.dp))
            .padding(top = 5.dp, bottom = 5.dp, start = 5.dp)

        Image(
            painter = when(labelName){
                CASH_WITHDRAWAL -> painterResource(id = R.drawable.cash_withdrawal_logo)
                QRIS_PAYMENT -> painterResource(id = R.drawable.qris_logo)
                TOPUP_GOPAY -> painterResource(id = R.drawable.gopay)
                else -> painterResource(id = R.drawable.other_cash_using)
            },
            contentDescription = null,
            modifier = imageModifier.constrainAs(image){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
            contentScale = ContentScale.Fit
        )

        Text(
            text = transaction.trx_date,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(date) {
                    top.linkTo(image.top)
                    start.linkTo(image.end)
                }
                .padding(start = 10.dp, top = 5.dp),
            color = blue2
        )

        Text(
            text = transaction.nominal.rupiahFormatting(),
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(nominal){
                    start.linkTo(image.end)
                    bottom.linkTo(image.bottom)
                }
                .padding(start = 10.dp, bottom = 5.dp)
        )

    }
}