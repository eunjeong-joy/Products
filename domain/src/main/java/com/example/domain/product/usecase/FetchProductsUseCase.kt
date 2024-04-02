package com.example.domain.product.usecase

import com.example.domain.product.model.ProductEntity
import com.example.domain.product.repo.ProductRepository
import io.reactivex.Single
import javax.inject.Inject

class FetchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(sectionId: Int): Single<List<ProductEntity>> =
        productRepository.fetchProducts(sectionId)
}