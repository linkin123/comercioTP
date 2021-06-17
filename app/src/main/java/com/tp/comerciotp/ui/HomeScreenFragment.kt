package com.tp.comerciotp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tp.comerciotp.R
import com.tp.comerciotp.data.remoteeeee.home.HomeDataSource
import com.tp.comerciotp.databinding.FragmentHomeScreenBinding
import com.tp.comerciotp.domain.home.HomeRepoImpl
import com.tp.comerciotp.presentation.home.HomeScreenViewModel
import com.tp.comerciotp.presentation.home.HomeScreenViewModelFactory
import com.tp.comerciotp.repository.RetrofitClient


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel> {
        HomeScreenViewModelFactory(HomeRepoImpl(HomeDataSource(RetrofitClient.webService)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)
        setUpToolbar()
        onclicks()
    }

    private fun onclicks() {
        binding.toolbarId.actionBar.ivBack.setOnClickListener {
            findNavController().navigate(R.id.CloseSessionDialogFragment)
        }
    }

    private fun setUpToolbar() {
        binding.toolbarId.actionBar.tvTitle.text = "generar QR"
    }



}