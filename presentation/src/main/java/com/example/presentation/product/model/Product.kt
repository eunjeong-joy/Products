package com.example.presentation.product.model

data class Product(
    val id: Int,
    val name: String,
    val image: String,
    val originalPrice: String,
    val discountedPrice: String?,
    val discountedRate: String?
) {
    companion object {
        fun dummy() = Product(
            id = 5063110,
            name = "[연세우유 x 마켓컬리] 전용목장우유 900mL",
            image = "https://img-cf.kurly.com/shop/data/goods/1637154205701l0.jpg",
            originalPrice = "2,070원",
            discountedPrice = "1,800원",
            discountedRate = "13%"
        )
    }
}
