package com.example.presentation.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.product.usecase.FetchProductsUseCase
import com.example.presentation.product.model.Product
import com.example.presentation.product.model.Product.Companion.convertTo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val fetchProductsUseCase: FetchProductsUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun fetchProducts() {
        fetchProductsUseCase(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { result ->
                _products.value = result.convertTo()
            }
            .addTo(compositeDisposable = compositeDisposable)
    }
}