package com.fs.hcmgmt.util

import android.content.SharedPreferences

class SessionManager(private val preferences: SharedPreferences) {

    fun hasToken(): Boolean = Constants.PREF_TOKEN.hasString()

    fun putToken(value: String): Boolean = Constants.PREF_TOKEN.put(value)

    fun getToken(): String? = Constants.PREF_TOKEN.getString("")

    fun clearToken(): Boolean = Constants.PREF_TOKEN.delete()

    fun getProject(): String? = Constants.PREF_PROJECT.getString("")

    fun putProject(value: String): Boolean = Constants.PREF_PROJECT.put(value)

    fun getZone(): String? = Constants.PREF_ZONE.getString("eu-west-101")

    fun putZone(value: String): Boolean = Constants.PREF_ZONE.put(value)

    private fun String.hasString(): Boolean =
        preferences.contains(this)

    private fun String.getString(defValue: String): String? =
        preferences.getString(this, defValue)

    private fun String.put(value: String): Boolean = preferences.edit().putString(this, value).commit()

    private fun String.delete(): Boolean = preferences.edit().remove(this).commit()
}