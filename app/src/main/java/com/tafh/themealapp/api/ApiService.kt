package com.tafh.themealapp.api

import com.tafh.themealapp.data.response.DetailMealResponse
import com.tafh.themealapp.data.response.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     *  Base url = https://www.themealdb.com/api/json/v1/1/
     *  endpoint:
     *   - getMealSeafood = filter.php?c=Seafood
     *   - getMealById = lookup.php?i=52874
     */

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    @GET("filter.php?c=Seafood")
    suspend fun getMealSeafood() : Response<MealResponse>

    @GET("lookup.php")
    suspend fun getById(
        @Query("i") id: Int?
    ) : Response<DetailMealResponse>
}