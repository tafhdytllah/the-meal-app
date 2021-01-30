package com.tafh.themealapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tafh.themealapp.data.repository.MealRepository
import com.tafh.themealapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MealViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MealViewModelFactory(MealRepository())).get(MealViewModel::class.java)

        viewModel.categoryList.observe(this, Observer {
            if (it.isSuccessful) {
                val response = it.body()?.categories
                Log.d("TAG", "response caregoryList = $response")
            } else {
                Toast.makeText(this, "${it.errorBody()}", Toast.LENGTH_SHORT).show()
            }

        })
        viewModel.mealList.observe(this, Observer {
            if (it.isSuccessful) {
                val response = it.body()?.meals
                Log.d("TAG", "response mealList = $response")
            } else {
                Toast.makeText(this, "${it.errorBody()}", Toast.LENGTH_SHORT).show()
            }

        })
        viewModel.mealDetail.observe(this, Observer {
            if (it.isSuccessful) {
                val response = it.body()?.meals
                Log.d("TAG", "response mealDetail = $response")
            } else {
                Toast.makeText(this, "${it.errorBody()}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}