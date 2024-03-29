package com.example.presentation.products.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.products.viewmodel.ProductsViewModel


@Preview
@Composable
private fun PreviewProductScreenView() {
    ProductsScreenView()
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductsScreenView(
    viewModel: ProductsViewModel = hiltViewModel()
) {

    val refreshState by viewModel.refreshState.observeAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshState ?: false,
        onRefresh = {
            viewModel.setRefreshState(true)
        }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {

    }
}