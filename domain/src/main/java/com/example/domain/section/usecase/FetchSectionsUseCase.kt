package com.example.domain.section.usecase

import com.example.domain.section.model.SectionsResponse
import com.example.domain.section.repo.SectionRepository
import io.reactivex.Single
import javax.inject.Inject

class FetchSectionsUseCase @Inject constructor(
    private val sectionRepository: SectionRepository
) {
    operator fun invoke(page: Int): Single<SectionsResponse> =
        sectionRepository.fetchSections(page)
}