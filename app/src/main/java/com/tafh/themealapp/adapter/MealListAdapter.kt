package com.tafh.themealapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tafh.themealapp.R
import com.tafh.themealapp.data.model.Meal
import com.tafh.themealapp.databinding.ItemMealBinding

class MealListAdapter : RecyclerView.Adapter<MealListAdapter.MealViewHolder>() {

    private var mealList = emptyList<Meal>()

    inner class MealViewHolder(private val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            binding.apply {
                ivImage.load(meal.strMealThumb) {
                    crossfade(true)
                    crossfade(1000)
                    placeholder(R.drawable.ic_image_placeholder)
                    error(R.drawable.ic_no_image)
                }

                tvTitle.text = meal.strMeal
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(mealList[position])
    }

    override fun getItemCount(): Int = mealList.size

    fun setData(list: List<Meal>?) {
        if (list != null) {
            this.mealList = list
            notifyDataSetChanged()
        }
    }



}