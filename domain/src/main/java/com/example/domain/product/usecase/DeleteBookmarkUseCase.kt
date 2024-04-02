package com.example.domain.product.usecase

import com.example.domain.product.repo.ProductRepository
import javax.inject.Inject

class DeleteBookmarkUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    operator fun invoke(productId: Int) {
        productRepository.deleteProductBookmark(productId)
    }
}