package com.tp.comerciotp.utils

import androidx.lifecycle.MutableLiveData

object PayLogic {

    fun getMoneyFormat(balance: String): String {
        return MoneyUtils.getMoney(balance)
    }

    fun getMoneyWithoutFormat(balance: String): String {
        return MoneyUtils.getStrSinComas(balance)
    }

    fun change(
        _cb1: MutableLiveData<Boolean>,
        _cb2: MutableLiveData<Boolean>,
        _cb3: MutableLiveData<Boolean>,
        _cb4: MutableLiveData<Boolean>,
        _cb5: MutableLiveData<Boolean>,
        all: Boolean
    ) {
        _cb1.value = !all
        _cb2.value = false
        _cb3.value = false
        _cb4.value = false
        _cb5.value = false
    }

    fun changeBank(
        _cb1: MutableLiveData<Boolean>,
        _cb2: MutableLiveData<Boolean>,
        _cb3: MutableLiveData<Boolean>,
        all: Boolean
    ) {
        _cb1.value = !all
        _cb2.value = false
        _cb3.value = false
    }

    fun getPropina(amount: String, propina: Int): String? {
        return MoneyUtils.getPropina(amount, propina)
    }

}