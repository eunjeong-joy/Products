package com.example.data.section.model

import com.example.domain.section.model.SectionEntity
import com.google.gson.annotations.SerializedName

data class SectionDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
) {
    companion object {
        private fun SectionDto.convertTo() = SectionEntity(
            title = this.title,
            id = this.id,
            type = this.type,
            url = this.url
        )

        fun List<SectionDto>.convertTo() = this.map { it.convertTo() }
    }
}

