package com.tafh.themealapp.data.repository

import com.tafh.themealapp.api.RetrofitBuilder.apiService


class MealRepository {

    suspend fun getMealSeafood() = apiService.getMealSeafood()

    suspend fun getById(id: Int) = apiService.getById(id)

}