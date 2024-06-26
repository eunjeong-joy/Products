package com.example.domain.section.repo

import com.example.domain.section.model.SectionsResponse
import io.reactivex.Single

interface SectionRepository {
    fun fetchSections(page: Int): Single<SectionsResponse>
}