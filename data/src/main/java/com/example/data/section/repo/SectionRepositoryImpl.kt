package com.example.data.section.repo

import com.example.data.network.Apiservice
import com.example.data.network.model.BaseResponse
import com.example.data.section.repo.model.SectionDto.Companion.convertTo
import com.example.domain.section.model.SectionEntity
import com.example.domain.section.repo.SectionRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SectionRepositoryImpl @Inject constructor(
    private val apiservice: Apiservice
) : SectionRepository {
    override fun fetchSections(page: Int): Single<List<SectionEntity>> =
        apiservice.getSections(page).map { response ->
            response.data?.let { sections ->
                sections.convertTo()
            }
        }.subscribeOn(Schedulers.io())
}