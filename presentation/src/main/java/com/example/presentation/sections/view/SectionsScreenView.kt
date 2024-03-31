package com.example.presentation.sections.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.product.model.Product
import com.example.presentation.product.view.HorizontalSection
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
    sectionsViewModel: SectionsViewModel = hiltViewModel()
) {

    val refreshState by sectionsViewModel.refreshState.observeAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshState ?: false,
        refreshingOffset = 30.dp,
        onRefresh = {
            sectionsViewModel.showPullRefreshIndicatorShowing()
            sectionsViewModel.fetchSections()
        }
    )

    val sections = sectionsViewModel.sections.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            sections.value?.let { secations ->
                secations.forEach { section ->
                    when (section.type) {
                        SectionType.HORIZONTAL -> {
                            item {
                                HorizontalSection(
                                    section = section,
                                    products = Product.dummyList()
                                )
                            }
                        }

                        SectionType.VERTICAL -> {
                            //TODO : VERTICAL section 작업 예정
                        }

                        SectionType.GRID -> {
                            //TODO : GRID section 작업 예정
                        }

                        else -> {} // nothing
                    }
                }
            }
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = refreshState ?: false,
            state = pullRefreshState
        )
    }
}