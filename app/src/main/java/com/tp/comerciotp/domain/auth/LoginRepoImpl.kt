package com.tp.comerciotp.domain.auth

import com.google.firebase.auth.FirebaseUser
import com.tp.comerciotp.data.remote.auth.LoginDataSource

class LoginRepoImpl(private val dataSource : LoginDataSource): LoginRepo {

    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        dataSource.signIn(email, password)

}