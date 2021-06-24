package com.tp.comerciotp.utils

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {
    private lateinit var prefs: SharedPreferences
    const val MIS_PREFERENCES = "mis_preferencias"
    private const val DEFAULT = ""

    fun init(context: Context) {
        prefs = context.getSharedPreferences(MIS_PREFERENCES, Context.MODE_PRIVATE)
    }


    fun saveString(key: String?, value: String?) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?): String? {
        return prefs.getString(key, DEFAULT)
    }

    fun saveBoolean(key: String?, value: Boolean) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String?): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun getBoolean(key: String?, valDef : Boolean): Boolean {
        return prefs.getBoolean(key, valDef)
    }

    fun saveInt(key: String?, value: Int) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String?): Int {
        return prefs.getInt(key, 0)
    }

    fun clearData() {
        prefs.edit().clear().apply()
    }
}
