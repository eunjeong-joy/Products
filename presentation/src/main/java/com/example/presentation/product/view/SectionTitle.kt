package com.example.presentation.product.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PreviewSectionTitle() {
    SectionTitle(title = "Title")
}

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        modifier = modifier
            .background(Color(0xFF5F0080))
            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth(),
        text = title,
        fontSize = 18.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}