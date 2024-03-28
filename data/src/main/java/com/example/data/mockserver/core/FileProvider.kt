package com.example.data.mockserver.core

internal interface FileProvider {
    fun getJsonFromAsset(filePath: String): String?
}
