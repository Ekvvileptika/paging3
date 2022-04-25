package com.example.myapplication.paging3.repository

import com.example.myapplication.paging3.PagedResponse
import javax.inject.Inject

class MortyApi @Inject constructor(
    private val api: IMortyAPi
) {
    suspend fun getPage(pagenum: String): PagedResponse {
        return api.getMortyData("0")
    }
}