package com.example.presentation.product.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.sections.data.Section
import com.example.presentation.sections.viewmodel.SectionsViewModel


@Preview
@Composable
fun PreviewGridSection() {
    GridSection(Section.dummy())
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GridSection(
    section: Section,
    viewModel: SectionsViewModel = hiltViewModel()
) {
    if (section.products.isEmpty()) {
        viewModel.fetchProducts(section.id)
        ProductsLoadingView()
    } else {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp
        val density = LocalContext.current.resources.displayMetrics.density
        val spaceSize = (15 * density).toInt()
        val productWidth = ((screenWidth - spaceSize) / 3).dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp)
        ) {
            SectionTitle(
                title = section.title
            )
            Spacer(modifier = Modifier.height(15.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                section.products.forEach {
                    SmallProduct(
                        product = it,
                        widthSize = productWidth
                    )
                }
            }
        }
    }
}
