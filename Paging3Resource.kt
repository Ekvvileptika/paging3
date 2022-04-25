package com.example.myapplication.paging3

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.paging3.repository.MortyApi

class Paging3Resource(
    private val apiservice: MortyApi
): PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val response = apiservice.getPage(pageNumber.toString())
            //val pagedResponse = response.results
            val data = response.results

            var nextPageNumber: Int? = null
            if (response?.pageInfo?.next != null) {
                val uri = Uri.parse(response.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}