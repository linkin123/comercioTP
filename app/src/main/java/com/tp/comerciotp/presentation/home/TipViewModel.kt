package com.tp.comerciotp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tp.comerciotp.utils.PayLogic

class TipViewModel : ViewModel(){

    private val _tipField = MutableLiveData<String>()
    val tipField: LiveData<String> get() = _tipField

    private val _etTip = MutableLiveData<Boolean>()
    val etTip: LiveData<Boolean> get() = _etTip

    private val _tipP = MutableLiveData<String>()
    val tipP: LiveData<String> get() = _tipP

    private val _cb10 = MutableLiveData<Boolean>()
    val cb10: LiveData<Boolean> get() = _cb10
    private var cbF10 = false

    private val _cb15 = MutableLiveData<Boolean>()
    val cb15: LiveData<Boolean> get() = _cb15
    private var cbF15 = false

    private val _cb20 = MutableLiveData<Boolean>()
    val cb20: LiveData<Boolean> get() = _cb20
    private var cbF20 = false

    private val _tip = MutableLiveData<Int>()
    val tip: LiveData<Int> get() = _tip

    private val _cbTip = MutableLiveData<Boolean>()
    val cbTip: LiveData<Boolean> get() = _cbTip

    private val _selectedTip = MutableLiveData<Boolean>()
    val selectedTip: LiveData<Boolean> get() = _selectedTip

    companion object {
        private const val ET_TIP = "et_tip"
        private const val CB_TIP = "cb_tip"
        private const val EMPTY_TIP = "empty_tip"
    }

    fun setCb(i: Int) {
        _tipP.value = "+$i% Propina"
        when (i) {
            5 -> {
                if (!cbF10) {
                    managerTip(true, CB_TIP, 5)
                    PayLogic.changeBank(_cb10, _cb15, _cb20, false)
                } else {
                    managerTip(false, EMPTY_TIP, 0)
                    PayLogic.changeBank(_cb10, _cb15, _cb20, true)
                }
                inv(5, cbF10)
            }
            10 -> {
                if (!cbF15) {
                    managerTip(true, CB_TIP, 10)
                    PayLogic.changeBank(_cb15, _cb10, _cb20, false)
                } else {
                    managerTip(false, EMPTY_TIP, 0)
                    PayLogic.changeBank(_cb10, _cb15, _cb20, true)
                }
                inv(10, cbF15)
            }
            15 -> {
                if (!cbF20) {
                    managerTip(true, CB_TIP, 15)
                    PayLogic.changeBank(_cb20, _cb15, _cb10, false)
                } else {
                    managerTip(false, EMPTY_TIP, 0)
                    PayLogic.changeBank(_cb10, _cb15, _cb20, true)
                }
                inv(15, cbF20)
            }
        }

    }

    private fun managerTip(cb: Boolean, tip: String, i: Int) {
        tip(editText = false, checkBox = cb)
        _tipField.value = tip
        _tip.value = i
    }

    private fun inv(i: Int, cb: Boolean) {
        cbF10 = false
        cbF15 = false
        cbF20 = false
        when (i) {
            5 -> cbF10 = !cb
            10 -> cbF15 = !cb
            15 -> cbF20 = !cb
        }
    }

    private fun tip(editText: Boolean, checkBox: Boolean) {
        _etTip.value = editText
        _cbTip.value = checkBox
        _selectedTip.value = checkBox
    }





}