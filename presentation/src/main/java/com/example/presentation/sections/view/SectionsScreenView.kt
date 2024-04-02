package com.example.presentation.sections.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.product.view.BigProduct
import com.example.presentation.product.view.GridSection
import com.example.presentation.product.view.HorizontalSection
import com.example.presentation.product.view.SectionTitle
import com.example.presentation.sections.data.SectionType
import com.example.presentation.sections.viewmodel.SectionsViewModel


@Preview
@Composable
private fun PreviewProductScreenView() {
    SectionsScreenView()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SectionsScreenView(
    viewModel: SectionsViewModel = hiltViewModel()
) {

    val refreshState by viewModel.refreshState.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshState,
        refreshingOffset = 30.dp,
        onRefresh = {
            viewModel.showPullRefreshIndicatorShowing()
            viewModel.sectionsClear()
            viewModel.fetchSections()
        }
    )

    val sections by viewModel.sections.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        if (sections.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                sections.forEach { section ->
                    when (section.type) {
                        SectionType.HORIZONTAL -> {
                            item {
                                HorizontalSection(
                                    section = section
                                )
                            }
                        }

                        SectionType.VERTICAL -> {
                            if (section.products.isEmpty()) {
                                viewModel.fetchProducts(section.id)
                                item {
                                    Text(text = "Loading") //임시 loading UI
                                }
                            } else {
                                item {
                                    SectionTitle(
                                        modifier = Modifier.padding(
                                            start = 15.dp,
                                            end = 15.dp,
                                            top = 15.dp
                                        ),
                                        title = section.title
                                    )
                                }

                                items(section.products) { product ->
                                    BigProduct(
                                        modifier = Modifier.padding(
                                            start = 15.dp,
                                            end = 15.dp,
                                            top = 15.dp
                                        ),
                                        product = product
                                    )
                                }

                            }
                        }

                        SectionType.GRID -> {
                            item {
                                GridSection(
                                    section = section
                                )
                            }
                        }

                        else -> {} // nothing
                    }
                }
            }
        } else {
            Text(text = "section loading") //임시 loading UI
        }

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = refreshState ?: false,
            state = pullRefreshState
        )
    }
}