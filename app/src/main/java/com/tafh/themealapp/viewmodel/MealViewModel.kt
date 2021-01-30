package com.tafh.themealapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tafh.themealapp.data.repository.MealRepository
import com.tafh.themealapp.data.response.DetailMealResponse
import com.tafh.themealapp.data.response.MealResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MealViewModel(private val repository: MealRepository) : ViewModel() {

    val mealList: MutableLiveData<Response<MealResponse>> = MutableLiveData()
    val mealDetail: MutableLiveData<Response<DetailMealResponse>> = MutableLiveData()

    init {
        getByCategory()
    }

    fun getByCategory() = viewModelScope.launch {
        val response = repository.getMealSeafood()
        mealList.value = response
    }

    fun getById() = viewModelScope.launch {
        val response = repository.getById(52874)
        mealDetail.value = response

    }

}