package com.example.domain.section.model

data class SectionEntity(
    val title: String,
    val id: Int,
    val type: String,
    val url: String
) {
    companion object {
        fun dummy() = SectionEntity(
            title = "함께하면 더 좋은 상품",
            id = 1,
            type = "grid",
            url = "section_products_1"
        )
    }
}
