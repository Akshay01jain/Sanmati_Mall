package com.sanmati.mall.commonUtils

expect object SharedPreference {
    fun putString(key: String, value: String)
    fun getString(key: String, defaultValue: String? = null): String?
    fun remove(key: String)
    fun clear()
}