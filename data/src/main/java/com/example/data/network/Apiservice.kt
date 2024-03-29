package com.example.data.network

import com.example.data.network.model.BaseResponse
import com.example.data.section.repo.model.SectionDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiservice {
    @GET("sections")
    fun getSections(
        @Query("page") page: Int
    ): Single<BaseResponse<List<SectionDto>>>
}