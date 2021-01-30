package com.tafh.themealapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import com.tafh.themealapp.data.model.DetailMeal
import com.tafh.themealapp.data.repository.MealRepository
import com.tafh.themealapp.databinding.FragmentDetailBinding
import com.tafh.themealapp.viewmodel.MealViewModel
import com.tafh.themealapp.viewmodel.MealViewModelFactory


class DetailFragment : Fragment() {


    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel by viewModels<MealViewModel> {
        MealViewModelFactory(MealRepository())
    }

    private lateinit var ingredientText : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val meal = args.meal
        val id = meal.idMeal.toInt()

        viewModel.getById(id)

        viewModel.mealDetail.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                val response = it.body()?.meals?.get(0)
                setupUi(response)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        })


        return binding.root
    }

    private fun setupUi(response: DetailMeal?) {


        binding.apply {
            tvTitleMeal.text = response?.strMeal

            ivDetailMeal.load(response?.strMealThumb) {
                crossfade(true)
                crossfade(1000)
                placeholder(R.drawable.ic_image_placeholder)
                error(R.drawable.ic_no_image)
            }

            ingredientText(response)

            tvBodyIngredient.text = ingredientText

            tvBodyIntructions.text = response?.strInstructions.toString()
        }
    }

    private fun ingredientText(response: DetailMeal?) {
        ingredientText = "${response!!.strMeasure1} ${response.strIngredient1}, " +
                    "${response.strMeasure2} ${response.strIngredient2}, " +
                    "${response.strMeasure3} ${response.strIngredient3}, " +
                    "${response.strMeasure4} ${response.strIngredient4}, " +
                    "${response.strMeasure5} ${response.strIngredient5}, " +
                    "${response.strMeasure6} ${response.strIngredient6}, " +
                "${response.strMeasure7} ${response.strIngredient7}, " +
                "${response.strMeasure8} ${response.strIngredient8}, " +
                "${response.strMeasure9} ${response.strIngredient9}, " +
                "${response.strMeasure10} ${response.strIngredient10}, " +
                "${response.strMeasure11} ${response.strIngredient11}, " +
                "${response.strMeasure12} ${response.strIngredient12}, " +
                "${response.strMeasure13} ${response.strIngredient13}, " +
                "${response.strMeasure14} ${response.strIngredient14}, " +
                "${response.strMeasure15} ${response.strIngredient15}, " +
                "${response.strMeasure16} ${response.strIngredient16}, " +
                "${response.strMeasure17} ${response.strIngredient17}, " +
                "${response.strMeasure18} ${response.strIngredient18}, " +
                "${response.strMeasure19} ${response.strIngredient19}, " +
                "${response.strMeasure20} ${response.strIngredient20}"
    }


}