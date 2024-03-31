package com.example.data.product.repo

import com.example.data.network.Apiservice
import com.example.data.product.model.ProductDto.Companion.convertTo
import com.example.domain.product.model.ProductEntity
import com.example.domain.product.repo.ProductRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiservice: Apiservice
) : ProductRepository {
    override fun fetchProducts(sectionId: Int): Single<List<ProductEntity>> =
        apiservice.getProducts(sectionId).map { response ->
            response.data?.let { products ->
                products.convertTo()
            }
        }.subscribeOn(Schedulers.io())
}