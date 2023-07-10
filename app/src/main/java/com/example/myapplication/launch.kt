package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

fun ViewModel.launch(
    function: suspend () -> Unit,
) {
    viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            
        }
    ) {
        function.invoke()
    }
}