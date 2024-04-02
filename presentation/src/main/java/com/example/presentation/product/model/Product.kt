package com.example.presentation.product.model

import com.example.domain.product.model.ProductEntity
import java.util.Locale

data class Product(
    val id: Int,
    val name: String,
    val image: String,
    val originalPrice: Int,
    val discountedPrice: Int?,
    val discountedRate: Float?,
    val isBookmarked: Boolean = false
) {
    companion object {
        fun ProductEntity.convertTo(): Product {
            val discountRate = this.discountedPrice?.let {
                ((this.originalPrice.toFloat() - it.toFloat()) / this.originalPrice.toFloat()) * 100
            }

            return Product(
                id = this.id,
                name = this.name,
                image = this.image,
                originalPrice = this.originalPrice,
                discountedPrice = this.discountedPrice,
                discountedRate = discountRate
            )
        }

        fun dummy() = ProductEntity.dummy().convertTo()

        fun dummyList() = listOf(dummy(), dummy(), dummy())

        fun List<ProductEntity>.convertTo() = this.map { it.convertTo() }
    }
}
