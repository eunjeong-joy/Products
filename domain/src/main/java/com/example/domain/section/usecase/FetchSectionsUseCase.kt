package com.example.domain.section.usecase

import com.example.domain.section.model.SectionEntity
import io.reactivex.Single
import javax.inject.Inject

class FetchSectionsUseCase @Inject constructor() {
    operator fun invoke(): Single<List<SectionEntity>> =
        Single.just(SectionEntity.dummyList())
}