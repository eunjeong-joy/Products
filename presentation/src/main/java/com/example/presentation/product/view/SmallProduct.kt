package com.example.presentation.product.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.presentation.R


@Preview
@Composable
fun PreviewSmallProduct() {
    SmallProduct()
}

@Composable
fun SmallProduct(
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.width(150.dp)) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            AsyncImage(
                model = "https://img-cf.kurly.com/shop/data/goods/1637154205701l0.jpg",
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Image(
                painter = painterResource(id = R.drawable.ic_btn_heart_off),
                contentDescription = "heart off",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(start = 0.dp, top = 5.dp, end = 5.dp, bottom = 0.dp)
            )
        }
        Text(
            text = "[샐러딩] 레디믹스 스탠다드 150g",
            fontSize = 15.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "30%",
                fontSize = 12.sp,
                color = Color(0xFFFA622F)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "6,200원",
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Text(
            text = "8,000원",
            fontSize = 10.sp,
            style = TextStyle(textDecoration = TextDecoration.LineThrough),
            color = Color(0xFF949494)
        )
    }
}