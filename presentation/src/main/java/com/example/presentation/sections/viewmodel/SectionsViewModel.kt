package com.example.presentation.sections.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.section.usecase.FetchSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SectionsViewModel @Inject constructor(
    private val fetchSectionsUseCase: FetchSectionsUseCase
): ViewModel(){
    private val compositeDisposable = CompositeDisposable()

    private val _refreshState = MutableLiveData<Boolean>(false)
    val refreshState: LiveData<Boolean> = _refreshState

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
        fetchSectionsUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                hidePullRefreshIndicatorShowing()
            }
            .subscribe { result ->
                //TODO : 섹션 정보 받아온 후 상품 정보 노춣 진행
            }
            .addTo(compositeDisposable)
    }
}