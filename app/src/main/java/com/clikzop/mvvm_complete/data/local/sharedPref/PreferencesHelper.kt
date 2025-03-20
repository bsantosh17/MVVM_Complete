package com.clikzop.mvvm_complete.data.local.sharedPref

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val sharedPreference:SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

    companion object{
        private const val PREF_NAME = "Login"
        private const val KEY_NAME = "isLogin"

    }

    fun saveLogin(name:String){
        sharedPreference.edit().putString(KEY_NAME,name).apply()
    }

    fun getLogin():String?{
        return sharedPreference.getString(KEY_NAME,null)
    }

    fun clear(){
        sharedPreference.edit().clear().apply()
    }
}