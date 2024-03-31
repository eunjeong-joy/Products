package com.example.data.product.model

import com.example.domain.product.model.ProductEntity
import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("originalPrice")
    val originalPrice: Int,
    @SerializedName("discountedPrice")
    val discountedPrice: Int?
) {
    companion object {
        private fun ProductDto.convertTo() = ProductEntity(
            id = this.id,
            name = this.name,
            image = this.image,
            originalPrice = this.originalPrice,
            discountedPrice = this.discountedPrice
        )

        fun List<ProductDto>.convertTo() = this.map { it.convertTo() }
    }
}
