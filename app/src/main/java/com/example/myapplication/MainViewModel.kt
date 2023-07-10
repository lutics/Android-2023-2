@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.myapplication

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val testRepository: TestRepository
) : ViewModel() {

    private val _result = MutableStateFlow(listOf<VO.Response.Result.OrganicResults>())
    val result: StateFlow<List<VO.Response.Result.OrganicResults>> = _result

    fun onSearch(
        query: String
    ): Any = launch {
        when (
            val results = testRepository.getResults(query)
        ) {
            is Resource.Success -> {
                _result.value = results.data.images_results
            }

            else -> {
                _result.value = listOf()
            }
        }
    }
}