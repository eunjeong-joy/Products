package com.example.presentation.sections.data

enum class SectionType(val value: String) {
    HORIZONTAL("horizontal"),
    VERTICAL("vertical"),
    GRID("grid"),
    NONE("none");

    companion object {
        fun from(value: String): SectionType? =
            SectionType.values().associateBy(SectionType::value)[value]
    }
}