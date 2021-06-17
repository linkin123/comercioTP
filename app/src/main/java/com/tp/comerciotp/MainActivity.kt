package com.tp.comerciotp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.tp.comerciotp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    companion object {
        const val FRAGMENT_QR = "QR"
        const val FRAGMENT_CLOSE_SESSION = "CloseSessionDialogFragment"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupNavigation()
        onclicks()
    }

    private fun onclicks() {
        binding.ivItem1.setOnClickListener {
            if(!navController.currentDestination?.label?.equals(FRAGMENT_QR)!!){
                navController.navigate(R.id.homeScreenFragment)
            }
        }
        binding.ivItem2.setOnClickListener {
            if(!navController.currentDestination?.label?.equals(FRAGMENT_CLOSE_SESSION)!!){
                navController.navigate(R.id.CloseSessionDialogFragment)

            }
        }
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.myNavHostFragment)
        visibilityNavElements(navController)
    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeScreenFragment-> {
                    binding.layerIdes.visibility = View.VISIBLE
                }
                R.id.CloseSessionDialogFragment-> {
                    binding.layerIdes.visibility = View.VISIBLE
                }
                else -> {
                    binding.layerIdes.visibility = View.GONE
                }
            }
        }
    }


}