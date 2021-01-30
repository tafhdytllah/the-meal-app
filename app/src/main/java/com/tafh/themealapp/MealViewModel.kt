package com.tafh.themealapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tafh.themealapp.data.repository.MealRepository
import com.tafh.themealapp.data.response.CategoryResponse
import com.tafh.themealapp.data.response.DetailMealResponse
import com.tafh.themealapp.data.response.MealResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MealViewModel(private val repository: MealRepository) : ViewModel() {

    val categoryList: MutableLiveData<Response<CategoryResponse>> = MutableLiveData()
    val mealList: MutableLiveData<Response<MealResponse>> = MutableLiveData()
    val mealDetail: MutableLiveData<Response<DetailMealResponse>> = MutableLiveData()

    init {
        getCategories()
        getByCategory()
        getById()
    }

    fun getCategories() = viewModelScope.launch {
        val response = repository.getCategories()
        categoryList.value = response
    }

    fun getByCategory() = viewModelScope.launch {
        val response = repository.getByCategory("Beef")
        mealList.value = response
    }

    fun getById() = viewModelScope.launch {
        val response = repository.getById(52874)
        mealDetail.value = response

    }

}