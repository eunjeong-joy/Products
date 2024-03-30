package com.example.domain.section.model

data class SectionsResponse(
    val sections: List<SectionEntity>,
    val nextPage: Int?
)