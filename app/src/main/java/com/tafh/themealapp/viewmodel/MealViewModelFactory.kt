package com.tafh.themealapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tafh.themealapp.data.repository.MealRepository
import java.lang.IllegalArgumentException

class MealViewModelFactory(
    private val repository: MealRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealViewModel(repository) as T
        }
        throw IllegalArgumentException("unable to construct viewmodel")
    }
}