package com.tp.comerciotp.data.remote.auth

import com.google.firebase.auth.FirebaseAuth

class CloseSessionDataSource {

    fun singOut(): Boolean {
        FirebaseAuth.getInstance().signOut()
        return true
    }

}