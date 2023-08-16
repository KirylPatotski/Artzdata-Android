package de.arztdata.app.data.app

import android.content.Context
import android.content.SharedPreferences


class AppData(private val context: Context){
    private var editor:SharedPreferences.Editor
    private var pref: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    init {
        editor = pref.edit()
    }

    companion object {

        const val SHARED_PREFS_NAME = "shared_prefs"

        const val DATA_VERSION = "data_version"
        const val NOTE = "profile_note"

        const val LOGGED_IN = "logged_in"
    }


    fun add(key:String, toAdd: Int? = 1){
        var number = toAdd
        if(toAdd == null) number = 1
        val tempNumber = pref.getInt(key,0)
        setInt(key,tempNumber+number!!)
    }

    fun setInt(key: String, value: Int){
        editor.apply {
            putInt(key, value)
        }.apply()
    }

    fun getInt(key: String, defaultValue: Int? = 0): Int {
        return pref.getInt(key, defaultValue!!)
    }

    fun setAnyString(key: String, value: String){
        editor.apply {
            putString(key, value)
        }.apply()
        println("$key -> $value")
    }

    fun getAnyString(key: String, defaultValue: String? = "" ): String? {
        return pref.getString(key, defaultValue)
    }

    fun setAnyBoolean(key: String, boolean: Boolean) {
        editor.apply {
            putBoolean(key, boolean)
        }.apply()
        println("$key $boolean")
    }

    fun getAnyBoolean(key: String, defaultValue: Boolean? = true): Boolean {
        return pref.getBoolean(key, defaultValue!!)
    }
}

