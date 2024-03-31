package com.example.presentation.sections.data

import com.example.domain.section.model.SectionEntity

data class Section(
    val title: String,
    val id: Int,
    val type: SectionType,
    val url: String
) {
    companion object {
        private fun SectionEntity.convertTo() = Section(
            title = this.title,
            id = this.id,
            type = SectionType.from(this.type)?.let { it }?: SectionType.NONE,
            url = this.url
        )

        fun List<SectionEntity>.convertTo() = this.map { it.convertTo() }

        fun dummy() = SectionEntity.dummy().convertTo()
    }
}
