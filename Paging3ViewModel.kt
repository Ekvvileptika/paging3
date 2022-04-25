package com.example.myapplication.paging3

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.paging3.repository.MortyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class Paging3ViewModel @ViewModelInject constructor(
    val repo: MortyApi
): ViewModel() {
    val characters: Flow<PagingData<Character>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                Paging3Resource(repo)
            }
        ).flow.cachedIn(viewModelScope)
}