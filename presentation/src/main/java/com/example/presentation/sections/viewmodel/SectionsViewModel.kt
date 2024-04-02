package com.example.presentation.sections.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.product.model.ProductEntity
import com.example.domain.product.usecase.DeleteBookmarkUseCase
import com.example.domain.product.usecase.FetchProductsUseCase
import com.example.domain.product.usecase.GetBookmarksUseCase
import com.example.domain.product.usecase.UpdateBookmarkUseCase
import com.example.domain.section.usecase.FetchSectionsUseCase
import com.example.presentation.product.model.Product
import com.example.presentation.product.model.Product.Companion.convertTo
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
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SectionsViewModel @Inject constructor(
    private val fetchSectionsUseCase: FetchSectionsUseCase,
    private val fetchProductsUseCase: FetchProductsUseCase,
    private val updateBookmarkUseCase: UpdateBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
    private val getBookmarksUseCase: GetBookmarksUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _refreshState = MutableStateFlow<Boolean>(false)
    val refreshState: StateFlow<Boolean> = _refreshState

    private val _sections = MutableStateFlow<List<Section>>(listOf())
    val sections: StateFlow<List<Section>> = _sections.asStateFlow()

    private var nextPage: Int? = null
    private var bookmarks: List<Int> = mutableListOf()

    init {
        fetchSections()
    }

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

    fun sectionsClear() {
        _sections.value = emptyList()
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
                getBookmarks()
            }
            .addTo(compositeDisposable)
    }

    private fun updateNextPage(page: Int?) {
        this.nextPage = page ?: 1
    }

    fun fetchProducts(sectionId: Int) {
        fetchProductsUseCase(sectionId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { result ->
                updateSectionProducts(sectionId, result).let { sections ->
                    updateSections(sections)
                }
            }
            .addTo(compositeDisposable = compositeDisposable)
    }

    private fun updateSectionProducts(
        sectionId: Int,
        products: List<ProductEntity>
    ): MutableList<Section> {
        val tempSections = _sections.value.toMutableList()
        val tempSection = tempSections.find { it.id == sectionId }
        val tempSectionIndex = tempSections.indexOf(tempSection)
        tempSection?.let { section ->
            tempSections[tempSectionIndex] = Section(
                id = section.id,
                title = section.title,
                type = section.type,
                url = section.url,
                products = getCheckedProducts(products.convertTo())
            )
        }
        return tempSections
    }

    private fun updateSections(sections: List<Section>) {
        _sections.value = sections
    }

    fun updateBookmark(productId: Int) {
        updateBookmarkUseCase(productId)
    }

    fun deleteBookmark(productId: Int) {
        deleteBookmarkUseCase(productId)
    }

    private fun getBookmarks() {
        runBlocking {
            bookmarks = getBookmarksUseCase()
        }
    }

    private fun getCheckedProducts(products: List<Product>): List<Product> =
        products.map { product ->
            if (bookmarks.contains(product.id)) {
                product.isBookmarked = true
            }
            product
        }
}