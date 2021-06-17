package com.tp.comerciotp.data.remoteeeee.auth

import com.google.firebase.auth.FirebaseAuth

class CloseSessionDataSource {

    fun singOut(): Boolean {
        FirebaseAuth.getInstance().signOut()
        return true
    }

}