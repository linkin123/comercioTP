package com.tp.comerciotp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.tp.comerciotp.R
import com.tp.comerciotp.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

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

    private fun doLogin(){
        binding.btnLoginStartSession.setOnClickListener{
            val email = binding.tietLoginUser.text.toString().trim()
            val pass = binding.tietLoginPass.text.toString().trim()

            validateCredentials(email, pass)
            signIn(email, pass)

        }
    }

    private fun validateCredentials(email : String , pass: String){
        if(email.isEmpty()){
            binding.tietLoginUser.error = "E-mail está vacío"
            return
        }

        if(pass.isEmpty()){
            binding.tietLoginPass.error = "Password está vacío"
            return
        }

    }

    private fun signIn(email : String, pass : String){

    }


}