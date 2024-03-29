package com.example.domain.section.model

data class SectionEntity(
    val title: String,
    val id: Int,
    val type: String,
    val url: String
) {
    companion object {
        private fun dummy() = SectionEntity(
            title = "TEST",
            id = 1,
            type = "A",
            url = "url"
        )

        fun dummyList() = listOf(dummy(), dummy(), dummy())
    }
}
