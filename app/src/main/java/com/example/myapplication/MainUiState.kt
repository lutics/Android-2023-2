package com.example.myapplication

sealed class MainUiState {
    object Loading : MainUiState()
    data class Success(val user: VO.Response.Result) : MainUiState()
    object Error : MainUiState()
}