package com.tp.comerciotp.domain.auth

interface CloseSessionRepo {
    fun singOut() : Boolean
}