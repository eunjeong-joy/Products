package com.example.presentation.sections.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SectionsViewModel @Inject constructor(): ViewModel(){
    private val _refreshState = MutableLiveData<Boolean>(false)
    val refreshState: LiveData<Boolean> = _refreshState

    fun setRefreshState(state: Boolean) {
        _refreshState.value = state
    }
}