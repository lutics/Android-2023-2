package com.example.myapplication

import androidx.annotation.StringRes

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(@StringRes val res: Int) : Resource<Nothing>()
    data class Error(val exception: Exception) : Resource<Nothing>()
}