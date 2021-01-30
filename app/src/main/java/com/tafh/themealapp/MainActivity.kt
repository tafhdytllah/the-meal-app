package com.tafh.themealapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.tafh.themealapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupNavigation()
        }


    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHosFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            val toolBar = supportActionBar ?: return@addOnDestinationChangedListener
            when(destination.id) {
                R.id.listFragment -> {
                    toolBar.apply {
                        show()
                        setDisplayShowTitleEnabled(true)

                    }
                }
                else -> {
                    toolBar.apply {
                        show()
                        setDisplayShowTitleEnabled(true)
//                        setHomeAsUpIndicator(R.drawable.ic_arrow_back)
//                        setDisplayHomeAsUpEnabled(true)
                    }
                }
            }

        }
    }
}