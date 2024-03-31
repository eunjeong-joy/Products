package com.example.presentation.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.presentation.sections.view.SectionsScreenView
import com.example.presentation.sections.viewmodel.SectionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sectionsViewModel: SectionsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SectionsScreenView(sectionsViewModel = sectionsViewModel)
        }
    }
}