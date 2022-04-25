package com.example.myapplication.paging3.repository

import com.example.myapplication.paging3.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMortyAPi {
    @GET("api/character/?page={pagenum}")
    suspend fun getMortyData(
        @Path("pagenum") pagenum: String
    ): PagedResponse
}