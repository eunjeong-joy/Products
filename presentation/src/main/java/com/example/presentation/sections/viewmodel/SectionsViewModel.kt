package com.example.presentation.sections.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.section.usecase.FetchSectionsUseCase
import com.example.presentation.sections.data.Section
import com.example.presentation.sections.data.Section.Companion.convertTo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SectionsViewModel @Inject constructor(
    private val fetchSectionsUseCase: FetchSectionsUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _refreshState = MutableStateFlow<Boolean>(false)
    val refreshState: StateFlow<Boolean> = _refreshState

    private val _sections = MutableStateFlow<List<Section>>(listOf())
    val sections: StateFlow<List<Section>> = _sections.asStateFlow()

    private var nextPage: Int? = null

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun setRefreshState(state: Boolean) {
        _refreshState.value = state
    }

    fun showPullRefreshIndicatorShowing() {
        setRefreshState(true)
    }

    private fun hidePullRefreshIndicatorShowing() {
        setRefreshState(false)
    }

    fun fetchSections() {
        fetchSectionsUseCase(nextPage ?: 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                hidePullRefreshIndicatorShowing()
            }
            .subscribe { result ->
                updateNextPage(result.nextPage)
                updateSections(result.sections.convertTo())
            }
            .addTo(compositeDisposable)
    }

    private fun updateNextPage(page: Int?) {
        this.nextPage = page ?: 1
    }
    private fun updateSections(sections: List<Section>) {
        _sections.value = sections
    }
}