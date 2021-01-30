package com.tafh.themealapp.data.repository

import com.tafh.themealapp.api.RetrofitBuilder.apiService


class MealRepository {

    suspend fun getCategories() = apiService.getCategories()

    suspend fun getByCategory(category: String) = apiService.getByCategory(category)

    suspend fun getById(id: Int) = apiService.getById(id)

}