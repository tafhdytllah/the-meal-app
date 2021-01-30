package com.tafh.themealapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tafh.themealapp.adapter.MealListAdapter
import com.tafh.themealapp.data.model.Meal
import com.tafh.themealapp.data.repository.MealRepository
import com.tafh.themealapp.databinding.FragmentListBinding
import com.tafh.themealapp.viewmodel.MealViewModel
import com.tafh.themealapp.viewmodel.MealViewModelFactory

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MealViewModel

    private val mealAdapter = MealListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, MealViewModelFactory(MealRepository())).get(MealViewModel::class.java)

        setAdapter()

        viewModel.mealList.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                val response = it.body()?.meals
                mealAdapter.setData(response)
            } else {
                Log.d("LOG", "${it.errorBody()}")
            }
        })

        mealAdapter.setOnItemClickCallBack(object : MealListAdapter.OnItemClickCallBack {
            override fun onItemClick(meal: Meal) {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(meal)
                findNavController().navigate(action)
            }

        })

        return binding.root
    }

    private fun setAdapter() {
        binding.apply {
            with(rvMealList) {
                setHasFixedSize(true)
                adapter = mealAdapter
            }
        }
    }


}