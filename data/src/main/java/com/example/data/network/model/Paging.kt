package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class Paging(
    @SerializedName("next_page")
    val nextPage: Int
)
