package com.example.domain.product.model

data class ProductEntity(
    val id: Int,
    val name: String,
    val image: String,
    val originalPrice: Int,
    val discountedPrice: Int?
) {
    companion object {
        fun dummy() = ProductEntity(
            id = 5063110,
            name = "[연세우유 x 마켓컬리] 전용목장우유 900mL",
            image = "https://img-cf.kurly.com/shop/data/goods/1637154205701l0.jpg",
            originalPrice = 2070,
            discountedPrice = 1800
        )
    }
}
