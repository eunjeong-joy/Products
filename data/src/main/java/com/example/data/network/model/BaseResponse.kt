package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<DATA: Any>(
    @SerializedName("data")
    val data: DATA?,
    @SerializedName("paging")
    val paging: Paging?
)
