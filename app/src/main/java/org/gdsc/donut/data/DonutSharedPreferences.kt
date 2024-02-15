package org.gdsc.donut.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object DonutSharedPreferences {
    private lateinit var preferences: SharedPreferences
    private const val PREFERENCES_NAME = "DONUT_PREFERENCES"
    private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun set(token: String?){
        preferences.edit {
            if (token == null) remove(KEY_ACCESS_TOKEN)
            else putString(KEY_ACCESS_TOKEN, token)
        }
    }

    /* 예시
    fun setUserName(token: String?){
        preferences.edit {
            if (token == null) {
                remove(KEY_USER_NAME)
            } else {
                putString(KEY_USER_NAME, token)
            }
        }
    }

    fun getUserName(): String? = preferences.getString(KEY_USER_NAME, null)
     */
}