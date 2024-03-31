package com.example.presentation.sections.data

import com.example.domain.section.model.SectionEntity

data class Section(
    val title: String,
    val id: Int,
    val type: String,
    val url: String
) {
    companion object {
        private fun SectionEntity.convertTo() = Section(
            title = this.title,
            id = this.id,
            type = this.type,
            url = this.url
        )

        fun List<SectionEntity>.convertTo() = this.map { it.convertTo() }

        fun dummy() = SectionEntity.dummy().convertTo()
    }
}
