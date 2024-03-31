package com.example.presentation.product.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.product.model.Product
import com.example.presentation.sections.data.Section

@Preview
@Composable
fun PreviewHorizontalSection() {
    HorizontalSection(
        section = Section.dummy(),
        products = Product.dummyList()
    )
}

@Composable
fun HorizontalSection(
    section: Section,
    products: List<Product>
) {
    Column {
        SectionTitle(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 15.dp),
            title = section.title
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { product ->
                SmallProduct(product = product)
            }
        }
    }
}