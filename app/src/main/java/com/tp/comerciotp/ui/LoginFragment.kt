package com.tp.comerciotp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.tp.comerciotp.R
import com.tp.comerciotp.core.Resource
import com.tp.comerciotp.data.remote.auth.LoginDataSource
import com.tp.comerciotp.databinding.FragmentLoginBinding
import com.tp.comerciotp.domain.auth.LoginRepoImpl
import com.tp.comerciotp.presentation.auth.LoginScreenViewModel
import com.tp.comerciotp.presentation.auth.LoginScreenViewModelFactory
import com.tp.comerciotp.utils.KeyUser
import com.tp.comerciotp.utils.PreferencesHelper

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel by viewModels<LoginScreenViewModel> {
        LoginScreenViewModelFactory(LoginRepoImpl(LoginDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        isUserLoggedIn()
        doLogin()
    }

    private fun isUserLoggedIn() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.homeScreenFragment)
        }
    }

    private fun doLogin() {
        binding.btnLoginStartSession.setOnClickListener {
            val email = binding.tietLoginUser.text.toString().trim()
            val pass = binding.tietLoginPass.text.toString().trim()

            validateCredentials(email, pass)
            signIn(email, pass)

        }
    }

    private fun validateCredentials(email: String, pass: String) {
        if (email.isEmpty()) {
            binding.tietLoginUser.error = "E-mail está vacío"
            return
        }

        if (pass.isEmpty()) {
            binding.tietLoginPass.error = "Password está vacío"
            return
        }

    }

    private fun signIn(email: String, pass: String) {
        viewModel.signIn(email, pass).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.btnLoginStartSession.isEnabled = false
                }
                is Resource.Success -> {
                    PreferencesHelper.saveString(KeyUser.UID, result.data?.uid)
                    findNavController().navigate(R.id.homeScreenFragment)
                    binding.progress.visibility = View.GONE
                }
                is Resource.Failure -> {
                    binding.progress.visibility = View.GONE
                    binding.btnLoginStartSession.isEnabled = true
                    Toast.makeText(requireContext(), "Error : ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }



}