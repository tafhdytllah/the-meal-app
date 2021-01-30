package com.tafh.themealapp.data.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
) : Parcelable