package com.example.data.product.model

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
)
