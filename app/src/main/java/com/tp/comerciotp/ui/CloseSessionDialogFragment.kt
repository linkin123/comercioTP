package com.tp.comerciotp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tp.comerciotp.R
import com.tp.comerciotp.core.Resource
import com.tp.comerciotp.data.remote.auth.CloseSessionDataSource
import com.tp.comerciotp.databinding.FragmentCloseSessionDialogBinding
import com.tp.comerciotp.domain.auth.CloseSessionImpl
import com.tp.comerciotp.presentation.auth.CloseSessionViewModel
import com.tp.comerciotp.presentation.auth.CloseSessionViewModelFactory
import com.tp.comerciotp.utils.KeyUser
import com.tp.comerciotp.utils.PreferencesHelper

class CloseSessionDialogFragment : DialogFragment(R.layout.fragment_close_session_dialog) {

    private lateinit var binding: FragmentCloseSessionDialogBinding
    private val viewModel by viewModels<CloseSessionViewModel> {
        CloseSessionViewModelFactory(CloseSessionImpl(CloseSessionDataSource()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCloseSessionDialogBinding.bind(view)
        onclicks()
    }

    private fun onclicks() {
        binding.mCloseSessionButton.setOnClickListener {
            viewModel.singOut().observe(viewLifecycleOwner, { result ->
                when (result) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        findNavController().navigate(R.id.loginFragment)
                        PreferencesHelper.clearData()
                        dismissAllowingStateLoss()
                    }
                    is Resource.Failure -> {
                        Toast.makeText(
                            requireContext(),
                            "error en firebase Out",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
        binding.mCancelCloseSessionButton.setOnClickListener {
            findNavController().navigate(R.id.homeScreenFragment)
            dismissAllowingStateLoss()
        }
    }



}