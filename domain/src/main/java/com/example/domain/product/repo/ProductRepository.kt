package com.example.domain.product.repo

import com.example.domain.product.model.ProductEntity
import io.reactivex.Single

interface ProductRepository {
    fun fetchProducts(sectionId: Int): Single<List<ProductEntity>>

    fun updateProductBookmark(productId: Int)

    fun deleteProductBookmark(productId: Int)
}