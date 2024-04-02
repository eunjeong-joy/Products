package com.example.domain.product.usecase

import com.example.domain.product.repo.ProductRepository
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<Int> = productRepository.getProductBookmarks()
}