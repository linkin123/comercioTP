package com.tp.comerciotp.application

import android.app.Application
import com.tp.comerciotp.utils.PreferencesHelper

class Aplication : Application(){

    override fun onCreate() {
        super.onCreate()
        PreferencesHelper.init(applicationContext)
    }
}