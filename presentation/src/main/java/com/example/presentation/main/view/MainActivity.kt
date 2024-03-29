package com.example.presentation.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.presentation.products.view.ProductsScreenView
import com.example.presentation.products.viewmodel.ProductsViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ProductsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsScreenView(viewModel = viewModel)
        }
    }
}